package com.jonathandiz.convertidor_de_monedas.repository;

import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateHistoryRepository extends JpaRepository<ExchangeRateHistory, Long> {
}
