package com.vegera.service;

import com.vegera.dto.ClientNotifyRequest;
import com.vegera.entity.Client;
import com.vegera.entity.CryptoCurrency;
import com.vegera.exception.CurrencyNotFoundException;
import com.vegera.repository.ClientRepository;
import com.vegera.repository.CryptoCurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    public void save(ClientNotifyRequest clientNotifyRequest) {
        String symbol = clientNotifyRequest.getSymbol();
        String username = clientNotifyRequest.getUsername();

        CryptoCurrency currency = cryptoCurrencyRepository.findBySymbol(symbol)
                .orElseThrow(() -> new CurrencyNotFoundException(String.format("Currency %s not found", symbol)));

        clientRepository.findByUsernameAndSymbol(username, symbol)
                .ifPresentOrElse(
                        client -> updateClient(client, currency),
                        () -> createClient(username, currency)
                );
    }

    private void updateClient(Client client, CryptoCurrency currency) {
        client.setSymbol(currency.getSymbol());
        client.setPrice(currency.getPriceUsd());
    }

    private void createClient(String username, CryptoCurrency currency) {
        Client client = Client.builder()
                .username(username)
                .symbol(currency.getSymbol())
                .price(currency.getPriceUsd())
                .build();
        clientRepository.save(client);
    }
}

