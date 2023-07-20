package prueba_tecnica_spring.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b> This class contains the methods that work with the user's encrypted password, as well as verify, etc. <br>
 */
public class PasswordUtils {
    /**
     * @param rawPassword variable in plain text
     * @param encodedPassword encrypted variable
     * @return Boolean, true if the key is correct
     */
    public static boolean isPasswordValid(String rawPassword, String encodedPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
