package prueba_tecnica_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prueba_tecnica_spring.exeptions.ResourceNotFoundException;
import prueba_tecnica_spring.models.PersonModel;
import prueba_tecnica_spring.repository.PersonRepository;

@Service("personService")
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonModel> getAll() {
        return personRepository.findAll();
    }

    @Override
    public PersonModel save(PersonModel user) {
        return personRepository.save(user);
    }

    @Override
    public PersonModel update(Long id, PersonModel user) {
        PersonModel userDb = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no existe!"));
        userDb.setBirth(user.getBirth());
        userDb.setLastname(user.getLastname());
        userDb.setName(user.getName());
        return personRepository.save(userDb);
    }

    @Override
    public PersonModel getById(Long id) {
        PersonModel userDb = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no existe!"));
        return userDb;
    }
}
