package aventurasMarcoyLuis.controller;

import aventurasMarcoyLuis.AttackType;
import aventurasMarcoyLuis.interfaces.Ipersonaje;

import java.io.*;
import java.util.List;

public class PlayerIn {
    private Ipersonaje player;
    private final BufferedReader in;

    //Constructor to specify an alternative source of moves
    public PlayerIn(Ipersonaje p1, BufferedReader initIn){
        player = p1;
        in = initIn;
    }

    //Normal constructor
    public PlayerIn(Ipersonaje p1){
        this(p1, new BufferedReader(new InputStreamReader(System.in)));
    }

    //Constructor for tests
    public PlayerIn(Ipersonaje p1, String actions){
        this(p1, new BufferedReader(new StringReader(actions)));
    }

    public Ipersonaje getPlayer(){
        return player;
    }

    public void deletePList(List<PlayerIn> playersList) {
        playersList.remove(this);
    }

    public void turn(Battle battle) throws IOException {
        String line;
        line = in.readLine();
        //if (line == null) {
        //    throw new IOException("end of input");
            
        char action = line.charAt(0);
        if (action == 'A'){
            char typeA = line.charAt(1);
            char enem = line.charAt(2);
            attack(typeA, enem, battle);
        }
        if (action == 'I'){
            char sItem = line.charAt(1);
            useItem(sItem, battle);
        }
        if (action == 'P'){
            //next turn
        }

    }

    private void useItem(char sItem, Battle battle) {
        int i = sItem;
        battle.playerUseItem(this, i);
    }

    private void attack(char typeA, char enem, Battle battle) throws IOException {
        int i = enem;
        if (typeA == 'M'){
            AttackType at = AttackType.MARTILLO;
            battle.playerAttack(this, i, at);
        }
        else  if (typeA == 'S'){
            AttackType at = AttackType.SALTO;
            battle.playerAttack(this, i, at);
        }
        else {
            throw new IOException("Attack's type don't exist");
        }
    }
}


