package com.jonathandiz.convertidor_de_monedas.services;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.math.BigDecimal;

@Entity
public record ExchangeRate(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id,
	
	@Column(nullable = false, length = 3)
	String fromCurrency,
	
	@Column(nullable = false, length = 3)
	String toCurrency,
	
	@Column(nullable = false, precision = 19, scale = 4)
	BigDecimal rate
) {}
