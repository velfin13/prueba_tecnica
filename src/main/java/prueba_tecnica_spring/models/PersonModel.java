package prueba_tecnica_spring.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "La name es obligatoria")
	@NotNull(message = "El campo name es obligatorio")
	private String name;

	@Column(nullable = false)
	@NotBlank(message = "La lastname es obligatoria")
	@NotNull(message = "El lastname name es obligatorio")
	private String lastname;

	@NotBlank(message = "La identificación es obligatoria")
	@Pattern(regexp = "\\d{10}", message = "La identificación debe tener 10 dígitos")
	@Column(nullable = false, unique = true)
	private String identification;

	@Column(name = "birthdate")
	@Past(message = "La birthdate debe ser anterior a la fecha actual")
	@NotNull(message = "birthdate es obligatoria")
	private Date birth;

	@OneToMany(mappedBy = "person")
	@JsonIgnore
	private List<UserModel> users;

}
