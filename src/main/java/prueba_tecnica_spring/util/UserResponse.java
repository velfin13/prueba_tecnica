package prueba_tecnica_spring.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import prueba_tecnica_spring.models.UserModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UserModel user;
    private String token;


}