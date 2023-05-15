package com.vegera.mapper;


import com.vegera.dto.CryptoCurrencyDto;
import com.vegera.entity.CryptoCurrency;
import org.springframework.stereotype.Component;

@Component
public class CryptoMapperToDto implements Mapper<CryptoCurrency, CryptoCurrencyDto> {
    @Override
    public CryptoCurrencyDto mapFrom(CryptoCurrency entity) {
        return CryptoCurrencyDto.builder()
                .id(entity.getId().toString())
                .symbol(entity.getSymbol())
                .priceUsd(entity.getPriceUsd().toString())
                .build();
    }
}
