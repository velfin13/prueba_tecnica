package prueba_tecnica_spring.util;

import org.springframework.beans.factory.annotation.Autowired;

import prueba_tecnica_spring.models.Rol;
import prueba_tecnica_spring.models.UserModel;
import prueba_tecnica_spring.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ValidatorData {
	
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
	
	
	public static boolean isEmail(String input) {
	    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	    return input.matches(emailRegex);
	}

    public List<String> convertRolesToListOfNames(UserModel user) {
        List<Rol> roles = user.getRoles();

        List<String> roleNames = roles.stream()
                .map(Rol::getName)
                .collect(Collectors.toList());

        return roleNames;
    }
}
