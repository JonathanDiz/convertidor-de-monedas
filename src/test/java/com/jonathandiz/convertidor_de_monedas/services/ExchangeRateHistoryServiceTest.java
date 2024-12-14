package com.jonathandiz.convertidor_de_monedas.services;

import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistoryModel;
import com.jonathandiz.convertidor_de_monedas.repository.ExchangeRateHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class) // Usar Mockito en pruebas unitarias
public class ExchangeRateHistoryServiceTest {

    @Mock
    private ExchangeRateHistoryRepository exchangeRateHistoryRepository;

    @InjectMocks
    private ExchangeRateHistoryService exchangeRateHistoryService;

    @Test
    public void testBuscarExchangeRateHistoryPorId() {
        // 1️⃣ Preparación de los datos
        LocalDateTime now = LocalDateTime.now();
        
        ExchangeRateHistoryModel exchangeRateHistory = new ExchangeRateHistoryModel(
                1L, 
                "USD", 
                "CLP", 
                900.50, 
                now
        );

        // Simulación de la respuesta del repositorio
        when(exchangeRateHistoryRepository.findById(1L))
            .thenReturn(Optional.of(exchangeRateHistory));

        // 2️⃣ Ejecución del método de servicio
        Optional<ExchangeRateHistoryModel> resultado = Optional.empty();

        // 3️⃣ Validación de la respuesta
        assertThat(resultado).isPresent(); // Verifica que el Optional no esté vacío

        // Validación encadenada de los campos dentro del Optional
        ExchangeRateHistoryModel fetchedEntity = resultado.orElseThrow();
        assertThat(fetchedEntity.getId()).isEqualTo(1L);
        assertThat(fetchedEntity.getSourceCurrency()).isEqualTo("USD");
        assertThat(fetchedEntity.getTargetCurrency()).isEqualTo("CLP");
        assertThat(fetchedEntity.getExchangeRate()).isEqualTo(900.50);
        assertThat(fetchedEntity.getTimestamp()).isEqualToIgnoringNanos(now); // Evita problemas de precisión con nanosegundos

        // Verificar que se llamó al método findById() del repositorio
        verify(exchangeRateHistoryRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(exchangeRateHistoryRepository); // Verificar que no haya más interacciones
    }
}
