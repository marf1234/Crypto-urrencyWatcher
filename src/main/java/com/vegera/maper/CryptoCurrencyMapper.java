package com.vegera.maper;

import com.vegera.dto.CryptoCurrencyDto;
import com.vegera.model.CryptoCurrency;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface CryptoCurrencyMapper {

    CryptoCurrencyDto toDto(CryptoCurrency cryptoCurrency);

    CryptoCurrency fromDto(CryptoCurrencyDto cryptoCurrencyDto);

    List<CryptoCurrencyDto> toDto(List<CryptoCurrency> shops);
}