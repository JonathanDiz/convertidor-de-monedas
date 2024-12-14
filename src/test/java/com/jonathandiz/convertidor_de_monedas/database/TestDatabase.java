package com.jonathandiz.convertidor_de_monedas.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@SpringBootTest
@Component
public class TestDatabase implements CommandLineRunner {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		String sql = "SELECT 1";
		Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
		if (result != null && result == 1) {
			System.out.println("Conexión a la base de datos exitosa.");
		} else {
			System.out.println("Error al conectar con la base de datos.");
		}
	}
}
