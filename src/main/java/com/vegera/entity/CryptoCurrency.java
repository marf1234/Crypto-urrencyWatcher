package com.vegera.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "crypto_currency")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CryptoCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "price_usd")
    private BigDecimal priceUsd;
}

