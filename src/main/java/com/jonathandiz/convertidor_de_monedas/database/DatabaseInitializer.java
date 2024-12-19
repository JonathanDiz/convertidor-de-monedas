package com.jonathandiz.convertidor_de_monedas.database;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DatabaseInitializer {

	private final DatabaseProperties databaseProperties;
	
	public DatabaseInitializer(DatabaseProperties databaseProperties) {
		this.databaseProperties = databaseProperties;
	}
	
	@PostConstruct
	public void initializeDatabase() {
		try (Connection connection = DriverManager.getConnection(
				databaseProperties.getUrl(),
				databaseProperties.getUsername(),
				databaseProperties.getPassword()
				)) {
			try (Statement statement = connection.createStatement()) {
				String createTableSQL = """
                        CREATE TABLE IF NOT EXISTS monedas (
                            id SERIAL PRIMARY KEY,
                            codigo VARCHAR(3) NOT NULL UNIQUE,
                            nombre VARCHAR(100) NOT NULL,
                            simbolo VARCHAR(10) NOT NULL,
                            tasa_conversion DECIMAL(18, 8) NOT NULL
                        );
                    """;
				
				statement.execute(createTableSQL);
				
				System.out.println("Base de datos inicializada correctamente.");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al inicializar la base de datos.", e);
		}
	}
}
