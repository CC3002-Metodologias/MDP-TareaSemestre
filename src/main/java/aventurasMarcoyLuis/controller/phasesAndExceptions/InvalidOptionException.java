package aventurasMarcoyLuis.controller.phasesAndExceptions;

/**
 * Exception de cuando la accion escojida no es permitida realizar en la fase actual
 */
public class InvalidOptionException extends Exception{
    public InvalidOptionException(String message){
        super(message);
    }
}
