package com.vegera.service;

import com.vegera.dto.CryptoCurrencyDto;
import com.vegera.maper.CryptoCurrencyMapper;
import com.vegera.model.CryptoCurrency;
import com.vegera.repisitory.CryptoCurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;



@Slf4j
@Service
@RequiredArgsConstructor
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {
    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CryptoCurrencyMapper cryptoCurrencyMapper;

    @Override
    public List<CryptoCurrencyDto> getAll() {
        List<CryptoCurrency> currencyList = cryptoCurrencyRepository.findAll();
        return cryptoCurrencyMapper.toDto(currencyList);
    }

    @Override
    public CryptoCurrencyDto getPrice(String symbol) {
        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findBySymbol(symbol);
        return cryptoCurrencyMapper.toDto(cryptoCurrency);
    }
}