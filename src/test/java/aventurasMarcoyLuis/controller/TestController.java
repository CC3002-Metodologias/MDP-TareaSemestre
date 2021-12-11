package aventurasMarcoyLuis.controller;

import aventurasMarcoyLuis.AttackType;
import aventurasMarcoyLuis.Items.HoneySyrup;
import aventurasMarcoyLuis.Items.RedMushroom;
import aventurasMarcoyLuis.interfaces.Ienemy;
import aventurasMarcoyLuis.interfaces.Ipersonaje;
import aventurasMarcoyLuis.interfaces.Items;
import aventurasMarcoyLuis.personajes.Luis;
import aventurasMarcoyLuis.personajes.Marco;
import aventurasMarcoyLuis.personajes.abstract_clases.AbstractEnemy;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidAttackException;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidTransitionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestController {

    GameController gameController;
    PlayerIn pLuis;
    PlayerIn pMarco;
    Marco marco;
    Luis luis;
    PrintStream out = new PrintStream(new NullOutputStream());

    @BeforeEach
    public void setUp(){
        marco = new Marco();
        luis = new Luis();
        gameController = new GameController();
        gameController.setOut(out);
    }




    /**
     * Testeo inicial de creacion del baúl y players
     */
    @Test
    public void constructorTest() {


        //Se crean los jugadores principales
        pMarco = gameController.newLuisPlayer();
        pLuis = gameController.newLuisPlayer();

        //Revisamos la correcta creación de estos.
        Assertions.assertEquals(PlayerIn.class, pMarco.getClass());
        Assertions.assertEquals(PlayerIn.class, pLuis.getClass());

        //Comprobamos que se haya creado la lista de jugadores, y que contengan los jugadores recién creados.
        assert gameController.getPlayersIn().contains(pMarco);
        assert gameController.getPlayersIn().contains(pLuis);

        //testeamos la creación del Baúl, el cual debe ser un arreglo vacío de Items.
        Assertions.assertEquals(new ArrayList<Items>(), gameController.getBaul());



    }

    /**
     * Testeo de inicio de la primera ronda, donde se crean nuevos items y 3 enemigos, los que
     * se añaden al Baúl y la lista de turnos de enmigos(EnemyList) respectivamente. Se testea la obtención del Baúl también.
     */
    @Test
    public void startRoundTest() throws InvalidTransitionException {
        gameController.newMarcoPlayer();
        gameController.newLuisPlayer();

        /* Damos el set inicial de la ronda 1, por lo cual se deben crear y agregar Items en el baúl, además de
         * la creación de 3 enemigos random, y agregar a la lista de enemigos de la ronda
         */
        gameController.start_newRound();

        //Test de creación y incorporación al baúl de los 3 Items tipo HoneySyrup
        Assertions.assertEquals(3, contandorObj(gameController.getBaul(), HoneySyrup.class));
        //Test de creación y incorporación al baúl de los 3 Items tipo RedMushroom
        Assertions.assertEquals(3, contandorObj(gameController.getBaul(), RedMushroom.class));
        //Test de creación y incorporación a la lista de turnos de Enemigos de los 3 Ienemy
        Assertions.assertEquals(3, contandorObj(gameController.getEnemyList(), AbstractEnemy.class));

    }

    /**
     * Testeo de turnos(Implementacion, obtencion de todos personajes del turno, obtencion del personaje del turno actual y siguiente),
     * seleccionar Accion y ejecución del turno de los enemigos.
     */
    @Test
    public void turnsTest() throws InvalidTransitionException {
        pLuis = new PlayerIn(luis, "P\nP");
        pMarco = new PlayerIn(marco, "P\nP");
        gameController.addPlayer(pMarco).addPlayer(pLuis);
        gameController.setOut(out);
        gameController.start_newRound();

        //testeamos que se puedan obtener todos los personajes activos en la ronda (personajes del turno)
        ArrayList<Ipersonaje> result = new ArrayList<Ipersonaje>();
        result.add(marco); result.add(luis);
        result.addAll(gameController.getEnemyList());
        Assertions.assertEquals(result, gameController.getAllPersOfTurns());
        //--------//
        double lifeLuis = luis.getHPactual(), lifeMarco = marco.getHPactual();
        //revisamos que el turno inicial sea de Marco y a su vez que se pueda obtener el turno actual.
        assert (Objects.equals(gameController.getOwnerTurn(), pMarco));

        gameController.tryToSelectAction();
        //revisamos que el turno ahora sea de Luis
        assert (Objects.equals(gameController.getOwnerTurn(), pLuis));

        gameController.tryToSelectAction();
        //En este momento debería correr el turno de los enemigos. Revisamos que los enemigos hayan atacado haciendo algo de daño.
        assert (pMarco.getPlayer().getHPactual()< lifeMarco || pLuis.getPlayer().getHPactual()<lifeLuis);

        //revisamos que el turno ahora sea nuevamente de Marco, si este no es KO. Si lo esta deberia ser de Luis.
        if (!marco.isKO()){
            assert (Objects.equals(gameController.getOwnerTurn(), pMarco));
        } else {
            assert (Objects.equals(gameController.getOwnerTurn(), pLuis));
        }


    }
    /**
     * Testeo de uso de Items.
     */
    @Test
    public void usingItemTest() throws InvalidTransitionException {
        pLuis = new PlayerIn(luis);
        pMarco = new PlayerIn(marco);
        gameController.addPlayer(pMarco).addPlayer(pLuis);
        gameController.setOut(out);
        gameController.start_newRound();

        //Le bajamos la vida y los FPs a los jugadores para que se pueda apreciar el efecto de usar los items.
        marco.setHP(marco.getHPmax()*0.9);
        luis.setFPactual(luis.getFPmax()-3);


        Items i1 = gameController.getBaul().get(3);
        /* Ahora pMarco deberia usar el Item correspondiente al indice 3 de baúl. Entonces este item ya no deberia estar
         en el Baúl. Esto es un RedMushroom
         */
        gameController.playerUseItem(pMarco, 3);
        Assertions.assertFalse(gameController.getBaul().contains(i1));
        Assertions.assertEquals(marco.getHPmax(), marco.getHPactual());

        Items i2 = gameController.getBaul().get(0);
        /* Ahora estando en el turno del segundo player, deberia usar el item con el indice 0 dentro del baúl.
           Corresponde a un HoneySyrup.
         */
        gameController.playerUseItem(pLuis, 0);
        Assertions.assertFalse(gameController.getBaul().contains(i2));
        Assertions.assertEquals(luis.getFPmax(), luis.getFPactual());


    }
    @Test
    public void useItemWithInput(){
        pLuis = new PlayerIn(luis, "I\n1\n");
        pMarco = new PlayerIn(marco,"I\n4\n");
        gameController.addPlayer(pMarco).addPlayer(pLuis);
        gameController.setOut(out);

        //Le bajamos la vida y los FPs a los jugadores para que se pueda apreciar el efecto de usar los items.
        marco.setHP(marco.getHPmax()*0.9);
        luis.setFPactual(luis.getFPmax()-3);

        gameController.tryToStartNewRound();

        Items i1 = gameController.getBaul().get(3);
        gameController.tryToSelectAction();
        Assertions.assertFalse(gameController.getBaul().contains(i1));
        Assertions.assertEquals(marco.getHPmax(), marco.getHPactual());

        Items i2 = gameController.getBaul().get(0);
        gameController.tryToSelectAction();
        Assertions.assertFalse(gameController.getBaul().contains(i2));
        Assertions.assertEquals(luis.getFPmax(), luis.getFPactual());

    }
    /**
     * Testeo de Ataques de Iplayer a enemigos ejecutados por el controller.
     * @throws InvalidTransitionException
     * @throws InvalidAttackException
     */
    @Test
    public void attackingTest() throws InvalidTransitionException, InvalidAttackException {
        pLuis = new PlayerIn(luis);
        pMarco = new PlayerIn(marco);
        gameController.addPlayer(pMarco).addPlayer(pLuis);
        gameController.setOut(out);
        gameController.setSeeds(4,1);
        /*
        Con esta semilla los enemigos creados corresponden a :
            0:Spiny | 1:Goomba | 2:Boo
         */

        //Empezamos una nueva ronda.
        gameController.start_newRound();

        //Obtenemos al primer enemigo que ataqueremos, corresponde a un Boo. También guardamos su vida antes de ser atacado.
        Ienemy enem1 = gameController.getEnemyList().get(2);
        Double hpiEnem1 = enem1.getHPactual();

        gameController.playerAttack(pMarco,2, AttackType.SALTO);
        //pMarco deberia haber atacado a enem1 con Salto
        Assertions.assertEquals(hpiEnem1 - marco.getAtaque()/enem1.getDefensa(), enem1.getHPactual());

        //Volvemos a obtener su vida actual
        hpiEnem1 = enem1.getHPactual();

        //pLuis intenta atacar a un Boo por lo que debería tirar un InvalidAttackException
        try {
            gameController.playerAttack(pLuis,2, AttackType.SALTO);
            //Si realiza el ataque, entonces esta mal.
            assert (false);
        } catch (InvalidAttackException e){
            //No realizo el ataque y arrojo un InvalidAttackException, esta correcto.
            assert true;
        }
        Assertions.assertEquals(hpiEnem1,enem1.getHPactual());

        //Obtenemos al segundo enemigo que ataqueremos, corresponde a un Goomba. También guardamos su vida antes de ser atacado.
        Ienemy enem2 = gameController.getEnemyList().get(1);
        Double hpiEnem2 = enem2.getHPactual();

        gameController.playerAttack(pMarco,1, AttackType.MARTILLO);
        //pMarco deberia haber atacado a enem1 con Salto
        Assertions.assertEquals(hpiEnem2 - 1.5*marco.getAtaque()/enem2.getDefensa(), enem2.getHPactual());

        //Obtenemos al tercer enemigo que ataqueremos, corresponde a un Spiny. También guardamos su vida antes de ser atacado.
        Ienemy enem3 = gameController.getEnemyList().get(0);
        Double hpiEnem3 = enem3.getHPactual();

        gameController.playerAttack(pLuis,0, AttackType.MARTILLO);
        //pMarco deberia haber atacado a enem1 con Salto
        Assertions.assertEquals(hpiEnem3 - 1.5*luis.getAtaque()/enem3.getDefensa(), enem3.getHPactual());

    }


    /**
     * Testeo de quitar un personaje enemigo cuando este esta KO
     */
    @Test
    public void enemKORemoveTest() throws InvalidAttackException {
        //Configuramos las stats de luis y marco para que sean capaces de KO a un enem de un solo ataque
        luis.setAtaque(50);
        marco.setAtaque(50);
        //------------------//
        pLuis = new PlayerIn(luis);
        pMarco = new PlayerIn(marco);
        gameController.addPlayer(pMarco).addPlayer(pLuis);
        gameController.setOut(out);
        gameController.setSeeds(4,1);
        /* Con esta semilla los enemigos creados corresponden a :
            0:Spiny | 1:Goomba | 2:Boo
         */
        gameController.tryToStartNewRound();

        Ienemy boo = gameController.getEnemyList().get(2);
        assert (gameController.getAllPersOfTurns().contains(boo));
        //marco ataca Boo con salto, y con el ataque en 50 deberia Knockiarlo.
        gameController.playerAttack(pMarco, 2, AttackType.SALTO);
        assert (!gameController.getEnemyList().contains(boo));
        assert (!gameController.getAllPersOfTurns().contains(boo));

        Ienemy goomba = gameController.getEnemyList().get(1);
        assert (gameController.getAllPersOfTurns().contains(goomba));
        //Ahora luis ataca goomba con salto, y con el ataque en 50 deberia Knockiarlo.
        gameController.playerAttack(pLuis, 1, AttackType.SALTO);
        assert (!gameController.getEnemyList().contains(goomba));
        assert (!gameController.getAllPersOfTurns().contains(goomba));
    }

    /**
     * Testeo de quitar un personaje principal cuando este esta KO
     */
    @Test
    public void playerKORemoveTest() throws InvalidAttackException {
        //Configuramos las stats de luis y marco para que sean capaces de KO a un enem de un solo ataque
        //Ahora además les bajamos las stats de vida para que puedan ser KO de un solo ataque
        luis.setAtaque(50);
        marco.setAtaque(50);
        luis.setHPmax(1);
        marco.setHPmax(1);
        //------------------//
        pLuis = new PlayerIn(luis, "A\nS\n2\n");
        pMarco = new PlayerIn(marco,"A\nS\n3\n");
        gameController.addPlayer(pMarco).addPlayer(pLuis);
        gameController.setOut(out);
        gameController.setSeeds(4,1);
        /* Con esta semilla los enemigos creados corresponden a :
            0:Spiny | 1:Goomba | 2:Boo
         */
        gameController.tryToStartNewRound();
        gameController.tryToSelectAction();
        gameController.tryToSelectAction();
        /* En este momento deberia ejecutarse el turno de los enemigos, pero solo hay un enemigo vivo y es Spiny.
           Este deberia knockiar a un Player(Marco o Luis) debido a la baja vida que fue configurada */
        assert (!gameController.getPlayerInList().contains(pMarco) | !gameController.getPlayerInList().contains(pLuis));
        assert (!gameController.getAllPersOfTurns().contains(marco) | !gameController.getAllPersOfTurns().contains(luis));
        assert (!gameController.iLost());
    }

    /**
     * Testeamos que al Knockquiarse todos los jugadores principales, el controlodar termine la partida y la
     * le como perdida.
     */
    @Test
    public void lostTest() {
        //Configuramos la vida de personajes principales para que puedan ser KO de un solo ataque enemigo
        luis.setHPmax(1);
        marco.setHPmax(1);
        //------------------//
        pLuis = new PlayerIn(luis, "P\n");
        pMarco = new PlayerIn(marco, "P\n");
        gameController.addPlayer(pMarco).addPlayer(pLuis);
        gameController.setOut(out);
        gameController.setSeeds(23, 47);

        gameController.tryToStartNewRound();
        gameController.tryToSelectAction();
        gameController.tryToSelectAction();
        /*
        Ahora dado que la accion elejida por los dos jugadores es Pasar Turno, los enemigos deberian atacar Knockiando a
        los 2, tanto a marco como a luis .
         */
        assert !gameController.notOver();
        assert gameController.iLost();
    }

    /**
     * Testeo de que al Knockiar a todos los enemigos de la ronda se comienze una nueva.
     * Se testea por lo tanto tambien el upgrade de stats de los jugadores principales y obtener la ronda.
     */
    @Test
    public void winRoundTest() throws InvalidAttackException {
        //Configuramos las stats de luis y marco para que sean capaces de KO a un enem de un solo ataque
        luis.setAtaque(50);
        marco.setAtaque(50);
        //------------------//
        pLuis = new PlayerIn(luis,"A\nS\n2\nA\nM\n1\n");
        pMarco = new PlayerIn(marco,"A\nS\n3\nA\nM\n1\n");
        gameController.addPlayer(pMarco).addPlayer(pLuis);
        gameController.setOut(out);
        gameController.setSeeds(4,803925247);
        /* Con esta semilla los enemigos creados corresponden a :
            1:Spiny | 2:Goomba | 3:Boo
         */
        gameController.tryToStartNewRound();
        gameController.tryToSelectAction();
        gameController.tryToSelectAction();
        gameController.tryToSelectAction();

        assert  gameController.getRound()==2;
        assert contandorObj(gameController.getBaul(),HoneySyrup.class)==4;
        assert contandorObj(gameController.getBaul(),RedMushroom.class)==4;
        assert contandorObj(gameController.getEnemyList(),AbstractEnemy.class)==3;
        assert marco.getNivel()==2 && luis.getNivel()==2;
    }

    @Test
    public void winTest(){
        /*
        De la misma manera del test anterior configuramos lo necesario para ganar una ronda de la forma más rápida posible.
        Ahora además cambiamos el número de la ronda a 5, para que cuando se gane la ronda, el controlodor entienda tambíen que
        se gano la partida. Esto lo hacemos, pues las demas rondas funcionan igual que la primera.
         */



        //Configuramos las stats de luis y marco para que sean capaces de KO a un enem de un solo ataque
        luis.setAtaque(50);
        marco.setAtaque(50);
        //------------------//
        pLuis = new PlayerIn(luis,"A\nS\n2\nA\nM\n1\n");
        pMarco = new PlayerIn(marco,"A\nS\n3\nA\nM\n1\n");
        gameController.addPlayer(pMarco).addPlayer(pLuis);
        gameController.setOut(out);
        gameController.setSeeds(4,803925247);
        /* Con esta semilla los enemigos creados corresponden a :
            1:Spiny | 2:Goomba | 3:Boo
         */
        gameController.tryToStartNewRound();
        gameController.Round = 5;
        gameController.tryToSelectAction();
        gameController.tryToSelectAction();
        gameController.tryToSelectAction();

        assert gameController.isWin();
        assert !gameController.notOver();

    }

    /**
     * @param list
     * @param c
     * @return el numero de objetos del tipo c en la lista list.
     */
    public int contandorObj(List list, Class c){
        int i = 0;
        for(Object o: list){
            if(o.getClass()==c | o.getClass().getGenericSuperclass()==c) i++;
        }
        return i;
    }

    /*
    @Test
    public void mainPrueba(){
        Random random = new Random();
        Random seed = new Random();
        int seedR = seed.nextInt();
        random.setSeed(seedR);
        random.nextInt(2);
        System.out.println(random.nextInt(4)+ " Seed = "+ seedR);
    }

     */

}
