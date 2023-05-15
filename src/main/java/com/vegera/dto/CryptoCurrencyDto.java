package com.vegera.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Value
@Builder
public class CryptoCurrencyDto {

    String id;

    String symbol;

    @JsonProperty("price_usd")
    String priceUsd;

}