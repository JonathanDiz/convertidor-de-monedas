package com.jonathandiz.convertidor_de_monedas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.jonathandiz.convertidor_de_monedas")
@EnableJpaRepositories(basePackages = "com.jonathandiz.convertidor_de_monedas.entity")
@EntityScan(basePackages = "com.jonathandiz.convertidor_de_monedas.model")
public class ConvertidorDeMonedasApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConvertidorDeMonedasApplication.class, args);
    }
}
