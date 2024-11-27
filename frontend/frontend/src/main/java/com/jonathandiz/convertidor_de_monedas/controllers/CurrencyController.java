package com.jonathandiz.convertidor_de_monedas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonathandiz.convertidor_de_monedas.services.CurrencyService;

@RestController
public class CurrencyController {
	
	private final CurrencyService currencyService;
	
	public CurrencyController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}
	
	@GetMapping("/api/rates")
	public Object getRates() {
		return currencyService.getExchangeRates();
	}
}
