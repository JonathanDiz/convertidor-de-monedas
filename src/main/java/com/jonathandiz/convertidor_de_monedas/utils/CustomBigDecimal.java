package com.jonathandiz.convertidor_de_monedas.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record CustomBigDecimal(BigDecimal value) {

	public CustomBigDecimal(String value) {
		this(new BigDecimal(value));
	}
	
	public CustomBigDecimal(double value) {
		this(BigDecimal.valueOf(value));
	}
	
	public CustomBigDecimal add(CustomBigDecimal other) {
		return new CustomBigDecimal(this.value.add(other.value));
	}
	
	public CustomBigDecimal subtract(CustomBigDecimal other) {
		return new CustomBigDecimal(this.value.subtract(other.value));
	}
	
	public CustomBigDecimal multiply(CustomBigDecimal other) {
		return new CustomBigDecimal(this.value.multiply(other.value));
	}
	
	public CustomBigDecimal divide(CustomBigDecimal other, int scale, RoundingMode roundingMode) {
		return new CustomBigDecimal(this.value.divide(other.value, scale, roundingMode));
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}
