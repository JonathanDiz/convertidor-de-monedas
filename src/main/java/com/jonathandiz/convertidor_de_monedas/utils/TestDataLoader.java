package com.jonathandiz.convertidor_de_monedas.utils;

import com.jonathandiz.convertidor_de_monedas.entity.ExchangeRateHistoryEntity;
import com.jonathandiz.convertidor_de_monedas.repository.ExchangeRateHistoryRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class TestDataLoader {

    private final ExchangeRateHistoryRepository repository;

    public TestDataLoader(ExchangeRateHistoryRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void loadTestData() {
        ExchangeRateHistoryEntity history = new ExchangeRateHistoryEntity();
        history.setFromCurrency("USD");
        history.setToCurrency("CLP");

        // Usar el nuevo BigDecimal
        CustomCurrency rate = new CustomCurrency(0, "875.50");
        history.setRate(rate);

        history.setTimestamp("2024-12-14T10:00:00");

        // Guardar el historial de tasas
        repository.save(history);
    }
}
