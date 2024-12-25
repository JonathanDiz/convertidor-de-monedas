package com.jonathandiz.convertidor_de_monedas.model;

import java.math.BigDecimal;

public class CurrencyConversionRequest {
	private BigDecimal amount;
	private String fromCurrency;
	private String toCurrency;
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public String getFromCurrency() {
		return fromCurrency;
	}
	
	public String getToCurrency() {
		return toCurrency;
	}
}
