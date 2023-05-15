package com.vegera.mapper;


import com.vegera.dto.CryptoCurrencyDto;
import com.vegera.entity.CryptoCurrency;
import org.springframework.stereotype.Component;

@Component
public class CryptoMapperToDto implements Mapper<CryptoCurrency, CryptoCurrencyDto> {

    public CryptoCurrencyDto mapFrom(CryptoCurrency entity) {
        return CryptoCurrencyDto.builder()
                .id(String.valueOf(entity.getId()))
                .symbol(entity.getSymbol())
                .priceUsd(String.valueOf(entity.getPriceUsd()))
                .build();
    }
}
