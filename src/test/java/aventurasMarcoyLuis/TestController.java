package aventurasMarcoyLuis;

import aventurasMarcoyLuis.Items.HoneySyrup;
import aventurasMarcoyLuis.Items.RedMushroom;
import aventurasMarcoyLuis.controller.BattleController;
import aventurasMarcoyLuis.controller.NullOutputStream;
import aventurasMarcoyLuis.controller.PlayerIn;
import aventurasMarcoyLuis.interfaces.Items;
import aventurasMarcoyLuis.personajes.Luis;
import aventurasMarcoyLuis.personajes.Marco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class TestController {

    BattleController battleController;
    PlayerIn pLuis;
    PlayerIn pMarco;
    Marco marco;
    Luis luis;

    @BeforeEach
    public void setUp(){
        marco = new Marco();
        luis = new Luis();
    }


    /**
     * Testeo inicial de creacion de Items, enemigos y players
     */
    @Test
    public void setBattle(){

        pMarco = new PlayerIn(marco);
        pLuis = new PlayerIn(luis);
        battleController = new BattleController(pMarco, pLuis);
        battleController.setOut( new PrintStream(new NullOutputStream()));
        Items honey = new HoneySyrup();
        Items rMushroom = new RedMushroom();

        //testeamos la creación del Baúl, el cual debe ser un arreglo vacío de Items.
        Assertions.assertEquals(new ArrayList<Items>(), battleController.getBaul());

        //damos el set inicial de la ronda 1, por lo cual se deben crear y agregar Items en el baúl, además de
        //la creación de 3 enemigos random, y agregar a la lista de enemigos de la ronda
        battleController.round1();
        Assertions.assertEquals(honey.getClass(), battleController.getBaul().get(0).getClass());
        Assertions.assertEquals(rMushroom.getClass(), battleController.getBaul().get(3).getClass());
        assert (battleController.getEnemyList().size() == 3);
        assert (battleController.getEnemyList().get(1)!= null);

        //testeamos la lista PlayerList de PlayerIn activos en la ronda
        assert (battleController.getPlayerList().size() == 2);
        assert (battleController.getPlayerList().contains(pMarco));
        assert (battleController.getPlayerList().contains(pLuis));

    }


    /**
     * Testeo de turnos, uso de Items.
     */
    @Test
    public void roundTest() throws IOException {
        pLuis = new PlayerIn(luis, "P\nI1\n");
        pMarco = new PlayerIn(marco, "P\nI3\n");

        BattleController battleController = new BattleController(pMarco, pLuis);
        battleController.setOut( new PrintStream(new NullOutputStream()));
        battleController.round1();

        //revisamos que el turno inicial sea de Marco
        assert (battleController.getdTurno() == "Marco");
        battleController.update();
        //luego debe ser de Luis
        assert (battleController.getdTurno() == "Luis");
        battleController.update();
        //turno de los enemigos, los cuales haran sus ataques, cada uno a jugador principal al azar
        assert (battleController.getdTurno() == "Enemigos turno");

        battleController.update();
        //luego del turno de los enemigos, revisamos que al menos Player haya recibido daño
        assert (pMarco.getPlayer().getHPactual() < pMarco.getPlayer().getHPmax() ||
                pLuis.getPlayer().getHPactual() < pLuis.getPlayer().getHPmax());

        //si el jugador Marco no esta KO, deberia ser su turno
        if (!pMarco.getPlayer().isKO()){
            assert (battleController.getdTurno() == "Marco");
        }

        battleController.update();
        //Marco debería haber usado el item correspondiente al indice 1 del baul (Un HoneySyrup)
        assert (battleController.getBaul().size() == 5);

        battleController.update();
        //Luis debería haber usado el item correspondiente al indice 3 del baul (Un Red Mushroom)
        assert (battleController.getBaul().size() == 4);


    }


    /**
     * Testeo de un ronda completa, ataques, Enemigos KO, aumento de nivel.
     */
    @Test
    public void roundTest2() throws IOException {
        pLuis = new PlayerIn(luis, "AS1\n");
        pMarco = new PlayerIn(marco, "AS1\nAS0\nAM1\n");

        double hpmaxMarco0 = marco.getHPmax();
        double atkMarco0 = marco.getAtaque();
        double defLuis0 = luis.getDefensa();
        double nvlLuis0 = luis.getNivel();

        BattleController battleController = new BattleController(pMarco, pLuis);
        battleController.setOut( new PrintStream(new NullOutputStream()));
        battleController.setSeeds(55,23);
        battleController.round1();
        //con esta semilla se crean 3 enemigos al azar de tipo Goomba

        battleController.update();
        //el enemigo atacado pMarco deberia estar KO y fuera de la lista de enemigos de la ronda (EnemyList)
        assert (battleController.getEnemyList().size() == 2);

        battleController.update();
        //el enemigo atacado pLuis deberia estar KO y fuera de la lista de enemigos de la ronda (EnemyList)
        assert (battleController.getEnemyList().size() == 1);

        //turno del enemigo
        battleController.update();

        battleController.update();
        //el ultimo enemigo restante deberia quedar KO y asi ganar la ronda avando a la siguiente ronda
        Assertions.assertEquals(2, battleController.getRound());

        //dado que se avanzo de ronda, los players deberian aumentar de nivel junto con sus estadisticas
        Assertions.assertEquals(nvlLuis0+1, pLuis.getPlayer().getNivel());
        Assertions.assertEquals(hpmaxMarco0*1.15, pMarco.getPlayer().getHPmax());
        Assertions.assertEquals(atkMarco0*1.15, pMarco.getPlayer().getAtaque());
        Assertions.assertEquals(defLuis0*1.15, pLuis.getPlayer().getDefensa());
    }


    /**
     * Testeo de una batalla completa ganada.
     */
    @Test
    public void winTest() throws IOException {
        /*IMPORTANTE: solo funciona con las stats tanto de Marco como de Luis tq puedan dejar KO a cualquier enemigo de
        un solo ataque, y con HPmax inicial de ambos sobre 10*/

        pLuis = new PlayerIn(luis, "AS1\nAM0\nAM0\nAM0\nAM0\nAS0\nAM1\nAS0\nAM1\nAM0");
        pMarco = new PlayerIn(marco, "AS1\nAS0\nAS0\nAM0\nAS1\nAS3\nAS1\nAS1\nAS0\nAM0\nAS2\nAS1\nAM1");

        BattleController battleController = new BattleController(pMarco, pLuis);
        battleController.setOut( new PrintStream(new NullOutputStream()));
        battleController.setSeeds(55,23);
        battleController.round1();

        assert (!battleController.isWin());

        for(int i=1; i<=32; i++){
            battleController.update();
        }

        assert (battleController.isWin());
        assert (!battleController.notOver());
    }
}
