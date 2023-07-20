package prueba_tecnica_spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public List<UserModel> getAll() {
        return userService.getAll();
    }

    @GetMapping("/withrols/{username}")
    public UserModel getAllWithRols(@PathVariable String username) {
        return userService.getAllUserWithRols(username);
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
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        UserModel newUser = userService.save(id, user);

        if (newUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage("Has exedido el limite de registro de usuario, solo se permiten 2!"));

        }

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
