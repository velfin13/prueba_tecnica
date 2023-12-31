package prueba_tecnica_spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import prueba_tecnica_spring.models.PersonModel;
import prueba_tecnica_spring.service.PersonServiceImpl;
import prueba_tecnica_spring.util.ResponseMessage;
import prueba_tecnica_spring.util.ValidatorData;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b>  PersonController<br>
 */
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonServiceImpl personService;

    @Autowired
    public  PersonController(PersonServiceImpl personService){
        this.personService = personService;
    }

    /**
     * @return List of Person Model
     */
    @GetMapping()
    public List<PersonModel> getAll() {
        return personService.getAll();
    }

    /**
     * @param id Long
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getById(id));
    }

    /**
     * @param person PersonModel
     * @param bindingResult BindingResult
     * @return ResponseEntity
     */
    @PostMapping()
    public ResponseEntity<?> savePerson(@Valid @RequestBody PersonModel person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(bindingResult.getFieldError().getDefaultMessage()));
        }
        boolean isValid = ValidatorData.isValidIdentification(person.getIdentification());
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage("El campo identificacion posee un numero repetido 4 veces"));
        }

        return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
    }

    /**
     * @param id Long
     * @param person PersonModel
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PersonModel person) {
        PersonModel userUpdated = personService.update(id, person);
        return ResponseEntity.ok(userUpdated);
    }
}
