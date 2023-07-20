package prueba_tecnica_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba_tecnica_spring.exeptions.ResourceNotFoundException;
import prueba_tecnica_spring.models.PersonModel;
import prueba_tecnica_spring.repository.PersonRepository;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b> This class implements the IPersonService methods and interacts with the repository. <br>
 */
@Service("personService")
public class PersonServiceImpl implements IPersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * This method returns the Persons of the database
     * @return List of PersonModel
     */
    @Override
    public List<PersonModel> getAll() {
        return personRepository.findAll();
    }

    /**
     * This method registers a Person in the database
     * @param person PersonModel
     * @return PersonModel
     */
    @Override
    public PersonModel save(PersonModel person) {
        return personRepository.save(person);
    }

    /**
     * This method updates a Person in the database
     * @param id   Long
     * @param user PersonModel
     * @return PersonModel
     */
    @Override
    public PersonModel update(Long id, PersonModel user) {
        PersonModel userDb = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no existe!"));
        userDb.setBirth(user.getBirth());
        userDb.setLastname(user.getLastname());
        userDb.setName(user.getName());
        return personRepository.save(userDb);
    }

    /**
     * This method returns a Person in the database according to an id of type Long
     * @param id Long
     * @return PersonModel
     */
    @Override
    public PersonModel getById(Long id) {
        PersonModel userDb;
        userDb = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no existe!"));
        return userDb;
    }
}
