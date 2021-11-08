package aventurasMarcoyLuis.controller;

import aventurasMarcoyLuis.Luis;
import aventurasMarcoyLuis.Marco;
import aventurasMarcoyLuis.interfaces.Ipersonaje;

import java.io.IOException;

public class GameController {

    /**
     * Metodo que ejecuta una batalla entera y setea los parametros iniciales
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        Ipersonaje luis = new Luis();
        Ipersonaje marco = new Marco();
        PlayerIn pLuis = new PlayerIn(luis);
        PlayerIn pMarco = new PlayerIn(marco);
        BattleController battleController = new BattleController(pMarco, pLuis);
        battleController.setSeeds(19,1);
        battleController.round1();
        playGame(battleController);
    }

    /**
     * Metodo que va pasando los turnos hasta que la batalla acabe
     * @param game
     */
    public static void playGame(BattleController game) {
        try {
            do {
                game.update();
            } while(game.notOver());
            System.out.println("game over!");
        } catch (IOException err) {
            System.out.println("game terminated!");
        }
    }

}

