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

        CryptoCurrency currency = cryptoCurrencyRepository.findBySymbol(clientNotifyRequest.getSymbol())
                .orElseThrow(() -> new CurrencyNotFoundException(String.format("Currency %s not found", clientNotifyRequest.getSymbol())));

        Optional<Client> clientOpt = clientRepository.findByUsernameAndSymbol(clientNotifyRequest.getUsername(), clientNotifyRequest.getSymbol());

        Client client;

        if (clientOpt.isPresent()) {
            clientOpt.get().setSymbol(currency.getSymbol());
            clientOpt.get().setPrice(currency.getPriceUsd());
        } else {
            client = Client.builder()
                    .username(clientNotifyRequest.getUsername())
                    .price(currency.getPriceUsd())
                    .symbol(currency.getSymbol())
                    .build();
            clientRepository.save(client);
        }
    }
}
