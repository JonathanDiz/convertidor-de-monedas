package com.jonathandiz.convertidor_de_monedas.controllers;

import com.jonathandiz.convertidor_de_monedas.services.ExchangeRateService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/exchange-rates")
public class ExchangeRateController {

	private final ExchangeRateService exchangeRateService;
	
	public ExchangeRateController(ExchangeRateService exchangeRateService) {
		this.exchangeRateService = exchangeRateService;
	}
	
	@GetMapping
	public BigDecimal getExchangeRate(@RequestParam String from, @RequestParam String to) {
		return exchangeRateService.getExchangeRate(from, to);
	}
	
	@PostMapping
	public void saveExchangeRate(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal rate) {
		exchangeRateService.saveExchangeRate(from, to, rate);
	}
}
