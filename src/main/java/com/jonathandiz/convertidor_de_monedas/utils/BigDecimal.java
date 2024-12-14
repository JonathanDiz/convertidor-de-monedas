package com.jonathandiz.convertidor_de_monedas.utils;

public record BigDecimal(CustomBigDecimal customBigDecimal) {
	
	// Constructor que acepta un String y crea un CustomBigDecimal
	public BigDecimal(String value) {
		this(new CustomBigDecimal(value));
	}
	
	// Constructor que acepta un double y crea un CustomBigDecimal
	public BigDecimal(double value) {
		this(new CustomBigDecimal(value));
	}
	
	// Delegar la suma a CustomBigDecimal
	public BigDecimal add(BigDecimal other) {
		return new BigDecimal(this.customBigDecimal.add(other.customBigDecimal));
	}
	
	// Delegar la resta a CustomBigdecimal
	public BigDecimal subtract(BigDecimal other) {
		return new BigDecimal(this.customBigDecimal.subtract(other.customBigDecimal));
	}
	
	// Delegar la multiplicación a CustomBigDecimal
	public BigDecimal multiply(BigDecimal other) {
		return new BigDecimal(this.customBigDecimal.multiply(other.customBigDecimal));
	}
	
	// Delegar la división a CustomBigDecimal
	public BigDecimal divide(BigDecimal other) {
		return new BigDecimal(this.customBigDecimal.divide(other.customBigDecimal, 0, null));
	}
	
	// Convertir a String usando el toString de CustomBigDecimal
	@Override
	public String toString() {
		return customBigDecimal.toString();
	}
}
