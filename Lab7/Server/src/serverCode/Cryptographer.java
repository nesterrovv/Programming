package serverCode;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Класс {@code PasswordManager} contains methods for working with encryption tools
 * @author Ivan Nesterov
 * @version 1.0
 * @since 23.06.21
 */
public class Cryptographer {

    /**
     * Method for receiving hex string of entered string. Used SHA-512 hex algorithm.
     * @param stringForEncrypt string for encrypting
     * @return hex string
     * @throws NoSuchAlgorithmException if algorithm is not exist
     */
    //String saltForEncrypt = "itm0un1vers1ty";
    public String encrypt(String stringForEncrypt){
        String saltForEncrypt = "itm0un1vers1ty";
        String hexString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(saltForEncrypt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(stringForEncrypt.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length ; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hexString = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return hexString;
    }
}