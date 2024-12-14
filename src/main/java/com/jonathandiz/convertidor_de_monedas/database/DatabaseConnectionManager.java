package com.jonathandiz.convertidor_de_monedas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
	private static final String LOCAL_URL = "jdbc:mysql://localhost:3306/registro_usuarios";
	private static final String RAILWAY_URL = System.getenv("DB_URL");
	private static final String LOCAL_USER = "jonathan";
	private static final String LOCAL_PASSWORD = System.getenv("DB_PASSWORD");
	private static final String RAILWAY_USER = System.getenv("DB_USER");
	private static final String RAILWAY_PASSWORD = System.getenv("DB_PASSWORD");

	private static Connection connection;
	
	/**
     * Intenta establecer la conexión con la base de datos en local o producción.
     * @return Una conexión persistente.
     * @throws SQLException Si no puede conectarse a ninguna base de datos.
     */
	public static Connection getconnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			return connection; // Retorna la conexión existente si es válida.
		}
		
		String url = isLocalEnvironment() ? LOCAL_URL : RAILWAY_URL;
		String user = isLocalEnvironment() ? LOCAL_USER : RAILWAY_USER;
		String password = isLocalEnvironment() ? LOCAL_PASSWORD : RAILWAY_PASSWORD;
	
		connection = validateConnection(url, user, password);
		return connection;
	}
	
	/**
     * Verifica si estamos en un entorno local o de producción.
     * @return true si estamos en un entorno local; false si es producción.
     */
	private static boolean isLocalEnvironment() {
		String host = System.getenv("HOSTNAME");
		return host == null || host.contains("localhost");
	}
	
	/**
     * Valida las credenciales y establece la conexión.
     * @param url URL de la base de datos.
     * @param user Usuario de la base de datos.
     * @param password Contraseña de la base de datos.
     * @return Una conexión válida.
     * @throws SQLException Si la conexión no puede establecerse.
     */
	private static Connection validateConnection(String url, String user, String password) throws SQLException {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a: " + url);
			return conn;
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos: " + e.getMessage());
			throw new SQLException("No se pudo establecer la conexión con la base de datos.");
		}
	}
}
