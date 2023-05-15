package com.vegera.service;


import com.vegera.dto.CryptoCurrencyDto;
import com.vegera.entity.CryptoCurrency;
import com.vegera.exception.CurrencyNotFoundException;
import com.vegera.mapper.CryptoMapperToDto;
import com.vegera.repository.CryptoCurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CryptoMapperToDto cryptoMapperToDto;

    public List<CryptoCurrencyDto> getAll() {
        return cryptoCurrencyRepository.findAll().stream()
                .map(cryptoMapperToDto::mapFrom)
                .collect(toList());
    }

    public CryptoCurrency getCurrency(String symbol) {
        return cryptoCurrencyRepository.findBySymbol(symbol)
                .orElseThrow(() -> new CurrencyNotFoundException(String.format("Currency %s not found", symbol)));
    }
}
