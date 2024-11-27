package com.jonathandiz.convertidor_de_monedas.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ExchangeRateHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate date;
	private String currency;
	private double rate;
	private double difference;
	
	// Getters y Setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public LocalDate getDate() { return date; }
	public void setDate(LocalDate date) { this.date = date; }
	
	public String getCurrency() { return currency; }
	public void setCurrency(String currency) { this.currency = currency; }
	
	public double getRate() { return rate; }
	public void setRate(double rate) { this.rate = rate; }
	
	public double getDifference() { return difference; }
	public void setDifference(double difference) { this.difference = difference; }
}
