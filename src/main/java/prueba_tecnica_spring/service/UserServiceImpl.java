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

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b> This class implements the IUserService methods and interacts with the repository. <br>
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PersonRepository personRepository;

    @Autowired
    public UserServiceImpl(PersonRepository personRepository,UserRepository userRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    /**
     * This method returns a list of people from the database.
     * @return List of UserModel
     */
    @Override
    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    /**
     * This method returns a person from the database by their id
     * @param id Long
     * @return UserModel
     */
    @Override
    public UserModel getById(Long id) {
        UserModel userDb;
        userDb = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User no existe!"));
        return userDb;
    }

    /**
     * This method receives an email or a username of type string and if it exists, it closes the session of that user
     * @param userOrEmail String
     * @return UserModel
     */
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

    /**
     * this method receives the id of a PersonModel and a UserModel and registers a new user
     * @param id   Long id PersonModel
     * @param user UserModel
     * @return UserModel
     */
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

    /**
     * Receives a user or email in string and returns a UserModel
     * @param userOrEmail String
     * @return UserModel
     */
    @Override
    public UserModel getUserByUserOrEmail(String userOrEmail){
        UserModel user = new UserModel();
        if (ValidatorData.isEmail(userOrEmail)) {
            user = userRepository.findByEmail(userOrEmail);
        } else {
            user = userRepository.findByUsername(userOrEmail);
        }
        return  user;
    }

    /**
     * This method receives an email and searches the database and if it exists, it replaces it with an identifier to make it unique.
     * @param name String
     * @param lastname String
     * @param identification String
     * @return String email unique
     */
    private String generateUniqueEmail(String name, String lastname, String identification) {
        String dominio;
        dominio = "@mail.com";
        String baseEmail;
        baseEmail = name.toLowerCase() + lastname.toLowerCase() + dominio;
        String uniqueEmail = baseEmail;
        int counter = 1;

        while (userRepository.existsByEmail(uniqueEmail)) {

            uniqueEmail = name.toLowerCase() + lastname.toLowerCase() + "-" + counter + dominio;
            counter++;
        }

        return uniqueEmail;
    }
}
