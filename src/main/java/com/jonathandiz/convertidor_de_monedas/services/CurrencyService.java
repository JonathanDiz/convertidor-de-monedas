package com.jonathandiz.convertidor_de_monedas.services;

import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistoryModel;
import com.jonathandiz.convertidor_de_monedas.repository.ExchangeRateHistoryRepository;
import com.jonathandiz.convertidor_de_monedas.repository.ExchangeRateRepository;
import com.jonathandiz.convertidor_de_monedas.utils.CustomCurrency;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    private final TransactionService transactionService;
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateHistoryRepository exchangeRateHistoryRepository;

    // Constructor para inyección de dependencias
    public CurrencyService(
            TransactionService transactionService,
            ExchangeRateRepository exchangeRateRepository,
            ExchangeRateHistoryRepository exchangeRateHistoryRepository) {
        this.transactionService = transactionService;
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateHistoryRepository = exchangeRateHistoryRepository;
    }

    // Método para convertir monedas
    public CustomCurrency convert(BigDecimal amount, String fromCurrency, String toCurrency) {
        BigDecimal exchangeRate = exchangeRateRepository.findRateByCurrencies(fromCurrency, toCurrency)
                .orElseThrow(() -> new IllegalArgumentException("Tasa de cambio no encontrada para: " 
                        + fromCurrency + " a " + toCurrency));
        return new CustomCurrency(amount.multiply(exchangeRate), toCurrency);
    }

    // Guardar historial de tasas de cambio
    public ExchangeRateHistoryModel saveHistory(ExchangeRateHistoryModel history) {
        return transactionService.executeWithTransaction(() -> exchangeRateHistoryRepository.save(history));
    }

    // Obtener historial por ID
    public Optional<ExchangeRateHistoryModel> getExchangeRateHistoryById(Long id) {
        return exchangeRateHistoryRepository.findById(id);
    }
}
