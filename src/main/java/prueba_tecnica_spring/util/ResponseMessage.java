package prueba_tecnica_spring.util;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b> This class returns a json with the message that you pass to it as a construct parameter <br>
 *
 */
public class ResponseMessage {
    private String message;

    /**
     * @param message string message
     */
    public ResponseMessage(String message) {
        this.message = message;
    }

    /**
     * @return String message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message String message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}