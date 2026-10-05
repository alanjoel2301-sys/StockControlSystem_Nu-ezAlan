
package com.tienda.stockcontrol.modelo;

/**
 *
 * @author Dell
 */
public class PasswordUtil {

    private static final int ITERACIONES = 210000;
    private static final int LARGO_HASH_BITS = 256;
    private static final int LARGO_SALT_BYTES = 16;

    private PasswordUtil() {
    }

    public static String generarSalt() {
        java.security.SecureRandom random = new java.security.SecureRandom();
        byte[] bytesSalt = new byte[LARGO_SALT_BYTES];
        random.nextBytes(bytesSalt);
        return bytesAHex(bytesSalt);
    }

    public static String calcularHash(String password, String salt) {
        try {
            byte[] bytesSalt = hexABytes(salt);
            javax.crypto.spec.PBEKeySpec especificacion = new javax.crypto.spec.PBEKeySpec(
                    password.toCharArray(), bytesSalt, ITERACIONES, LARGO_HASH_BITS);
            javax.crypto.SecretKeyFactory fabrica =
                    javax.crypto.SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] bytesHash = fabrica.generateSecret(especificacion).getEncoded();
            return bytesAHex(bytesHash);
        } catch (java.security.NoSuchAlgorithmException | java.security.spec.InvalidKeySpecException e) {
            throw new RuntimeException("No se pudo calcular el hash de la contraseña.", e);
        }
    }

    public static boolean verificar(String password, String salt, String hashGuardado) {
        String hashCalculado = calcularHash(password, salt);
        return java.security.MessageDigest.isEqual(
                hashCalculado.getBytes(java.nio.charset.StandardCharsets.UTF_8),
                hashGuardado.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    private static String bytesAHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static byte[] hexABytes(String hex) {
        int largo = hex.length();
        byte[] datos = new byte[largo / 2];
        for (int i = 0; i < largo; i += 2) {
            datos[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return datos;
    }
}