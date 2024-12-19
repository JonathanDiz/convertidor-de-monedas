package com.jonathandiz.convertidor_de_monedas.ssh;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.springframework.stereotype.Component;

@Component
public class SSHServer {

    private SshServer sshServer;

    /**
     * Inicia un servidor SSH en la IP local detectada automáticamente.
     * 
     * @param port El puerto donde se iniciará el servidor SSH.
     */
    public void startServer(int port) {
        try {
            // Detectar la IP local de forma automática
            String localIp = InetAddress.getLocalHost().getHostAddress();
            
            sshServer = SshServer.setUpDefaultServer();
            sshServer.setPort(port);
            sshServer.setHost(localIp); 
            sshServer.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
            
            sshServer.setPasswordAuthenticator((username, password, session) -> 
                "user".equals(username) && "password".equals(password)
            );
            
            new Thread(() -> {
                try {
                    sshServer.start();
                    System.out.println("✅ Servidor SSH iniciado en la IP " + localIp + " y puerto " + port);
                } catch (IOException e) {
                    throw new RuntimeException("❌ Error al iniciar el servidor SSH.", e);
                }
            }).start();

            while (!sshServer.isStarted()) {
                Thread.sleep(500); 
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("❌ Error al iniciar el servidor SSH.", e);
        }
    }

    /**
     * Detiene el servidor SSH si está en ejecución.
     */
    public void stopServer() {
        try {
            if (sshServer != null && !sshServer.isClosed()) {
                sshServer.stop();
                System.out.println("✅ Servidor SSH detenido.");
            }
        } catch (IOException e) {
            throw new RuntimeException("❌ Error al detener el servidor SSH.", e);
        }
    }

    /**
     * Busca un puerto disponible de forma automática.
     * 
     * @return El número de puerto disponible.
     */
    private int findAvailablePort() {
        try (java.net.ServerSocket socket = new java.net.ServerSocket(0)) {
            socket.setReuseAddress(true); // Permitir reutilizar el puerto si se libera
            int port = socket.getLocalPort(); // Devuelve el puerto asignado automáticamente
            System.out.println("✅ Puerto disponible encontrado: " + port);
            return port;
        } catch (IOException e) {
            throw new RuntimeException("❌ No se pudo encontrar un puerto disponible.", e);
        }
    }

    /**
     * Inicia el servidor SSH en un puerto disponible automáticamente.
     */
    public void startServerOnAvailablePort() {
        int availablePort = findAvailablePort();
        startServer(availablePort);
    }
}
