package com.jonathandiz.convertidor_de_monedas.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Component;

@Component
public class SSHConnection {
    private Session session;

    public void connect() {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession("user", "127.0.0.1", 22); 
            session.setPassword("password");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println("SSH Connection established successfully!");
        } catch (JSchException e) {
            System.err.println("❌ Error al establecer la conexión SSH: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void disconnect() {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }
}

