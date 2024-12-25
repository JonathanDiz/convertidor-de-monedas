package com.jonathandiz.convertidor_de_monedas.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository {

	/**
     * Encuentra la tasa de cambio entre dos monedas.
     *
     * @param fromCurrency Código de la moneda de origen (por ejemplo, "USD").
     * @param toCurrency   Código de la moneda de destino (por ejemplo, "EUR").
     * @return Una tasa de cambio como BigDecimal, o un Optional vacío si no se encuentra.
     */
    @Query("SELECT e.rate FROM ExchangeRateEntity e WHERE e.fromCurrency = :fromCurrency AND e.toCurrency = :toCurrency")
    Optional<BigDecimal> findRateByCurrencies(
            @Param("fromCurrency") String fromCurrency,
            @Param("toCurrency") String toCurrency);
}

