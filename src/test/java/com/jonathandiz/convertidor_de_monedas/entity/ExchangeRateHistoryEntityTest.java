package com.jonathandiz.convertidor_de_monedas.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exchange_rate_history") // 🔥 Define la tabla asociada
public class ExchangeRateHistoryEntityTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 🔥 Estrategia para la generación automática de la clave primaria
    private Long id;

    @Column(name = "source_currency", nullable = false, length = 3) // 🔥 Define la columna y sus restricciones
    private String sourceCurrency;

    @Column(name = "target_currency", nullable = false, length = 3)
    private String targetCurrency;

    @Column(name = "exchange_rate", nullable = false)
    private Double exchangeRate;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    // 🔥 Constructor sin argumentos (necesario para JPA)
    public ExchangeRateHistoryEntityTest() {
    }

    // 🔥 Constructor con todos los argumentos
    public ExchangeRateHistoryEntityTest(Long id, String sourceCurrency, String targetCurrency, Double exchangeRate, LocalDateTime timestamp) {
        this.id = id;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
        this.timestamp = timestamp;
    }

    // Getters y setters (incluido 'id')
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

    // 🔥 toString para facilitar la depuración
    @Override
    public String toString() {
        return "ExchangeRateHistoryEntity{" +
                "id=" + id +
                ", sourceCurrency='" + sourceCurrency + '\'' +
                ", targetCurrency='" + targetCurrency + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", timestamp=" + timestamp +
                '}';
    }
}
