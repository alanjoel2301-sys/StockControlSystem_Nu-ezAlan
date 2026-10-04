
package com.tienda.stockcontrol.modelo;

/**
 *
 * @author Dell
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtil {
    private PasswordUtil() {
    }
    
    public static String generarSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytesSalt = new byte[8];
        random.nextBytes(bytesSalt);
        return bytesAHex(bytesSalt);
    }
 
    public static String calcularHash(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytesHash = digest.digest((salt + password).getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return bytesAHex(bytesHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("El algoritmo SHA-256 no esta disponible.", e);
        }
    }
    
    private static String bytesAHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    public static boolean verificar(String password, String salt, String hashGuardado) {
        String hashCalculado = calcularHash(password, salt);
        return hashCalculado.equals(hashGuardado);
    }
    
}
