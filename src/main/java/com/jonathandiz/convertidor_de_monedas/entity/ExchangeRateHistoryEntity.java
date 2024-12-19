package com.jonathandiz.convertidor_de_monedas.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.function.IntPredicate;

import com.jonathandiz.convertidor_de_monedas.utils.CustomCurrency;

@Entity
@Table(name = "exchange_rate_history")
public class ExchangeRateHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_from", nullable = false)
    private String currencyFrom;

    @Column(name = "currency_to", nullable = false)
    private String currencyTo;

    @Column(name = "rate", nullable = false)
    private CustomCurrency rate;

    @Column(name = "exchange_date", nullable = false)
    private LocalDateTime exchangeDate;

    // 🔥 Constructor vacío (requerido por JPA)
    public ExchangeRateHistoryEntity() {
    }

    // 🔥 Constructor con parámetros (útil para crear objetos de forma rápida)
    public ExchangeRateHistoryEntity(Long id, String currencyFrom, String currencyTo, CustomCurrency rate, LocalDateTime exchangeDate) {
        this.id = id;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
        this.exchangeDate = exchangeDate;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public CustomCurrency getRate() {
        return rate;
    }

    public void setRate(CustomCurrency rate) {
        this.rate = rate;
    }

    public LocalDateTime getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(LocalDateTime exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    // toString para depuración
    @Override
    public String toString() {
        return "ExchangeRateHistoryEntity{" +
                "id=" + id +
                ", currencyFrom='" + currencyFrom + '\'' +
                ", currencyTo='" + currencyTo + '\'' +
                ", rate=" + rate +
                ", exchangeDate=" + exchangeDate +
                '}';
    }

	public IntPredicate getSourceCurrency() {
		// TODO Auto-generated method stub
		return null;
	}

	public IntPredicate getTargetCurrency() {
		// TODO Auto-generated method stub
		return null;
	}

	public IntPredicate getExchangeRate() {
		// TODO Auto-generated method stub
		return null;
	}

	public IntPredicate getTimestamp() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFromCurrency(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setToCurrency(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setTimestamp(String string) {
		// TODO Auto-generated method stub
		
	}
}
