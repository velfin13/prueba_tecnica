package prueba_tecnica_spring.service;

import prueba_tecnica_spring.models.SessionModel;

import java.util.List;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b>  Interface that contains the methods of SessionService<br>
 */
public interface ISessionsService {
    /**
     * @param id Long
     * @return List of SessionModel
     */
    public List<SessionModel> getSessionsActivesWithIdUser(Long id);
}
