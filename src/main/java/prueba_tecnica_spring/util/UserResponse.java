package prueba_tecnica_spring.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import prueba_tecnica_spring.models.UserModel;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b> Login response model returning UserModel and a token<br>
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UserModel user;
    private String token;
}