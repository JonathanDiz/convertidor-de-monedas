package com.jonathandiz.convertidor_de_monedas.controllers;

import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistoryModel;
import com.jonathandiz.convertidor_de_monedas.services.ExchangeRateHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rates")
public class ExchangeRateHistoryController {

    private final ExchangeRateHistoryService service;

    public ExchangeRateHistoryController(ExchangeRateHistoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<ExchangeRateHistoryModel> getRates(@RequestParam String sourceCurrency,
                                              @RequestParam String targetCurrency) {
        return service.getRates(sourceCurrency, targetCurrency);
    }

    @PostMapping
    public void saveRate(@RequestBody ExchangeRateHistoryModel rate) {
        service.saveRate(rate);
    }
}
