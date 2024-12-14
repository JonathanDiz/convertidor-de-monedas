package com.jonathandiz.convertidor_de_monedas.services;

import com.jonathandiz.convertidor_de_monedas.utils.DatabaseOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
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

    @Test
    public void testObtenerDatos() {
        String query = "SELECT * FROM usuarios";
        ResultSet resultSet = DatabaseOperations.executeSelectQuery(query);
        try {
            while (resultSet.next()) {
                assertNotNull(resultSet.getString("nombre"));
                assertNotNull(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInsertarUsuario() {
        String query = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
        Object[] params = {"Jonathan", "jonathan@example.com"};
        int rowsAffected = DatabaseOperations.executePreparedUpdateQuery(query, params);
        assertNotNull(rowsAffected);
    }

    @Test
    public void testActualizarUsuario() {
        String insertQuery = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
        Object[] params = {"Jonathan", "jonathan@example.com"};
        DatabaseOperations.executePreparedUpdateQuery(insertQuery, params);

        String updateQuery = "UPDATE usuarios SET nombre = ? WHERE email = ?";
        Object[] updateParams = {"Jonathan Updated", "jonathan@example.com"};
        int rowsAffected = DatabaseOperations.executePreparedUpdateQuery(updateQuery, updateParams);
        assertNotNull(rowsAffected);
    }
}
