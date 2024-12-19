package com.jonathandiz.convertidor_de_monedas.utils;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;

@Component
public class DatabaseInitializer {

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.name}")
    private String dbName;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.password}")
    private String dbPassword;

    @PostConstruct
    public void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement statement = connection.createStatement()) {

            String checkDbQuery = "SELECT 1 FROM pg_database WHERE datname = '" + dbName + "'";
            var resultSet = statement.executeQuery(checkDbQuery);
            
            if (!resultSet.next()) {
                System.out.println("La base de datos no existe. Creándola...");
                String createDbQuery = "CREATE DATABASE " + dbName;
                statement.executeUpdate(createDbQuery);
                System.out.println("Base de datos '" + dbName + "' creada exitosamente.");
            } else {
                System.out.println("La base de datos '" + dbName + "' ya existe.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al crear la base de datos: " + e.getMessage());
        }
    }
}

