package com.jonathandiz.convertidor_de_monedas.exception;

public class CustomNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5748388028070872606L;

	public CustomNotFoundException(String message) {
		super(message);
	}
}
