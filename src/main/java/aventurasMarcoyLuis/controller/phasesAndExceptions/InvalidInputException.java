package aventurasMarcoyLuis.controller.phasesAndExceptions;

/**
 * Exception cuando el input o opción escojida es invalida y no corresponde a una opción a elejir.
 */
public class InvalidInputException extends Exception{

    public InvalidInputException(String message){
        super(message);
    }
}
