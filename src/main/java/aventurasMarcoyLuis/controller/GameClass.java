package aventurasMarcoyLuis.controller;

import aventurasMarcoyLuis.Luis;
import aventurasMarcoyLuis.Marco;
import aventurasMarcoyLuis.interfaces.Ipersonaje;

import java.io.IOException;

public class GameClass {

    public static void main(String args[]) throws IOException {
        Ipersonaje luis = new Luis();
        Ipersonaje marco = new Marco();
        PlayerIn pLuis = new PlayerIn(luis);
        PlayerIn pMarco = new PlayerIn(marco);
        Battle battle = new Battle(pMarco, pLuis);
        battle.round1();
    }

}

