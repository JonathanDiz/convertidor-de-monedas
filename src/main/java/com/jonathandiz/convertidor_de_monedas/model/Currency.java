package com.jonathandiz.convertidor_de_monedas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Currency {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private double exchangeRate;
	public void setName(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setExchangeRate(double d) {
		// TODO Auto-generated method stub
		
	}
}
