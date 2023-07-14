package prueba_tecnica_spring.exeptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import prueba_tecnica_spring.util.ResponseMessage;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    private ResponseMessage responseMessage;

    public ResourceNotFoundException(String message) {
        super(message);
        this.responseMessage = new ResponseMessage(message);
    }

    public ResponseMessage getErrorDetails() {
        return responseMessage;
    }
}