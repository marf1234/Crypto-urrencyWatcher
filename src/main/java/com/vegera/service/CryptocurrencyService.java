package com.vegera.service;

import com.vegera.controller.CryptocurrencyQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CryptocurrencyService {
    @Value("${crypto.list}")
    private List<Cryptocurrency> cryptocurrencies;

    @Autowired
    private CryptocurrencyQuoteRepository quoteRepository;

    @Scheduled(fixedRate = 60000)
    public void updateQuotes() {
        for (Cryptocurrency cryptocurrency : cryptocurrencies) {
            String url = "https://api.coinlore.net/api/ticker/?id=" + cryptocurrency.getId();
            ResponseEntity<CryptocurrencyQuote> response = restTemplate.getForEntity(url, CryptocurrencyQuote.class);
            CryptocurrencyQuote quote = response.getBody();
            quote.setSymbol(cryptocurrency.getSymbol());
            quote.setTimestamp(LocalDateTime.now());
            quoteRepository.save(quote);
        }
    }

    public List<Cryptocurrency> getCryptocurrencies() {
        return cryptocurrencies;
    }

    public CryptocurrencyQuote getQuote(String symbol) {
        return quoteRepository.findBySymbol(symbol);
    }
}
