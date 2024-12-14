package com.jonathandiz.convertidor_de_monedas.services;

import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistoryModel;

@Service
public class TransactionService {
	
	@Transactional
	public ExchangeRateHistoryModel executeWithTransaction(Runnable action) {
		action.run();
		return null;
	}
	
	@Transactional(readOnly = true)
	public <T> T fetchData(Supplier<T> action) {
		return action.get();
	}
}
