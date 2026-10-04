import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class ClasseAES {
    private static final String TRANSFORMACIO = "AES/ECB/PKCS5Padding";

    public static String encripta(String missatge, String clau) {
        try {
            SecretKeySpec clauAES = new SecretKeySpec(clau.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance(TRANSFORMACIO);
            cipher.init(Cipher.ENCRYPT_MODE, clauAES);
            byte[] bytesXifrats = cipher.doFinal(missatge.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(bytesXifrats);
        } catch (Exception e) {
            System.out.println("Error encriptant amb AES: " + e.getMessage());
            return null;
        }
    }

    public static String desencripta(String missatgeXifrat, String clau) {
        try {
            SecretKeySpec clauAES = new SecretKeySpec(clau.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance(TRANSFORMACIO);
            cipher.init(Cipher.DECRYPT_MODE, clauAES);

            byte[] bytesXifrats = Base64.getDecoder().decode(missatgeXifrat);
            byte[] bytesOriginals = cipher.doFinal(bytesXifrats);

            return new String(bytesOriginals, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Error desencriptant amb AES: " + e.getMessage());
            return null;
        }
    }
}
