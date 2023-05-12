package com.vegera.controller;

import com.vegera.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CryptocurrencyController {
    @Autowired
    private CryptocurrencyService cryptocurrencyService;

    @GetMapping("/cryptocurrencies")
    public List<Cryptocurrency> getCryptocurrencies() {
        return cryptocurrencyService.getCryptocurrencies();
    }

    @GetMapping("/quotes/{symbol}")
    public CryptocurrencyQuote getQuote(@PathVariable String symbol) {
        return cryptocurrencyService.getQuote(symbol);
    }
}
