package prueba_tecnica_spring.models;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b> UserModel for JPA <br>
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	@NotBlank(message = "La username es obligatoria")
	@Pattern(regexp = "^[^!@#$%^&*()_+=\\-\\[\\]{};':\"\\\\|,.<>/?]*$", message = "El username no debe contener signos")
	@NotNull(message = "El campo username es obligatorio")
	private String username;

	@Column(name = "password", nullable = false)
	@NotNull(message = "El campo password es obligatorio")
	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=\\-\\[\\]{};':\"\\\\|,.<>/?]).*$", message = "La password no cumple con los requisitos")
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

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_rols",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"),
			uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "rol_id"})
	)
	private List<Rol> roles;

	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties("user")
	private List<SessionModel> sesiones;
}