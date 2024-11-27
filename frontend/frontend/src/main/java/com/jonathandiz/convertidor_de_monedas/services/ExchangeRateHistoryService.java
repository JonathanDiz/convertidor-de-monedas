package com.jonathandiz.convertidor_de_monedas.services;

import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistory;
import com.jonathandiz.convertidor_de_monedas.repository.ExchangeRateHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateHistoryService {

    private final ExchangeRateHistoryRepository repository;

    public ExchangeRateHistoryService(ExchangeRateHistoryRepository repository) {
        this.repository = repository;
    }

    public ExchangeRateHistory saveHistory(ExchangeRateHistory history) {
        return repository.save(history);
    }

    public List<ExchangeRateHistory> getAllHistory() {
        return repository.findAll();
    }
}

