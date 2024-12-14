package com.jonathandiz.convertidor_de_monedas.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.KeyPair;

import java.io.File;

public class SSHKeyGenerator {

	private static final String RSA_KEY_PATH = System.getProperty("user.home") + "/.ssh/id_rsa";

	/**
     * Genera un par de claves RSA si no existen.
     */
	public static void generateKeyPairIfNotExists() {
		try {
			File privateKeyFile = new File(RSA_KEY_PATH);
			if (!privateKeyFile.exists()) {
				JSch jsch = new JSch();
				KeyPair keyPair = KeyPair.genKeyPair(jsch, KeyPair.RSA);
				
				// Guarda la clave privada y la clave pública
				keyPair.writePrivateKey(RSA_KEY_PATH);
				keyPair.writePublicKey(RSA_KEY_PATH + ".pub", "comment");
				System.out.println("Par de claves SSH generado en: " + RSA_KEY_PATH);
				keyPair.dispose();
			} else {
				System.out.println("El par de claves RSA ya existen en: " + RSA_KEY_PATH);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error al generar el par de claves RSA", e);
		}
	}
}
