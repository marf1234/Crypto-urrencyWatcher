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

/**
 * Service for managing clients and their notifications.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    /**
     * Saves a client's notification request.
     *
     * @param clientNotifyRequest The client's notification request.
     * @throws CurrencyNotFoundException if the requested currency is not found.
     */
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
        /**
         * Updates an existing client with the latest currency information.
         *
         * @param client   The client to update.
         * @param currency The updated currency information.
         */
        client.setSymbol(currency.getSymbol());
        client.setPrice(currency.getPriceUsd());
    }

    private void createClient(String username, CryptoCurrency currency) {
        /**
         * Creates a new client with the specified username and currency information.
         *
         * @param username The username of the new client.
         * @param currency The currency information.
         */
        Client client = Client.builder()
                .username(username)
                .symbol(currency.getSymbol())
                .price(currency.getPriceUsd())
                .build();
        clientRepository.save(client);
    }
}


