package com.jonathandiz.convertidor_de_monedas.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "exchange_rate_history")
public class ExchangeRateHistoryModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "source_curreny", nullable = false)
	private String sourceCurrency;
	
	@Column(name = "target_currency", nullable = false)
	private String targetCurrency;
	
	@Column(name = "exchange_rate", nullable = false)
	private Double exchangeRate;
	
	@Column(name = "timestamp", nullable = false)
	private LocalDateTime timestamp;
	
	public ExchangeRateHistoryModel(long l, String string, String string2, double d, LocalDateTime now) {
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSourceCurrency() {
		return sourceCurrency;
	}
	
	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}
	
	public String getTargetCurrency() {
		return targetCurrency;
	}
	
	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}
	
	public Double getExchangeRate() {
		return exchangeRate;
	}
	
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Object getRate() {
		// TODO Auto-generated method stub
		return null;
	}
}


