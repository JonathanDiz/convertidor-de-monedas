package com.jonathandiz.convertidor_de_monedas.database;

import com.jonathandiz.convertidor_de_monedas.config.TestDatabaseConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfig.class})
public class TestDatabaseOperations {

	@BeforeEach
	public void setUp() {
		String createTableQuery = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id SERIAL PRIMARY KEY, " +
                "nombre VARCHAR(100), " +
                "email VARCHAR(100)" +
                ")";
        DatabaseOperations.executePreparedUpdateQuery(createTableQuery, new Object[]{});
    }
	
	/**
     * Obtiene los datos de la tabla 'usuarios' e imprime cada registro.
     */
    @Test
    public void testObtenerDatos() {
    	String query = "SELECT * FROM usuarios";
    	
    	ResultSet resultSet = DatabaseOperations.executeSelectQuery(query);
    
    	try {
    		while (resultSet.next()) {
    			String nombre = resultSet.getString("nombre");
    			String email = resultSet.getString("email");
    			System.out.println("Usuario: " + nombre + ", Email: " + email);
    			assertNotNull(nombre);
    			assertNotNull(email);
    		}
    	} catch (SQLException e) {
    		System.err.println("Error al obtener los datos de la base de datos.");
    		e.printStackTrace();
    	} finally {
    		DatabaseOperations.closeResultSet(resultSet);
    	}
    }
    
    /**
     * Inserta un nuevo usuario en la tabla 'usuarios'.
     * @param nombre Nombre del usuario.
     * @param email Correo electrónico del usuario.
     */
    @Test
    public void testInsertarUsuario() {
    	String query = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
        Object[] params = {"Jonathan", "jonathan@example.com"};
        int rowsAffected = DatabaseOperations.executePreparedUpdateQuery(query, params);
        
        if (rowsAffected > 0) {
        	System.out.println("Usuario insertado exitosamente.");
        } else {
        	System.err.println("Error al insertar usuario.");
        }
        
        // Verifica que el usuario se haya insertado
        String selectQuery = "SELECT * FROM usuarios WHERE nombre = ? AND email = ?";
        Object[] selectParams = {"Jonathan", "jonathan@example.com"};
        ResultSet resultSet = DatabaseOperations.executePreparedSelectQuery(selectQuery, selectParams);
    
        try {
        	if (resultSet.next()) {
        		String nombre = resultSet.getString("nombre");
        		String email = resultSet.getString("email");
        		assertNotNull(nombre);
        		assertNotNull(email);
        	}
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	DatabaseOperations.closeResultSet(resultSet);
        }
    }
}
