package com.jonathandiz.convertidor_de_monedas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static Connection connection = null;
	
	private DatabaseConnection() {
		
	}
	
	// Método para obtener la conexión a la base de datos
	public static Connection getConnection() {
		if (connection == null) {
			try {
				String url = System.getenv("DB_URL");
				String username = System.getenv("DB_USER");
				String password = System.getenv("DB_PASSWORD");
				
				// Si no se puede recuperar la URL desde las variables de entorno
				if (url == null || username == null || password == null) {
					throw new SQLException("Faltan variables de entorno para la conexión.");
				}
				
				// Realiza la conexión a la base de datos
				connection = DriverManager.getConnection(url, username, password);
				System.out.println("Conexión exitosa a la base de datos");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Error al conectar con la base de datos", e);
			}
		}
		return connection;
	}
	
	// Método para cerrar la conexón a la base de datos
	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Conexión cerrada");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
