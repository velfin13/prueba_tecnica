package prueba_tecnica_spring.util;

import prueba_tecnica_spring.models.Rol;
import prueba_tecnica_spring.models.UserModel;
import java.util.List;
import java.util.stream.Collectors;
/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b> This class validates the UserModel record data <br>
 */
public class ValidatorData {

    /**
     * This method receives the String type identification as a parameter and validates that it does not have 4 repeated numbers in a row.
     * @param identification string identification
     * @return Boolean - true if the identification is valid
     */
    public static boolean isValidIdentification(String identification) {
        for (int i = 0; i <= identification.length() - 4; i++) {
            char currentChar = identification.charAt(i);
            boolean hasRepeatedDigits = true;

            for (int j = i + 1; j < i + 4; j++) {
                if (identification.charAt(j) != currentChar) {
                    hasRepeatedDigits = false;
                    break;
                }
            }

            if (hasRepeatedDigits) {
                return false;
            }
        }

        return true;
    }


    /**
     * This method validates that the email entered is an email
     * @param email String email
     * @return Boolean
     */
    public static boolean isEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}
