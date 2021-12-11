package aventurasMarcoyLuis.controller.observers;

import aventurasMarcoyLuis.controller.GameController;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidTransitionException;

import java.beans.PropertyChangeEvent;

/**
 * Observer dedicado a ver si se ha perdido el juego.
 */
public class WinObserver implements Iobserver{

    private final GameController controller;

    public WinObserver(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        boolean bool = (boolean) evt.getNewValue();
        if (bool) {
            try {
                controller.getPhase().toEndRoundPhase();
                controller.tryToRoundOver();
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
        }
    }
}
