package com.jonathandiz.convertidor_de_monedas.controller;

import com.jonathandiz.convertidor_de_monedas.controllers.ExchangeRateHistoryController;
import com.jonathandiz.convertidor_de_monedas.entity.ExchangeRateHistoryEntity;
import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistoryModel;
import com.jonathandiz.convertidor_de_monedas.services.ExchangeRateHistoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(ExchangeRateHistoryController.class) // Carga solo el controlador, no todo el contexto de Spring
public class ExchangeRateHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc; // Inyecta el MockMvc para pruebas web

    @MockitoBean
    private ExchangeRateHistoryService exchangeRateHistoryService; // Mofifica el servicio para que no se llame realmente

    @Test
    public void testObtenerExchangeRateHistory() throws Exception {
        // 1️⃣ Simular la respuesta del servicio
        ExchangeRateHistoryModel exchangeRateHistory = new ExchangeRateHistoryModel(
                1L, 
                "USD", 
                "CLP", 
                900.50, 
                LocalDateTime.now()
        );

        // 2️⃣ Simular la respuesta de findById del servicio
        when(exchangeRateHistoryService.findById(1L));

        // 3️⃣ Realizar la solicitud GET y verificar la respuesta
        mockMvc.perform(get("/exchange-rate-history/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "id": 1,
                        "sourceCurrency": "USD",
                        "targetCurrency": "CLP",
                        "exchangeRate": 900.50
                    }
                """, true)); // El parámetro "true" verifica que no existan campos adicionales
    }
}
