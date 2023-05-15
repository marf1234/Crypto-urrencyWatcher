package com.vegera.controller;

import com.vegera.dto.ClientNotifyRequest;
import com.vegera.dto.CryptoCurrencyDto;

import com.vegera.entity.CryptoCurrency;
import com.vegera.service.ClientService;
import com.vegera.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/crypto")
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;
    private final ClientService clientService;


    @GetMapping("/all")
    public List<CryptoCurrencyDto> getAll() {
        return cryptoCurrencyService.getAll();
    }

    @GetMapping("/{symbol}")
    public CryptoCurrency getCurrency(@PathVariable String symbol) {
        return cryptoCurrencyService.getCurrency(symbol);
    }

    @PostMapping("/notify")
    public void saveClientInfo(ClientNotifyRequest clientNotifyRequest) {
        clientService.save(clientNotifyRequest);
    }
}
