package prueba_tecnica_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prueba_tecnica_spring.models.UserModel;
import prueba_tecnica_spring.service.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping()
	public List<UserModel>getAll(){
		return userService.getAll();
	}
	
	
}
