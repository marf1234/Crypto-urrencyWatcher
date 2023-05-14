package com.vegera.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "cryptocurrencies")
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCurrency {

    @Id
    private Long id;
    private String symbol;
    private BigDecimal priceUSD;
}