package prueba_tecnica_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import prueba_tecnica_spring.models.UserModel;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b>  UserRepository<br>
 */
public interface UserRepository extends JpaRepository<UserModel, Long> {

    /**
     * @param email String
     * @return UserModel
     */
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    UserModel findByEmail(String email);

    /**
     * @param username String
     * @return UserModel
     */
    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    UserModel findByUsername(String username);

    /**
     * @param email String
     * @return boolean
     */
    @Query(value = "SELECT EXISTS(SELECT 1 FROM users WHERE email = :email)", nativeQuery = true)
    boolean existsByEmail(String email);

    /**
     * @param personId Long
     * @return int
     */
    @Query("SELECT COUNT(u) FROM UserModel u WHERE u.person.id = :personId")
    int countUsersByPersonId(Long personId);

}
