package prueba_tecnica_spring.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import prueba_tecnica_spring.models.SessionModel;

import java.util.List;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b>  SessionRepository<br>
 */
public interface SessionRepository extends JpaRepository<SessionModel, Long>{
    @Query(value = "SELECT * FROM sessions s WHERE s.fecha_cierre IS NULL AND s.user_id = :userId", nativeQuery = true)
    List<SessionModel> findOpenSessionsByUserId(Long userId);
}
