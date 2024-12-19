package com.jonathandiz.convertidor_de_monedas.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvironmentConfig {

    boolean isLocalEnvironment() {
		String host = System.getenv("HOSTNAME");
		return host == null || host.contains("localhost");
	}
}
