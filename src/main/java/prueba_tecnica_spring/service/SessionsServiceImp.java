package prueba_tecnica_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba_tecnica_spring.models.SessionModel;
import prueba_tecnica_spring.repository.SessionRepository;

import java.util.List;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b> This class implements the ISessionsService methods and interacts with the repository. <br>
 */
@Service("sessionService")
public class SessionsServiceImp implements ISessionsService{
    private final SessionRepository sessionRepository;
    @Autowired
    public SessionsServiceImp(SessionRepository sessionRepository){
        this.sessionRepository=sessionRepository;
    }

    /**
     * @param id Long
     * @return List of SessionModel
     */
    @Override
    public List<SessionModel> getSessionsActivesWithIdUser(Long id){
        return sessionRepository.findOpenSessionsByUserId(id);
    }
}
