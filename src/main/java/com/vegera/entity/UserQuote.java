package com.vegera.entity;

import com.vegera.controller.CryptocurrencyQuote;

public class UserQuote {
    private String username;
    private String symbol;
    private CryptocurrencyQuote quote;
    // getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public CryptocurrencyQuote getQuote() {
        return quote;
    }

    public void setQuote(CryptocurrencyQuote quote) {
        this.quote = quote;
    }
}
