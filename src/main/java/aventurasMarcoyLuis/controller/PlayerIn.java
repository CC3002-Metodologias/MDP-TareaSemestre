package aventurasMarcoyLuis.controller;

import aventurasMarcoyLuis.AttackType;
import aventurasMarcoyLuis.interfaces.Ipersonaje;

import java.io.*;
import java.util.List;

public class PlayerIn {
    /**
     * Variable que almacena un Ipersonaje
     */
    private Ipersonaje player;
    private final BufferedReader in;

    /**
     * Constructor to specify an alternative source of moves
     */
    public PlayerIn(Ipersonaje p1, BufferedReader initIn){
        player = p1;
        in = initIn;
    }

    /**
     * Contructor utilizado normalmente
     * @param p1
     */
    public PlayerIn(Ipersonaje p1){
        this(p1, new BufferedReader(new InputStreamReader(System.in)));
    }

    /**
     * Contructor para tests
     */
    public PlayerIn(Ipersonaje p1, String actions){
        this(p1, new BufferedReader(new StringReader(actions)));
    }

    /**
     * retorna el Ipersonaje asociado al PlayerIn
     */
    public Ipersonaje getPlayer(){
        return player;
    }

    /**
     * Se borra de la playerList que entra como parametro
     */
    public void deletePList(List<PlayerIn> playersList) {
        playersList.remove(this);
    }

    /**
     * Ejecuta el turno de un personaje principal con la entrada input correspondiente
     * Por el momento, la entrada debe ser un String con la primera letra indicando la accion, la segunda que tipo de
     * ataque /el item que quiere usar(indice), la tercera solo en caso de ataque a quien va dirido
     */
    public void turn(BattleController battleController) throws IOException {

        String line;
        do {
            System.out.print("Select Action ");
            line = in.readLine();
            if (line == null) {
                throw new IOException("end of input");
            }
        } while(line.length() > 3);

        char action = line.charAt(0);
        if (action == 'A'){
            char typeA = line.charAt(1);
            char enem = line.charAt(2);
            attack(typeA, enem, battleController);
        }
        if (action == 'I'){
            char sItem = line.charAt(1);
            useItem(sItem, battleController);
        }
        if (action == 'P'){
            //next turn
        }

    }

    /**
     * Metodo que se llama cuando un jugador quiere usar un Item, para ser resolvido por battleController
     */
    private void useItem(char sItem, BattleController battleController) throws IOException {
        int i = Character.getNumericValue(sItem);
        battleController.playerUseItem(this, i);
    }

    /**
     * Metodo que se llama cuando un jugador quiere atacar a un enemigo, para ser resolvido por battleController
     */
    private void attack(char typeA, char enem, BattleController battleController) throws IOException {
        int i = Character.getNumericValue(enem);
        if (typeA == 'M'){
            AttackType at = AttackType.MARTILLO;
            battleController.playerAttack(this, i, at);
        }
        else  if (typeA == 'S'){
            AttackType at = AttackType.SALTO;
            battleController.playerAttack(this, i, at);
        }
        else {
            throw new IOException("Attack's type don't exist");
        }
    }
}


