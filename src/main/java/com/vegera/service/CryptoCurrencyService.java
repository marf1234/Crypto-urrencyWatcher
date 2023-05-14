package com.vegera.service;

import com.vegera.dto.CryptoCurrencyDto;

import java.util.List;

public interface CryptoCurrencyService {

    List<CryptoCurrencyDto> getAll();

    CryptoCurrencyDto getPrice(String symbol);
}