package com.jonathandiz.convertidor_de_monedas;

import com.jonathandiz.convertidor_de_monedas.config.EnvironmentDetector;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements ApplicationRunner {

	private final EnvironmentDetector environmentDetector;
	
	public AppInitializer(EnvironmentDetector environmentDetector) {
		this.environmentDetector = environmentDetector;
	}
	
	@Override
	public void run (ApplicationArguments args) {
		if (environmentDetector.isProduction()) {
			System.out.println("Aplicación iniciada en entorno de PRODUCCIÓN.");
		} else {
			System.out.println("Aplicación iniciada en entorno LOCAL.");
		}
	}
}
