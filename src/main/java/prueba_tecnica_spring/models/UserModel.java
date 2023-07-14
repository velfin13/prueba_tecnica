package prueba_tecnica_spring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	@NotBlank(message = "La username es obligatoria")
	@Pattern(regexp = "^[^!@#$%^&*()_+=\\-\\[\\]{};':\"\\\\|,.<>/?]*$", message = "El nombre no debe contener signos")
	@NotNull(message = "El campo username es obligatorio")
	private String username;

	@Column(name = "password", nullable = false)
	@NotNull(message = "El campo password es obligatorio")
	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=\\-\\[\\]{};':\"\\\\|,.<>/?]).*$", message = "La contraseña no cumple con los requisitos")
	private String password;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "session_active")
	private Boolean session_active = false;
	
	@Column(name = "intentos_sesion")
	private int intentos_login = 0;

	private Boolean status = true;

	@ManyToOne
	private PersonModel person;
}