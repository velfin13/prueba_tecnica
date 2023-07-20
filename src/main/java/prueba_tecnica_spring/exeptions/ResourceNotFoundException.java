package prueba_tecnica_spring.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import prueba_tecnica_spring.util.ResponseMessage;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b> Custom exception for a resource not found <br>
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private final ResponseMessage responseMessage;

    /**
     * @param message String
     */
    public ResourceNotFoundException(String message) {
        super(message);
        this.responseMessage = new ResponseMessage(message);
    }

    public ResponseMessage getErrorDetails() {
        return responseMessage;
    }
}