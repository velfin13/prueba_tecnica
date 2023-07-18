package prueba_tecnica_spring.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import prueba_tecnica_spring.models.UserModel;
import prueba_tecnica_spring.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepoitory;
	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        UserModel usuario = userRepoitory.findByUsername(username);
	        if (usuario == null) {
	            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
	        }

	        return User.builder()
	                .username(usuario.getUsername())
	                .password(usuario.getPassword())
	                .roles(usuario.getRoles())
	                .build();
	    }

}