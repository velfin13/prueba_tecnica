package prueba_tecnica_spring.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import prueba_tecnica_spring.models.Rol;
import prueba_tecnica_spring.models.UserModel;
import prueba_tecnica_spring.repository.UserRepository;
import prueba_tecnica_spring.util.ValidatorData;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepoitory;
    private ValidatorData validData = new ValidatorData();

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

        return User.builder().username(user.getUsername()).password(user.getPassword()).authorities(getAuthorities(user.getRoles())).build();
    }

    private Collection<GrantedAuthority> getAuthorities(List<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}