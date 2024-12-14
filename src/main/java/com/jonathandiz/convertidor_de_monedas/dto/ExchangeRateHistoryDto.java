package com.jonathandiz.convertidor_de_monedas.dto;

import java.math.BigDecimal;

public class ExchangeRateHistoryDto {
    private Long id;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal rate;
    private String timestamp;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFromCurrency() { return fromCurrency; }
    public void setFromCurrency(String fromCurrency) { this.fromCurrency = fromCurrency; }
    public String getToCurrency() { return toCurrency; }
    public void setToCurrency(String toCurrency) { this.toCurrency = toCurrency; }
    public BigDecimal getRate() { return rate; }
    public void setRate(Object object) { this.rate = (BigDecimal) object; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
