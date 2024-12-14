package com.jonathandiz.convertidor_de_monedas.utils;

import java.sql.*;

public class DatabaseOperations {

	public static ResultSet executeSelectQuery(String query) {
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement()) {
				return statement.executeQuery(query);
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta SELECT", e);
		}
	}
	
	public static int executePreparedUpdateQuery(String query, Object[] params) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				for (int i = 0; i < params.length; i++) {			
			}
				return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta UPDATE preparada", e);
		}
	}
	
	private static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "password");
		} catch (SQLException e) {
			throw new RuntimeException("Error al conectar a la base de datos H2", e);
		}
	}
	
}
