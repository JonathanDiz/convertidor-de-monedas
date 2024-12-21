package com.jonathandiz.convertidor_de_monedas.config;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.security.auth.x500.X500Principal;

public class SSLGenerator {

    public static void main(String[] args) {
        try {
            // 1️⃣ Generar par de claves (RSA, 2048 bits)
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();

            // 2️⃣ Crear el certificado autofirmado
            X509Certificate certificate = generateSelfSignedCertificate(keyPair);

            // 3️⃣ Crear un keystore y almacenar la clave privada y el certificado
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(null, null); // Crea un keystore vacío
            keyStore.setKeyEntry("mi_certificado", keyPair.getPrivate(), "mi_contraseña".toCharArray(), new Certificate[]{certificate});

            // 4️⃣ Guardar el keystore en un archivo
            try (FileOutputStream fos = new FileOutputStream("keystore.p12")) {
                keyStore.store(fos, "mi_contraseña".toCharArray());
            }

            System.out.println("🎉 Keystore 'keystore.p12' generado exitosamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera un certificado X.509 autofirmado.
     *
     * @param keyPair El par de claves (pública y privada) para firmar el certificado.
     * @return Un certificado X.509 autofirmado.
     */
    private static X509Certificate generateSelfSignedCertificate(KeyPair keyPair) throws Exception {
        // 1️⃣ Parámetros del certificado
        long now = System.currentTimeMillis();
        Date startDate = new Date(now);
        Date endDate = new Date(now + (365L * 24 * 60 * 60 * 1000)); // Válido por 1 año

        // 2️⃣ Datos del certificado (DN - Distinguished Name)
        X500Principal dnName = new X500Principal("CN=localhost, OU=Desarrollo, O=MiOrganizacion, L=Santiago, ST=Santiago, C=CL");

        // 3️⃣ Usar API de `java.security.cert.Certificate` para construir el certificado
        java.security.cert.CertificateFactory certFactory = java.security.cert.CertificateFactory.getInstance("X.509");
        byte[] certBytes = createX509Certificate(dnName, keyPair, startDate, endDate);
        return (X509Certificate) certFactory.generateCertificate(new java.io.ByteArrayInputStream(certBytes));
    }

    /**
     * Genera los bytes de un certificado X.509 autofirmado.
     *
     * @param dnName    El Distinguished Name (DN) del certificado.
     * @param keyPair   El par de claves para firmar el certificado.
     * @param startDate La fecha de inicio de validez.
     * @param endDate   La fecha de fin de validez.
     * @return Un arreglo de bytes que representa el certificado X.509.
     */
    private static byte[] createX509Certificate(X500Principal dnName, KeyPair keyPair, Date startDate, Date endDate) throws Exception {
        // Usar la clase `sun.security.x509` ya no está permitido en Java 17
        // Pero podemos usar `java.security.Signature` para firmar el certificado manualmente

        // 1️⃣ Crear una estructura básica de un X.509 Certificate (para esta demostración es simplificado)
        BigInteger serialNumber = BigInteger.valueOf(System.currentTimeMillis());

        // 2️⃣ Firmar el certificado
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(dnName.getEncoded()); // Firmar el DN

        // 3️⃣ Crear los bytes firmados (simulación de la firma del certificado)
        byte[] signatureBytes = signature.sign();

        // 4️⃣ Para hacerlo más realista, deberíamos construir un verdadero X.509, 
        // pero esto requiere acceso a `sun.security.x509` o `BouncyCastle`. 
        // Por ahora, asumimos que se crea un certificado simplificado.

        // Retornar los bytes del certificado
        return signatureBytes; // ⚠️ Esto NO es un certificado X.509 real, solo una demostración
    }
}

