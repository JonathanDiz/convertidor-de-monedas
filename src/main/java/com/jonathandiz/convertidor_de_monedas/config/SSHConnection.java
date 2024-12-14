package com.jonathandiz.convertidor_de_monedas.config;

import jakarta.annotation.PostConstruct;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@Component
public class SSHConnection {

	private Session session;
	
	@PostConstruct
	public void connect() {
		try {
			String host = "remote-server-ip";
			int sshPort = 22;
			String user = "postgres";
			String privateKeyPath = System.getProperty("user.home") + "/.ssh/id_rsa";
		
			JSch jsch = new JSch();
			jsch.addIdentity(privateKeyPath);
			session = jsch.getSession(user, host, sshPort);
			
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			
			System.out.println("Conexión SSH establecida.");
			int localPort = 5432;
			int remotePort = 5432;
			session.setPortForwardingL(localPort, "localhost", remotePort);
			
		} catch (Exception e) {
			throw new RuntimeException("Error al establecer la conexión SSH", e);
		}
	}
	
	public void disconnect() {
		if (session != null && session.isConnected()) {
			session.disconnect();
			System.out.println("Conexión SSH cerrada.");
		}
	}
}
