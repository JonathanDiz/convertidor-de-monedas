package com.jonathandiz.convertidor_de_monedas.database;

import java.sql.*;

public class DatabaseOperations {

	// Método para ejecutar una consulta SELECT
	public static ResultSet executeSelectQuery(String query) {
		ResultSet resultSet = null;
		Connection connection = DatabaseConnection.getConnection();
		
		try (Statement statement = connection.createStatement()) {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error ejecutando consulta SELECT", e);
		}
		
		return resultSet;
	}
	
	// Método para ejecutar una consulta INSET, UPDATE o DELETE
	public static int executeUpdateQuery(String query) {
		int rowsAffected = 0;
		Connection connection = DatabaseConnection.getConnection();
		
		try (Statement statement = connection.createStatement()) {
			rowsAffected = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error ejecutando consulta UPDATE/INSERT/DELETE", e);
		}
		
		return rowsAffected;
	}
	
	// Método para ejecutar una consulta preparada con parámetros
	public static int executePreparedUpdateQuery(String query, Object[] params) {
		int rowsAffected = 0;
		Connection connection = DatabaseConnection.getConnection();
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			// Asignamos los parámetros de la consulta preparada
			for (int i = 0; i < params.length; i++) {
				preparedStatement.setObject(i + 1, params[i]);
			}
			rowsAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error ejecutando consulta preparada", e);
		}
		
		return rowsAffected;
	}
	
	// Método para cerrar el ResultSet y evitar fugas de recursos
	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
