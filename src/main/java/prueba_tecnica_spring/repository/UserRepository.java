package prueba_tecnica_spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import prueba_tecnica_spring.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

	@Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
	UserModel findByEmail(String email);

	@Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
	UserModel findByUsername(String username);

	@Query(value = "SELECT EXISTS(SELECT 1 FROM users WHERE email = :email)", nativeQuery = true)
	boolean existsByEmail(String email);
	
	@Query("SELECT COUNT(u) FROM UserModel u WHERE u.person.id = :personId")
    int countUsersByPersonId(Long personId);

}
