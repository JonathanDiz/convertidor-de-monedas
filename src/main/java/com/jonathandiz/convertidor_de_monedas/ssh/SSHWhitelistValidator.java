package com.jonathandiz.convertidor_de_monedas.ssh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Component
public class SSHWhitelistValidator {

	@Value("${ssh.whitelist}")
	private List<String> allowedIPs;
	
	/**
     * Verifica si una IP está permitida en la whitelist.
     * 
     * @param ipAddress La dirección IP que se quiere validar.
     * @return true si está permitida, false en caso contrario.
     */
	public boolean isAllowed(String ipAddress) {
		try {
			InetAddress address = InetAddress.getByName(ipAddress);
			for (String allowedIP : allowedIPs) {
				if (allowedIP.contains("/")) { // Validar rangos CIDR
					if (isInCIDRRange(address, allowedIP)) {
						return true;
					}
				} else if (allowedIP.equals(ipAddress)) {
					return true; // IP exacta
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
     * Comprueba si una dirección IP pertenece a un rango CIDR.
     * 
     * @param address La dirección IP a comprobar.
     * @param cidr    El rango en formato CIDR (ej: 203.0.113.0/24).
     * @return true si pertenece al rango, false en caso contrario.
     */
	private boolean isInCIDRRange(InetAddress address, String cidr) {
		String[] parts = cidr.split("/");
		String ip = parts[0];
		int prefixLength = Integer.parseInt(parts[1]);
		
		try {
			byte[] addressBytes = address.getAddress();
			byte[] cidrBytes = InetAddress.getByName(ip).getAddress();
			
			int mask = ~((1 << (32 - prefixLength)) - 1);
			int addressInt = byteArrayToInt(addressBytes);
			int cidrInt = byteArrayToInt(cidrBytes);
			
			return (addressInt & mask) == (cidrInt & mask);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private int byteArrayToInt(byte[] bytes) {
		int result = 0;
		for (byte b : bytes) {
			result = (result << 8) | (b & 0xFF);
		}
		return result;
	}
}
