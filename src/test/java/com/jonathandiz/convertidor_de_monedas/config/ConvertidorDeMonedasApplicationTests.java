package com.jonathandiz.convertidor_de_monedas.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.jonathandiz.convertidor_de_monedas.config.SomeSpecificConfig;

@SpringBootTest
@ContextConfiguration(classes = {SomeSpecificConfig.class})

class ConvertidorDeMonedasApplicationTests {

	@Test
	void contextLoads() {
	}

}
