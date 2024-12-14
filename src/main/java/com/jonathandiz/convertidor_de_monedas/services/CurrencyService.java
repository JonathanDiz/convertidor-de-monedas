package com.jonathandiz.convertidor_de_monedas.services;

import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistoryModel;
import com.jonathandiz.convertidor_de_monedas.repository.ExchangeRateHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
	
	private final TransactionService transactionService;
	private final ExchangeRateHistoryRepository repository;
	
	public CurrencyService(TransactionService transactionService, ExchangeRateHistoryRepository repository) {
		this.transactionService = transactionService;
		this.repository = repository;
	}
	
	public ExchangeRateHistoryModel saveHistory(ExchangeRateHistoryModel history) {
		return transactionService.executeWithTransaction(() -> repository.save(history));
	}

	public ExchangeRateHistoryModel getExchangeRateHistoryById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
