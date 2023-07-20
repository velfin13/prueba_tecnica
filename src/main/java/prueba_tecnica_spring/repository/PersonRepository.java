package prueba_tecnica_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba_tecnica_spring.models.PersonModel;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b>  PersonRepository<br>
 */
public interface PersonRepository extends JpaRepository<PersonModel, Long>  {}
