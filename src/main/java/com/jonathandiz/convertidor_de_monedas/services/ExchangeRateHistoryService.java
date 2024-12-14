package com.jonathandiz.convertidor_de_monedas.services;

import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistoryModel;
import com.jonathandiz.convertidor_de_monedas.repository.ExchangeRateHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRateHistoryService {

    private final ExchangeRateHistoryRepository repository;

    public ExchangeRateHistoryService(ExchangeRateHistoryRepository repository) {
        this.repository = repository;
    }

    public List<ExchangeRateHistoryModel> getRates(String sourceCurrency, String targetCurrency) {
        return repository.findBySourceCurrencyAndTargetCurrency(sourceCurrency, targetCurrency);
    }

    public void saveRate(ExchangeRateHistoryModel rate) {
        repository.save(rate);
    }

    public List<ExchangeRateHistoryModel> getRatesBefore(LocalDateTime timestamp) {
        return repository.findByTimestampBefore(timestamp);
    }

	public com.jonathandiz.convertidor_de_monedas.entity.ExchangeRateHistoryEntity saveExchangeRateHistory(
			com.jonathandiz.convertidor_de_monedas.entity.ExchangeRateHistoryEntity exchangeRateHistory) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<com.jonathandiz.convertidor_de_monedas.entity.ExchangeRateHistoryEntity> findById(long l) {
		// TODO Auto-generated method stub
		return null;
	}
}
