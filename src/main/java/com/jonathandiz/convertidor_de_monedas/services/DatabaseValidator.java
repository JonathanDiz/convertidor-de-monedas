package com.jonathandiz.convertidor_de_monedas.services;

import com.jonathandiz.convertidor_de_monedas.model.Currency;
import com.jonathandiz.convertidor_de_monedas.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabaseValidator {

	private final CurrencyRepository currencyRepository;
	
	public DatabaseValidator(CurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}
	
	public void validatePersistence() {
		Currency currency = new Currency();
		currency.setName("USD");
		currency.setExchangeRate(1.0);
		currencyRepository.save(currency);
		
		System.out.println("Datos guardados y recuperados: " + currencyRepository.findAll());
	}
}
