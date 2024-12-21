package com.jonathandiz.convertidor_de_monedas.ssh;

public class SSHApplication {

	public static void main(String[] args) {
		SSHConnectionService connectionService = new SSHConnectionService();
		String clientIP = "192.168.1.10";
		
		try {
			connectionService.connect(clientIP);
		} catch (SecurityException e) {
			System.err.println("❌ Conexión denegada: " + e.getMessage());
		}
	}
}
