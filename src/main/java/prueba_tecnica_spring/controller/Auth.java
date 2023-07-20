package prueba_tecnica_spring.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import prueba_tecnica_spring.models.SessionModel;
import prueba_tecnica_spring.models.UserModel;
import prueba_tecnica_spring.repository.SessionRepository;
import prueba_tecnica_spring.repository.UserRepository;
import prueba_tecnica_spring.security.JwtUtilService;
import prueba_tecnica_spring.service.UserServiceImpl;
import prueba_tecnica_spring.util.PasswordUtils;
import prueba_tecnica_spring.util.ResponseMessage;
import prueba_tecnica_spring.util.UserResponse;
import prueba_tecnica_spring.util.ValidatorData;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b>  Auth Controller<br>
 */
@RestController
@RequestMapping("/api/auth")
public class Auth {
    private final UserRepository userRepository;
    private final  JwtUtilService jwtUtilService;
    private final SessionRepository sessionRepo;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService usuarioDetailsService;
    private final UserServiceImpl userService;

    @Autowired
    public Auth(JwtUtilService jwtUtilService,UserRepository userRepository ,SessionRepository sessionRepo,UserServiceImpl userService,UserDetailsService usuarioDetailsService,AuthenticationManager authenticationManager){
        this.userService=userService;
        this.usuarioDetailsService=usuarioDetailsService;
        this.authenticationManager=authenticationManager;
        this.sessionRepo=sessionRepo;
        this.userRepository=userRepository;
        this.jwtUtilService=jwtUtilService;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> data) {
        String userOrEmail = data.get("userOrEmail");
        UserModel userSingOut = userService.logout(userOrEmail);
        if (userSingOut != null) {
            return ResponseEntity.ok(new ResponseMessage("Cerraste Sesion con exito!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("No existe este usuario"));
        }

    }

    @PostMapping("/singin")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String userOrEmail = loginData.get("userOrEmail");
        String password = loginData.get("password");
        UserModel user = new UserModel();
        user = userService.getUserByUserOrEmail(userOrEmail);

        if (user != null && PasswordUtils.isPasswordValid(password, user.getPassword())) {
            if (!user.getStatus()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessage("Usuario bloqueado, comuniquese con Administrador"));
            }
            if (user.getSession_active()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessage("Ya iniciaste sesion en otro ordenador"));
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userOrEmail, password));

            final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(userOrEmail);

            String jwt = jwtUtilService.generateToken(userDetails);

            UserResponse response = new UserResponse(user, jwt);

            SessionModel session = new SessionModel();
            session.setFechaInicio(new Date());
            session.setUser(user);
            sessionRepo.save(session);

            user.setSession_active(true);
            userRepository.save(user);
            return ResponseEntity.ok(response);

        } else {
            // Usuario no encontrado o la contraseña no coincide
            if (user != null) {
                user.setIntentos_login(user.getIntentos_login() + 1);
                userRepository.save(user);

                if (user.getIntentos_login() >= 3) {
                    user.setStatus(false);
                    userRepository.save(user);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ResponseMessage("Usuario bloqueado por seguridad, contacte con administrador!"));
                }

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
                        "Usuario o contraseña inválida: Intento " + user.getIntentos_login() + "/3"));
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Usuario no encontrado"));
        }

    }
}
