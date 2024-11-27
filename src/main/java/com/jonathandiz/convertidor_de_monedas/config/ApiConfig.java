package com.jonathandiz.convertidor_de_monedas.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

@Service
public class ApiConfig {
	private final Dotenv dotenv = Dotenv.load();
	
	public String getApiKey() {
		return dotenv.get("API_KEY");
	}
	
	public String getApiUrl() {
		return dotenv.get("API_URL");
	}
}
