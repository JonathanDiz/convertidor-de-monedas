package com.jonathandiz.convertidor_de_monedas.repository;

import com.jonathandiz.convertidor_de_monedas.entity.ExchangeRateHistoryEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
public class ExchangeRateHistoryRepositoryTest {

    @Autowired
    private ExchangeRateHistoryRepository exchangeRateHistoryRepository;

    @AfterEach
    void limpiarBaseDeDatos() {
        exchangeRateHistoryRepository.deleteAll(); // Limpia la base de datos después de cada prueba
    }

    @Test
    public void testGuardarYBuscarEntidad() {
        // 1️⃣ Preparar la entidad de prueba
        LocalDateTime now = LocalDateTime.now();
        ExchangeRateHistoryEntity exchangeRateHistory = new ExchangeRateHistoryEntity(
        );

        // 2️⃣ Guardar la entidad en la base de datos
        ExchangeRateHistoryEntity savedEntity = exchangeRateHistoryRepository.save(exchangeRateHistory);

        // 3️⃣ Consultar la entidad por su ID
        Optional<ExchangeRateHistoryEntity> resultado = Optional.empty();

        // 4️⃣ Validar la respuesta
        assertThat(resultado).isPresent();
        ExchangeRateHistoryEntity fetchedEntity = resultado.get();
        
        assertThat(fetchedEntity.getId()).isEqualTo(savedEntity.getId());
        assertThat(fetchedEntity.getSourceCurrency()).isEqualTo("USD");
        assertThat(fetchedEntity.getTargetCurrency()).isEqualTo("CLP");
        assertThat(fetchedEntity.getExchangeRate()).isEqualTo(900.50);
        assertThat(fetchedEntity.getTimestamp());
    }
}
