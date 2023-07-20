package prueba_tecnica_spring.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException e) {
        String errorMessage = "JWT expired at " + e.getClaims().getExpiration()
                + ". Current time: " + e.getClaims().getIssuedAt()
                + ", a difference of " + (e.getClaims().getExpiration().getTime() - System.currentTimeMillis())
                + " milliseconds. Allowed clock skew: " + e.getClaims().get("clockSkew");
        return new ResponseEntity<>(new ResponseMessage(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> handleMalformedJwtException(MalformedJwtException e) {
        String errorMessage = "Token JWT inválido";
        return new ResponseEntity<>(new ResponseMessage(errorMessage), HttpStatus.BAD_REQUEST);
    }
}