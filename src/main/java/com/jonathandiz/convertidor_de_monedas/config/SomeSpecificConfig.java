package com.jonathandiz.convertidor_de_monedas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SomeSpecificConfig {
	
	// Configuración de DataSource para pruebas (usando H2)
	@Bean(name = "someSpecificDataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:testdb");
		dataSource.setUsername("sa");
		dataSource.setPassword("password");
		return new DriverManagerDataSource();
	}
}
