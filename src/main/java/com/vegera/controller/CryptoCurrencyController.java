package com.vegera.controller;

import com.vegera.dto.CryptoCurrencyDto;
import com.vegera.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping(Mapping.CRYPTOCURRENCY_CONTROLLER)
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;

    @GetMapping("/")
    public ResponseEntity<List<CryptoCurrencyDto>> getAllCryptoCurrencies() {
        List<CryptoCurrencyDto> cryptoCurrencies = cryptoCurrencyService.getAll();
        return ResponseEntity.ok(cryptoCurrencies);
    }

    @GetMapping("/{symbol}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CryptoCurrencyDto> getCryptoCurrencyPrice(@NotNull @PathVariable String symbol) {
        CryptoCurrencyDto cryptoCurrencyDto = cryptoCurrencyService.getPrice(symbol);
        return ResponseEntity.ok(cryptoCurrencyDto);
    }
}