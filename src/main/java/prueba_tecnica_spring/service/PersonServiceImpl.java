package prueba_tecnica_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
