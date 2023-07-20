package prueba_tecnica_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prueba_tecnica_spring.exeptions.ResourceNotFoundException;
import prueba_tecnica_spring.models.PersonModel;
import prueba_tecnica_spring.models.UserModel;
import prueba_tecnica_spring.repository.PersonRepository;
import prueba_tecnica_spring.repository.UserRepository;
import prueba_tecnica_spring.util.ValidatorData;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<UserModel> getAll() {
		return userRepository.findAll();
	}

	@Override
	public UserModel getAllUserWithRols(String username) {
		return userRepository.getUserWithSessionsByUsername(username);
	}

	@Override
	public UserModel getById(Long id) {
		UserModel userDb = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User no existe!"));
		return userDb;
	}

	@Override
	public UserModel logout(String userOrEmail) {
		UserModel user = new UserModel();

		if (ValidatorData.isEmail(userOrEmail)) {
			user = userRepository.findByEmail(userOrEmail);
		} else {
			user = userRepository.findByUsername(userOrEmail);
		}
		
		if (user != null) {
			user.setSession_active(false);
			return userRepository.save(user);
		} else {
			return null;
		}

	}

	@Override
	public UserModel save(Long id, UserModel user) {
		PersonModel personDb = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Persona no existe!"));

		if (userRepository.countUsersByPersonId(id) <= 1) {
			UserModel newUser = new UserModel();
			String uniqueEmail = generateUniqueEmail(personDb.getName(), personDb.getLastname(),
					personDb.getIdentification());
			newUser.setEmail(uniqueEmail);
			newUser.setPassword(user.getPassword());
			newUser.setUsername(user.getUsername());
			newUser.setPerson(personDb);
			return userRepository.save(newUser);
		}

		return null;

	}

	private String generateUniqueEmail(String name, String lastname, String identification) {
		String dominio = "@mail.com";
		String baseEmail = name.toLowerCase() + lastname.toLowerCase() + dominio;
		String uniqueEmail = baseEmail;
		int counter = 1;

		while (userRepository.existsByEmail(uniqueEmail)) {

			uniqueEmail = name.toLowerCase() + lastname.toLowerCase() + "-" + counter + dominio;
			counter++;
		}

		return uniqueEmail;
	}
}
