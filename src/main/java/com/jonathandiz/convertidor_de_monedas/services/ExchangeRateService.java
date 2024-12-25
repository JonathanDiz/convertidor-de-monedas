package com.jonathandiz.convertidor_de_monedas.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ExchangeRateService {
	
	private final ExchangeRateRepository exchangeRateRepository;
	
	public ExchangeRateService(ExchangeRateRepository exchangeRateRepository) {
		this.exchangeRateRepository = exchangeRateRepository;
	}
	
	public BigDecimal getExchangeRate(String fromCurrency, String toCurrency) {
		Optional<ExchangeRate> rate = exchangeRateRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
		return rate.map(ExchangeRate::rate).orElseThrow(() ->
			new IllegalArgumentException("Exchange rate not found for: " + fromCurrency + " to " + toCurrency));
	}
	
	public ExchangeRate saveExchangeRate(String fromCurrency, String toCurrency, BigDecimal rate) {
		ExchangeRate exchangeRate = new ExchangeRate(null, fromCurrency, toCurrency, rate);
		return exchangeRateRepository.save(exchangeRate);
	}
}
