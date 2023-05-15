package com.vegera.service;

import com.vegera.entity.Client;
import com.vegera.entity.CryptoCurrency;
import com.vegera.repository.ClientRepository;
import com.vegera.repository.CryptoCurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class NotifyService {

    private static final String ACTUAL_INFO_BTC = "https://api.coinlore.net/api/ticker?id=90";
    private static final String ACTUAL_INFO_ETH = "https://api.coinlore.net/api/ticker?id=80";
    private static final String ACTUAL_INFO_SOL = "https://api.coinlore.net/api/ticker?id=48543";
    private static final BigDecimal MAX_CHANGE = BigDecimal.valueOf(1);
    private static final BigDecimal CONVERT_TO_PERCENT = BigDecimal.valueOf(100);

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final ClientRepository clientRepository;
    private final RestTemplate restTemplate;

    @Scheduled(fixedDelay = 60000L)
    public void updateCurrencies() {
        CryptoCurrency btc = updateInfoFromServer(ACTUAL_INFO_BTC);
        CryptoCurrency eth = updateInfoFromServer(ACTUAL_INFO_ETH);
        CryptoCurrency sol = updateInfoFromServer(ACTUAL_INFO_SOL);

        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            loggingInfo(btc, client);
            loggingInfo(eth, client);
            loggingInfo(sol, client);
        }
    }

    private void loggingInfo(CryptoCurrency cryptoCurrency, Client client) {
        if (client.getSymbol().equals(cryptoCurrency.getSymbol())) {
            BigDecimal changePrice = calculatePriceChange(client.getPrice(), cryptoCurrency.getPriceUsd());
            if (changePrice.abs().compareTo(MAX_CHANGE) > 0) {
                log.warn("Currency: {}, username: {}, change of price: {} percent", cryptoCurrency.getSymbol(), client.getUsername(), changePrice);
            }
        }
    }

    private CryptoCurrency updateInfoFromServer(String url) {
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Map<String, Object>>>() {
        });
        List<Map<String, Object>> body = response.getBody();
        Map<String, Object> data = body.get(0);
        String id = String.valueOf(data.get("id"));
        String symbol = String.valueOf(data.get("symbol"));
        String price = String.valueOf(data.get("price_usd"));

        CryptoCurrency cryptoCurrency = CryptoCurrency.builder()
                .id(Long.valueOf(id))
                .symbol(symbol)
                .priceUsd(new BigDecimal(price))
                .build();
        return cryptoCurrencyRepository.save(cryptoCurrency);
    }

    private BigDecimal calculatePriceChange(BigDecimal oldPrice, BigDecimal currentPrice) {
        BigDecimal priceChange = currentPrice.subtract(oldPrice).divide(oldPrice, 5, RoundingMode.CEILING);
        return priceChange.multiply(CONVERT_TO_PERCENT);
    }
}

