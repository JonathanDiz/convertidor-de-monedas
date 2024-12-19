package com.jonathandiz.convertidor_de_monedas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	private final EnvironmentDetector environmentDetector;

    @Value("${server.url:localhost}")
    private String serverUrl;

    @Value("${DATABASE_URL:#{null}}")
    private String databaseUrl;

    @Value("${MYSQL_USER:#{null}}")
    private String mysqlUser;

    @Value("${MYSQL_PASSWORD:#{null}}")
    private String mysqlPassword;

    public AppConfig(EnvironmentDetector environmentDetector) {
        this.environmentDetector = environmentDetector;
        this.environmentDetector.detectEnvironment(serverUrl);

        configureDatabase();
    }

    private void configureDatabase() {
        if (environmentDetector.isProduction()) {
            System.out.println("Configurando base de datos para PRODUCCIÓN...");
            System.out.println("URL: " + databaseUrl);
            System.out.println("Usuario: " + mysqlUser);
        } else {
            System.out.println("Configurando base de datos para LOCAL...");
            System.out.println("URL: localhost");
            System.out.println("Usuario: root");
        }
    }

    RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
}
