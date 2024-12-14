package com.jonathandiz.convertidor_de_monedas.controllers;

import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistoryModel;
import com.jonathandiz.convertidor_de_monedas.services.CurrencyExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exchange")
public class CurrencyExchangeController {

	private final CurrencyExchangeService currencyExchangeService;
	
	public CurrencyExchangeController(CurrencyExchangeService currencyExchangeService) {
		this.currencyExchangeService = currencyExchangeService;
	}
	
	@PostMapping
	public ResponseEntity<ExchangeRateHistoryModel> saveExchangeRateHistory(@RequestBody ExchangeRateHistoryModel history) {
		return ResponseEntity.ok(currencyExchangeService.saveExchangeRateHistory(history));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ExchangeRateHistoryModel> getExchangeRateHistory(@PathVariable Long id) {
		return ResponseEntity.ok(currencyExchangeService.getExchangeRateHistoryById(id));
	}
}
