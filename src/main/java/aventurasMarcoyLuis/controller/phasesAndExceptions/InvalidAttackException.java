package aventurasMarcoyLuis.controller.phasesAndExceptions;

/**
 * Exception de cuando se intenta realizar un ataque de un personaje a otro que no es atacable por el primero o por el medio de ataque
 * que se intenta.
 */
public class InvalidAttackException extends Exception{
    public InvalidAttackException(String message){
        super(message);
    }
}
