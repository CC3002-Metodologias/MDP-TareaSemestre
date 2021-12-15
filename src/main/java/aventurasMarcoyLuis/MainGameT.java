package aventurasMarcoyLuis;

import aventurasMarcoyLuis.controller.GameController;
import aventurasMarcoyLuis.model.personajes.Luis;
import aventurasMarcoyLuis.model.personajes.Marco;

import java.io.PrintStream;

// No pescar, usado para probar batallas.
public class MainGameT {
    /**
     * Metodo que ejecuta una batalla entera y setea los parametros iniciales
     * @param args
     */
    public static void main(String args[]) {
        GameController gameController = new GameController();
        gameController.newLuisPlayer();
        gameController.newMarcoPlayer();
        playGame(gameController, System.out);
    }

    /**
     * Metodo que va pasando los turnos hasta que la batalla acabe
     * @param game
     */
    public static void playGame(GameController game, PrintStream out) {
        out.println("Welcome To The: Aventuras de Marco y Luis ");
        game.tryToStartNewRound();
        do {
            game.tryToSelectAction();
        } while (game.notOver());
    }

}

