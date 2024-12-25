package com.jonathandiz.convertidor_de_monedas.repository;

import com.jonathandiz.convertidor_de_monedas.entity.ExchangeRateHistoryEntity;
import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ExchangeRateHistoryRepository extends JpaRepository<ExchangeRateHistoryModel, Long> {
	
	// Encuentra las tasas de cambio por moneda de origen y destino
	List<ExchangeRateHistoryModel> findBySourceCurrencyAndTargetCurrency(String sourceCurrency, String targetCurrency);
	
	// Encuentra las tasas de cambio antes de una fecha específica
	List<ExchangeRateHistoryModel> findByTimestampBefore(LocalDateTime timestamp);

	ExchangeRateHistoryEntity save(com.jonathandiz.convertidor_de_monedas.entity.ExchangeRateHistoryEntity exchangeRateHistory);

	Object findRateByCurrencies(String fromCurrency, String toCurrency);
}
