package com.jonathandiz.convertidor_de_monedas.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Component;

@Component
public class IPDetector {

    /**
     * Obtiene la dirección IP local de la máquina.
     * 
     * @return La IP local de la máquina.
     */
    public String getLocalIPAddress() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("No se pudo detectar la dirección IP local", e);
        }
    }
}
