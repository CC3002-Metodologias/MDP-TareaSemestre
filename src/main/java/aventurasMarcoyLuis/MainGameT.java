package aventurasMarcoyLuis;

import aventurasMarcoyLuis.controller.GameController;
import aventurasMarcoyLuis.controller.PlayerIn;
import aventurasMarcoyLuis.personajes.Luis;
import aventurasMarcoyLuis.personajes.Marco;

import java.io.PrintStream;

public class MainGameT {
    private final Luis luis= new Luis();
    private final Marco marco = new Marco();
    /**
     * Metodo que ejecuta una batalla entera y setea los parametros iniciales
     * @param args
     */
    public static void main(String args[]) {
        final Luis luis= new Luis();
        final Marco marco = new Marco();
        GameController gameController = new GameController();
        luis.setAtaque(50);
        marco.setAtaque(50);
        //------------------//
        PlayerIn pLuis = new PlayerIn(luis, "A\nS\n1\nA\nM\n1\n");
        PlayerIn pMarco = new PlayerIn(marco,"A\nS\n3\nA\nM\n1\n");
        gameController.addPlayer(pMarco).addPlayer(pLuis);
        gameController.setSeeds(4,803925247);

        playGame(gameController, System.out);
    }

    /**
     * Metodo que va pasando los turnos hasta que la batalla acabe
     * @param game
     */
    public static void playGame(GameController game, PrintStream out) {
        out.println("Welcome To The: Aventuras de Marco y Luis ");
        game.tryToStartNewRound();
        /*
        do {
            game.tryToSelectAction();
        } while (game.notOver());

         */
        game.tryToSelectAction();
        game.tryToSelectAction();
        game.tryToSelectAction();
        System.out.println(game.getRound());

    }

}

