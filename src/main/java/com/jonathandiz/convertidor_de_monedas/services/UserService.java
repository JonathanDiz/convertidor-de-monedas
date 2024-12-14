package com.jonathandiz.convertidor_de_monedas.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jonathandiz.convertidor_de_monedas.database.DatabaseConnectionManager;

public class UserService {
	
	public void fetchUsers() {
		try (Connection connection = DatabaseConnectionManager.getconnection();
				Statement statement = connection.createStatement()) {
			
			String query = "SELECT * FROM usuarios";
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				System.out.println("Usuario: " + resultSet.getString("nombre"));
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener usuarios: " + e.getMessage());
		}
	}
}
