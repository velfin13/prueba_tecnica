package prueba_tecnica_spring.service;

import java.util.List;

import prueba_tecnica_spring.models.PersonModel;

public interface IPersonService {

	List<PersonModel> getAll();

	PersonModel save(PersonModel user);

	PersonModel update(Long id, PersonModel user);

	PersonModel getById(Long id);

}
