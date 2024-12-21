package com.jonathandiz.convertidor_de_monedas.ssh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jonathandiz.convertidor_de_monedas.config.AppProperties;

@Component
public class WhitelistLogger implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(WhitelistLogger.class);
	
	private final AppProperties appProperties;
	
	public WhitelistLogger(AppProperties appProperties) {
		this.appProperties = appProperties;
	}
	
	@Override
	public void run(String... args) {
		logger.info("Whitelist cargada correctamente: {}", appProperties.getWhitelist());
	}
}
