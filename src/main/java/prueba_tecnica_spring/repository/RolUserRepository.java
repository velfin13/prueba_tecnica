package prueba_tecnica_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prueba_tecnica_spring.models.UserRoleModel;

public interface RolUserRepository extends JpaRepository<UserRoleModel, Long> {

}
