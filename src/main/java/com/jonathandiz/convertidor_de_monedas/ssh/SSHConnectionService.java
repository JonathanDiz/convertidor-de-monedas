package com.jonathandiz.convertidor_de_monedas.ssh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SSHConnectionService {

	@Autowired
	private SSHWhitelistValidator whitelistValidator;
	
	public void connect(String clientIP) {
		if (whitelistValidator.isAllowed(clientIP)) {
			System.out.println("✅ Conexión permitida para IP: " + clientIP);
			// Lógica para establecer la conexión SSH
		} else {
			System.err.println("⛔ Conexión rechazada para IP: " + clientIP);
			throw new SecurityException("IP no permitida: " + clientIP);
		}
	}
}
