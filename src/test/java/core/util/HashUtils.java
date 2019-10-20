package core.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {


    private static String getMD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes());
            byte[] digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toLowerCase();
    }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static String getTimestamp(){
        return String.valueOf(System.currentTimeMillis());
    }


    public static String getHash (String timestamp, String privateKey, String publicKey) {
        return getMD5(timestamp+privateKey+publicKey);
    }
}
