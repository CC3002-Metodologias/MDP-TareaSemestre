package aventurasMarcoyLuis.controller;

import aventurasMarcoyLuis.AttackType;
import aventurasMarcoyLuis.interfaces.Iplayer;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidAttackException;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidInputException;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidOptionException;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidTransitionException;


import java.io.*;

/**
 * Clase que funciona como controlodor de un personaje principal
 */
public class PlayerIn {
    /**
     * Variable que almacena un Ipersonaje
     */
    private final Iplayer player;
    private final BufferedReader in;
    private PrintStream out = System.out;


    /**
     * Constructor to specify an alternative source of moves
     */
    public PlayerIn(Iplayer p1, BufferedReader initIn){
        player = p1;
        player.setPlayerIn(this);
        in = initIn;
    }

    /**
     * Contructor utilizado normalmente
     * @param p1
     */
    public PlayerIn(Iplayer p1){
        this(p1, new BufferedReader(new InputStreamReader(System.in)));
    }

    /**
     * Contructor para tests
     */
    public PlayerIn(Iplayer p1, String actions){
        this(p1, new BufferedReader(new StringReader(actions)));
    }

    /**
     * Seteamos el PrintStream de la clase, para poder dejar un Null PrintStream
     */
    public void setOut(PrintStream printStream){
        out = printStream;
    }
    /**
     * retorna el Ipersonaje asociado al PlayerIn
     */
    public Iplayer getPlayer(){
        return player;
    }

    /**
     * Dado un Input selecciona la acción que realizará el jugador que controla el turno.
     * @param gameController
     * @throws InvalidOptionException
     * @throws InvalidTransitionException
     * @throws IOException
     */
    public void selectAction(GameController gameController) throws InvalidTransitionException, IOException, InvalidInputException {
        out.println("These are the baul and the enemies en this moment:");
        gameController.printBaul();
        gameController.printEnemyList();

        char action = inputChar("Select an action: A for Attack, I to use an Item and P to Pass turn: ");

        if (action == 'A'){
            gameController.getPhase().toSetAttackPhase(this);
            gameController.tryToSelectAttack(this);
        }
        else if (action == 'I'){
            gameController.getPhase().toChoosingItemPhase(this);
            gameController.tryToSelectItem(this);
        }
        else if (action == 'P'){
            gameController.getPhase().toEndTurnPhase();
            gameController.tryToEndTurn();
        }
        else{
            throw new InvalidInputException("Not valid Option. Write 'A' to Attack or 'I' to use an Item or 'P' to Pass turn ");
        }


    }

    /**
     * Dado un Input selecciona el tipo de ataque para luego seleccionar al enemigo.
     * @param gameController
     * @throws InvalidOptionException
     * @throws InvalidTransitionException
     * @throws IOException
     */
    public void selectAttack(GameController gameController) throws IOException, InvalidInputException {

        char action = inputChar("You choose Attack. Write M to use Martillo or S to Attack for Salto: ");

        if (action == 'M'){
            gameController.tryToSelectVictim(this, AttackType.MARTILLO);
        }
        else if (action == 'S'){
            gameController.tryToSelectVictim(this, AttackType.SALTO);
        }
        else{
            throw new InvalidInputException("Not valid Option. Write 'M' for Attack with Martillo or " +
                    "'S' for Attack with Salto");
        }


    }

    /**
     * Dado un Input selecciona el enemigo al que el jugador atacará.
     * @param gameController
     * @throws InvalidOptionException
     * @throws InvalidTransitionException
     * @throws IOException
     */
    public void selectVictimAttack(GameController gameController, AttackType attackType) throws IOException, InvalidTransitionException, InvalidInputException, InvalidAttackException {

        char victim = inputChar("Indica al enemigo que quieres atacar escribiendo la posicion de este en la lista : ");
        int iVictim = Character.getNumericValue(victim)-1;

        if (iVictim < gameController.getEnemyList().size() && iVictim>= 0){
            gameController.getPhase().toAttackingPhase(this);
            gameController.playerAttack(this, iVictim,attackType);

            if (gameController.notOver()){
                gameController.getPhase().toEndTurnPhase();
                gameController.tryToEndTurn();
            }
        }
        else throw new InvalidInputException("Out of range");

    }

    /**
     * Dado un Input selecciona el item que desea usar el jugador que controla el turno.
     * @param gameController
     * @throws InvalidOptionException
     * @throws InvalidTransitionException
     * @throws IOException
     */
    public void selectItem(GameController gameController) throws IOException, InvalidTransitionException, InvalidInputException {

        char item = inputChar("Indica el Item que quieres usar escribiendo la posicion de este en la lista : ");
        int idItem = Character.getNumericValue(item)-1;

        if (idItem < gameController.getBaul().size() && idItem >= 0){
            gameController.getPhase().toUsingItemPhase(this);
            gameController.playerUseItem(this, idItem);
            gameController.getPhase().toEndTurnPhase();
            gameController.tryToEndTurn();
        }
        else throw new InvalidInputException("Out of Baul's range");
    }

    /**
     * Metodo para recibir un input que retorna el primer caracter de este
     * @param message : mensage que se verá en pantalla antes de dar paso al input
     * @return Primer caratater del input
     * @throws IOException
     */
    public char inputChar(String message) throws IOException {
        String line;
        do {
            out.print(message);
            line = in.readLine();
            if (line == null) {
                throw new IOException("end of input");
            }
        } while(line.length() != 1);
        return line.charAt(0);
    }


}


