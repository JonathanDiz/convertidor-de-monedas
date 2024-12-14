package com.jonathandiz.convertidor_de_monedas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentDetector {

    private final String serverUrl;
    private boolean isProduction;

    // Constructor para inyectar el valor de serverUrl
    public EnvironmentDetector(@Value("${server.url:http://localhost:8080}") String serverUrl) {
        this.serverUrl = serverUrl;
        detectEnvironment(serverUrl);
    }

    // Método para detectar el entorno basado en la URL del servidor
    void detectEnvironment(String serverUrl2) {
        if (serverUrl == null || serverUrl.isEmpty()) {
            throw new IllegalStateException("La URL del servidor no está configurada. Verifica tu configuración.");
        }

        if (serverUrl.contains("localhost")) {
            isProduction = false;
            System.out.println("Entorno detectado: LOCAL");
        } else if (serverUrl.contains("railway.app")) {
            isProduction = true;
            System.out.println("Entorno detectado: PRODUCCIÓN");
        } else {
            System.out.println("Advertencia: No se pudo determinar el entorno. Configuración desconocida.");
            isProduction = false; // Asume LOCAL si no se puede determinar
        }
    }

    // Métodos para obtener el estado del entorno
    public boolean isProduction() {
        return isProduction;
    }

    public boolean isLocal() {
        return !isProduction;
    }
}
