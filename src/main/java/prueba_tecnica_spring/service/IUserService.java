package prueba_tecnica_spring.service;

import java.util.List;
import prueba_tecnica_spring.models.UserModel;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b>  Interface that contains the methods of UserService<br>
 */
public interface IUserService {

	/**
	 * @return List of UserModel
	 */
	List<UserModel> getAll();

	/**
	 * @param id Long
	 * @return UserModel
	 */
	UserModel getById(Long id);

	/**
	 * @param id Long
	 * @param user UserModel
	 * @return UserModel
	 */
	UserModel save(Long id, UserModel user);

	/**
	 * @param email String
	 * @return UserModel
	 */
	UserModel logout(String email);

	/**
	 * @param userOrEmail String
	 * @return UserModel
	 */
	public UserModel getUserByUserOrEmail(String userOrEmail);

}
