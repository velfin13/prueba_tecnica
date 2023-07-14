package prueba_tecnica_spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import prueba_tecnica_spring.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, String> {

	@Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
	UserModel findByEmail(String email);
	
	@Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
	UserModel findByUsername(String username);
	
}
