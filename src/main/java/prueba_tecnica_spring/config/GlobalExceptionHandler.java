package prueba_tecnica_spring.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import prueba_tecnica_spring.exeptions.ResourceNotFoundException;
import prueba_tecnica_spring.util.ResponseMessage;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseMessage handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseMessage("Recurso No encontrado");
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseMessage handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseMessage("Error: Campo duplicado");
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseMessage handleBadRequestException(HttpMessageNotReadableException ex) {
        return new ResponseMessage("Solicitud incorrecta");
    }
}