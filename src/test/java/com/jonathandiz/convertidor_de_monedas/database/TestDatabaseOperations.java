package com.jonathandiz.convertidor_de_monedas.database;

import com.jonathandiz.convertidor_de_monedas.database.DatabaseOperations;
import java.sql.*;

public class TestDatabaseOperations {
	public void obtenerDatos() {
		String query = "SELECT * FROM usuarios";
		ResultSet resultSet = DatabaseOperations.executeSelectQuery(query);
	
		try {
			while (resultSet.next()) {
				String nombre = resultSet.getString("nombre");
				String email = resultSet.getString("email");
				System.out.println("Usuario: " + nombre + ", Email: " + email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseOperations.closeResultSet(resultSet);
		}
	}
	
	public void insertarUsuario(String nombre, String email) {
        String query = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
        Object[] params = {nombre, email};
        int rowsAffected = DatabaseOperations.executePreparedUpdateQuery(query, params);
        
        if (rowsAffected > 0) {
            System.out.println("Usuario insertado exitosamente.");
        } else {
            System.out.println("Error al insertar usuario.");
        }
    }
}
