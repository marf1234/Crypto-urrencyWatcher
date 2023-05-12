package com.vegera.controller;

import com.vegera.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NotifyController {
    @Autowired
    private CryptocurrencyService cryptocurrencyService;

    @Autowired
    private UserQuoteRepository userQuoteRepository;

    @PostMapping("/notify")
    public void notify(@RequestBody UserQuote userQuote) {
        CryptocurrencyQuote quote = cryptocurrencyService.getQuote(userQuote.getSymbol());
        if (quote != null) {
            userQuote.setQuote(quote);
            userQuoteRepository.save(userQuote);
        }
    }
}