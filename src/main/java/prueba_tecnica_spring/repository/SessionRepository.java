package prueba_tecnica_spring.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import prueba_tecnica_spring.models.SessionModel;

public interface SessionRepository extends JpaRepository<SessionModel, Long>{

}
