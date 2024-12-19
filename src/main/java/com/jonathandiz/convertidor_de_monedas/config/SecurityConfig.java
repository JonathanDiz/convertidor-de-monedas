package com.jonathandiz.convertidor_de_monedas.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/public/**").permitAll()  // Rutas públicas
                                .requestMatchers("/admin/**").hasRole("ADMIN") // Solo ADMIN
                                .requestMatchers("/user/**").hasRole("USER") // Solo USER
                                .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
                )
                .httpBasic(withDefaults()); // Usa autenticación básica
        
        return http.build();
    }
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user")
			.password(passwordEncoder().encode("password"))
			.roles("USER");
	}
}
