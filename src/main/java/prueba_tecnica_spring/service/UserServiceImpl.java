package prueba_tecnica_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prueba_tecnica_spring.models.UserModel;
import prueba_tecnica_spring.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements IUserService {

	 @Autowired
	    private UserRepository userRepository;

	    @Override
	    public List<UserModel> getAll() {
	        return userRepository.findAll();
	    }
}
