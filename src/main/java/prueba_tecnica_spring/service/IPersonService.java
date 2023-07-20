package prueba_tecnica_spring.service;

import java.util.List;
import prueba_tecnica_spring.models.PersonModel;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b>  Interface that contains the methods of PersonService<br>
 */
public interface IPersonService {

	/**
	 * @return List of PersonModel
	 */
	List<PersonModel> getAll();

	/**
	 * @param person PersonModel
	 * @return PersonModel
	 */
	PersonModel save(PersonModel person);

	/**
	 * @param id Long
	 * @param person PersonModel
	 * @return PersonModel
	 */
	PersonModel update(Long id, PersonModel person);

	/**
	 * @param id Long
	 * @return PersonModel
	 */
	PersonModel getById(Long id);

}
