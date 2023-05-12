package com.vegera.controller;

import com.vegera.entity.UserQuote;
import com.vegera.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotifyTask {
    @Autowired
    private CryptocurrencyService cryptocurrencyService;

    @Autowired
    private UserQuoteRepository userQuoteRepository;

    @Scheduled(fixedRate = 60000)
    public void notifyUsers() {
        List<UserQuote> userQuotes = userQuoteRepository.findAll();
        for (UserQuote userQuote : userQuotes) {
            CryptocurrencyQuote currentQuote = cryptocurrencyService.getQuote(userQuote.getSymbol());
            CryptocurrencyQuote registeredQuote = userQuote.getQuote();
            double percentageChange = (currentQuote.getPrice() - registeredQuote.getPrice()) / registeredQuote.getPrice() * 100;
            if (Math.abs(percentageChange) > 1) {
                String message = String.format("Price change for %s (%s) by %f%% since registration by user %s",
                        registeredQuote.getSymbol(), registeredQuote.getTimestamp(), percentageChange, userQuote.getUsername());
                log.warn(message);
            }
        }
    }
}
