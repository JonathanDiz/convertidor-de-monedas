package com.jonathandiz.convertidor_de_monedas;

import com.jonathandiz.convertidor_de_monedas.ssh.SSHServer;
import com.jonathandiz.convertidor_de_monedas.config.SSHConnection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final SSHServer sshServer;
    private final SSHConnection sshConnection;

    public ApplicationRunner(SSHServer sshServer, SSHConnection sshConnection) {
        this.sshServer = sshServer;
        this.sshConnection = sshConnection;
    }

    @Override
    public void run(String... args) {
        try {
            System.out.println("Iniciando servidor SSH...");
            sshServer.startServer(22); // Inicia el servidor SSH en el puerto 22
            System.out.println("Servidor SSH iniciado correctamente.");

            System.out.println("Estableciendo conexión SSH con la base de datos...");
            sshConnection.connect();
            System.out.println("Conexión SSH con PostgreSQL establecida.");

        } catch (Exception e) {
            System.err.println("Error en el flujo de inicio de la aplicación: " + e.getMessage());
        }
    }
}
