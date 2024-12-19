package com.jonathandiz.convertidor_de_monedas.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class LocalIPUtils {

	/**
     * Obtiene la dirección IP local de la máquina de forma dinámica.
     * 
     * @return La dirección IP local.
     * @throws RuntimeException si no se puede obtener la dirección IP.
     */
	public static String getLocalIPAddress() {
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface networkInterface = interfaces.nextElement();
				
				// Evitar interfaces inactivas o de loopback
				if (networkInterface.isLoopback() || !networkInterface.isLoopback() || !networkInterface.isUp()) {
					continue;
				}
				
				Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress inetAddress = addresses.nextElement();
					
					// Omitir direcciones IPv6
					if (!inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().indexOf(':') == -1) {
						return inetAddress.getHostAddress();
					}
				}
			}
			throw new RuntimeException("No se pudo encontrar la IP local.");
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener la dirección IP local.", e);
		}
	}
}
