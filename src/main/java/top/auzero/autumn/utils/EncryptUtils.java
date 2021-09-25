package top.auzero.autumn.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
*
* @author Auzero
* @since 2021/9/24
*/
public class EncryptUtils {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(String raw) {
        return encoder.encode(raw);
    }

    public static boolean notMatch(String raw, String encoded) {
        return !encoder.matches(raw, encoded);
    }

}
