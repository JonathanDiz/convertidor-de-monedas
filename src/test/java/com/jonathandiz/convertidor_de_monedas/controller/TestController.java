package com.jonathandiz.convertidor_de_monedas.controller;

import com.jonathandiz.convertidor_de_monedas.services.CurrencyExchangeService;
import com.jonathandiz.convertidor_de_monedas.controllers.ExchangeRateHistoryController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TestController {
	
	private MockMvc mockMvc;
	
	@Mock
	private CurrencyExchangeService currencyExchangeService;
	
	@InjectMocks
	private ExchangeRateHistoryController exchangeRateHistoryController;
	
	@Test
	public void testGetExample() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(exchangeRateHistoryController).build();
	
		mockMvc.perform(get("/api/rates"))
			.andExpect(status().isOk());
	}
}
