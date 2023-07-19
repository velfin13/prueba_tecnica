package prueba_tecnica_spring.service;

import java.util.List;

import prueba_tecnica_spring.models.UserModel;

public interface IUserService {

	List<UserModel> getAll();

	List<UserModel> getAllUserWithRols(String username);

	UserModel getById(Long id);

	UserModel save(Long id, UserModel user);

	UserModel logout(String email);

}
