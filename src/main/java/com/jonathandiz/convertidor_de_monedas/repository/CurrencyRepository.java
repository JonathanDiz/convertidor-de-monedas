package com.jonathandiz.convertidor_de_monedas.repository;

import com.jonathandiz.convertidor_de_monedas.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long>{
}
