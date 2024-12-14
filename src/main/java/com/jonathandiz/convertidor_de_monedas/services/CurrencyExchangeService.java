package com.jonathandiz.convertidor_de_monedas.services;

import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistoryModel;
import com.jonathandiz.convertidor_de_monedas.repository.ExchangeRateHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurrencyExchangeService {
	
	private final ExchangeRateHistoryRepository exchangeRateHistoryRepository;
	
	public CurrencyExchangeService(ExchangeRateHistoryRepository exchangeRateHistoryRepository) {
		this.exchangeRateHistoryRepository = exchangeRateHistoryRepository;
	}
	
	@Transactional
	public ExchangeRateHistoryModel saveExchangeRateHistory(ExchangeRateHistoryModel history) {
		return exchangeRateHistoryRepository.save(history);
	}
	
	@Transactional(readOnly = true)
	public ExchangeRateHistoryModel getExchangeRateHistoryById(Long id) {
		return exchangeRateHistoryRepository.findById(id).orElseThrow(() -> 
				new IllegalArgumentException("Registro no encontrado con ID: " + id));
	}
}
