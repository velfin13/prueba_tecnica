package prueba_tecnica_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prueba_tecnica_spring.models.PersonModel;
import prueba_tecnica_spring.service.PersonServiceImpl;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
	@Autowired
	private PersonServiceImpl personService;
	
	@GetMapping()
	public List<PersonModel>getAll(){
		return personService.getAll();
	}
}
