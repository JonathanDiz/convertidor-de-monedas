package com.jonathandiz.convertidor_de_monedas.controllers;

import com.jonathandiz.convertidor_de_monedas.dto.ExchangeRateHistoryDto;
import com.jonathandiz.convertidor_de_monedas.model.CurrencyConversionRequest;
import com.jonathandiz.convertidor_de_monedas.services.CurrencyService;
import com.jonathandiz.convertidor_de_monedas.utils.CustomCurrency;
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
	public ResponseEntity<Object> getExchangeRate(@PathVariable Long id) {
		return ResponseEntity.ok(
				DtoConverter.toDTO(currencyService.getExchangeRateHistoryById(id))
		);
	}

	    @PostMapping("/convert")
	    public ResponseEntity<CustomCurrency> convertCurrency(@RequestBody CurrencyConversionRequest request) {
	        CustomCurrency result = currencyService.convert(request.getAmount(), request.getFromCurrency(), request.getToCurrency());
	        return ResponseEntity.ok(result);
	    }
	}
