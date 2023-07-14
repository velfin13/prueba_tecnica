package prueba_tecnica_spring.service;

import java.util.List;

import prueba_tecnica_spring.models.UserModel;

public interface IUserService {

	List<UserModel> getAll();

	UserModel getById(Long id);

	UserModel save(Long id, UserModel user);

}
