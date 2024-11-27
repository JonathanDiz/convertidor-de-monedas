package com.jonathandiz.convertidor_de_monedas.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jonathandiz.convertidor_de_monedas.config.ApiConfig;

@Service
public class CurrencyService {
	
	private final ApiConfig apiConfig;
	private final RestTemplate restTemplate;
	
	public CurrencyService(ApiConfig apiConfig, RestTemplate restTemplate) {
		this.apiConfig = apiConfig;
		this.restTemplate = restTemplate;
	}
	
	public Object getExchangeRates() {
		String url = apiConfig.getApiUrl();
		return restTemplate.getForObject(url, Object.class);
	}
}
