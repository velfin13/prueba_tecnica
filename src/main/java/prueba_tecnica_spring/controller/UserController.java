package prueba_tecnica_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import prueba_tecnica_spring.models.UserModel;
import prueba_tecnica_spring.service.UserServiceImpl;
import prueba_tecnica_spring.util.ResponseMessage;


@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	@GetMapping()
	public List<UserModel> getAll() {
		return userService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getById(id));
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> saveUser(@PathVariable Long id, @Valid @RequestBody UserModel user,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseMessage(bindingResult.getFieldError().getDefaultMessage()));
		}

		return new ResponseEntity<>(userService.save(id, user), HttpStatus.CREATED);
	}

}
