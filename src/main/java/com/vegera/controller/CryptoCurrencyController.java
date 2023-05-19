package com.vegera.controller;

import com.vegera.dto.ClientNotifyRequest;
import com.vegera.dto.CryptoCurrencyDto;

import com.vegera.entity.CryptoCurrency;
import com.vegera.service.ClientService;
import com.vegera.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller class for handling cryptocurrency-related API endpoints.
 */
@RestController
@RequestMapping("/api/crypto")
@RequiredArgsConstructor
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;
    private final ClientService clientService;

    /**
     * Retrieves all cryptocurrency currencies.
     *
     * @return List of CryptoCurrencyDto representing all cryptocurrencies.
     */
    @GetMapping("/all")
    public List<CryptoCurrencyDto> getAllCryptoCurrencies() {
        return cryptoCurrencyService.getAll();
    }

    /**
     * Retrieves a specific cryptocurrency by symbol.
     *
     * @param symbol The symbol of the cryptocurrency to retrieve.
     * @return CryptoCurrency representing the cryptocurrency.
     */
    @GetMapping("/{symbol}")
    public CryptoCurrency getCryptoCurrency(@PathVariable String symbol) {
        return cryptoCurrencyService.getCurrency(symbol);
    }

    /**
     * Saves client information for cryptocurrency notifications.
     *
     * @param clientNotifyRequest The ClientNotifyRequest containing the client information.
     */
    @PostMapping("/notify")
    public void saveClientInfo(@RequestBody ClientNotifyRequest clientNotifyRequest) {
        clientService.save(clientNotifyRequest);
    }
}
