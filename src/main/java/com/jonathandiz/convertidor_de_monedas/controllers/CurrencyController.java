package com.jonathandiz.convertidor_de_monedas.controllers;

import com.jonathandiz.convertidor_de_monedas.dto.ExchangeRateHistoryDto;
import com.jonathandiz.convertidor_de_monedas.services.CurrencyService;
import com.jonathandiz.convertidor_de_monedas.utils.DtoConverter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exchange")
public class CurrencyController {
	
	private final CurrencyService currencyService;
	
	public CurrencyController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ExchangeRateHistoryDto> getExchangeRate(@PathVariable Long id) {
		return ResponseEntity.ok(
				DtoConverter.toDTO(currencyService.getExchangeRateHistoryById(id))
		);
	}
}
