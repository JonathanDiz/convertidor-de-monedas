package com.jonathandiz.convertidor_de_monedas.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SSHServerManager {

	 /**
     * Verifica si el servidor SSH está corriendo.
     * @return true si el servidor está activo, false en caso contrario.
     */
	public boolean idSSHServerRunning() {
		try {
			Process process = Runtime.getRuntime().exec("systemctl is-active ssh");
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				String status = reader.readLine();
				return "active".equalsIgnoreCase(status);
			}
		} catch (IOException e) {
			throw new RuntimeException("Error al verificar el estado del servidor SSH", e);
		}
	}
	
	/**
     * Inicia el servidor SSH.
     */
	public void startSSHServer() {
		if (!idSSHServerRunning()) {
			try {
				System.out.println("Iniciando servidor SSH...");
				Process process = Runtime.getRuntime().exec("sudo systemctl start ssh");
				int exitCode = process.waitFor();
				if (exitCode == 0) {
					System.out.println("Servidor SSH iniciado correctamente.");
				} else {
					throw new RuntimeException("Error al iniciar el servidor SSH. Código de salida: " + exitCode);
				}
			} catch (IOException | InterruptedException e) {
				throw new RuntimeException("Error al iniciar el servidor SSH", e);
			}
		} else {
			System.out.println("El servidor SSH ya está activo.");
		}
	}
}
