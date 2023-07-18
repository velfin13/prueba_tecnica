package prueba_tecnica_spring.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import prueba_tecnica_spring.models.UserModel;
import prueba_tecnica_spring.repository.UserRepository;
import prueba_tecnica_spring.util.ValidatorData;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepoitory;
	
	 @Override
	    public UserDetails loadUserByUsername(String userOrEmail) throws UsernameNotFoundException {
	     
		 UserModel user = null;
		 
		 if (ValidatorData.isEmail(userOrEmail)) {
			 user = userRepoitory.findByEmail(userOrEmail);
			} else {
				user = userRepoitory.findByUsername(userOrEmail);
			}

		 
	        
	        if (user == null) {
	            throw new UsernameNotFoundException("Usuario no encontrado: " + user);
	        }

	        return User.builder()
	                .username(user.getUsername())
	                .password(user.getPassword())
	                .roles(user.getRoles())
	                .build();
	    }

}