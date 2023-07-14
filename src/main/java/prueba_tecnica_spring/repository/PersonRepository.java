package prueba_tecnica_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba_tecnica_spring.models.PersonModel;

public interface PersonRepository extends JpaRepository<PersonModel, String>  {

}
