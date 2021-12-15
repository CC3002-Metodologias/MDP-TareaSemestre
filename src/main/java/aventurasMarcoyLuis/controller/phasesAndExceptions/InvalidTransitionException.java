package aventurasMarcoyLuis.controller.phasesAndExceptions;

/**
 * Exception que se arroja cuando se intenta transicionar de una fase a otra que no permite la primera.
 */
public class InvalidTransitionException extends Exception{
    public InvalidTransitionException(String message){
        super(message);
    }
}
