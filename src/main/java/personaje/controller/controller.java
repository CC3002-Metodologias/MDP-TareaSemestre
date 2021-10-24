package personaje.controller;

import personaje.*;
import personaje.Items.HoneySyrup;
import personaje.Items.RedMushroom;
import personaje.interfaces.Ienemy;
import personaje.interfaces.Ipersonaje;
import personaje.interfaces.Items;
import java.util.List;

public class controller {
    public int kills;
    public int score;
    public int round;
    public personaje.Marco marco;
    public personaje.Luis luis;
    public personaje.Spiny spiny;
    public personaje.Boo boo;
    public personaje.Goomba goomba;
    public List<Ipersonaje> playersList;
    public List<Ienemy> enemyList;
    public List<Items> baul;

    public void controller(){
        kills = 0;
        score = 0;
        round = 1;
        iniciarPersonajesItems();

    }

    public int getKills() {
        return kills;
    }
    public int getScore() {
        return score;
    }
    public int getRound() {
        return round;
    }
    public void setKills(int kills) {
        this.kills = kills;
    }
    public void setRound(int round) {
        this.round = round;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public void iniciarPersonajesItems(){
        luis = new Luis();
        marco = new Marco();
        boo = new Boo();
        goomba = new Goomba();
        spiny = new Spiny();

        playersList.add(marco);
        playersList.add(luis);
        enemyList.add(boo);
        enemyList.add(goomba);
        enemyList.add(spiny);

        this.newHoney();
        this.newHoney();
        this.newHoney();
        this.newMushroom();
        this.newMushroom();
        this.newMushroom();
    }

    public void newMushroom(){
        RedMushroom mushroom = new RedMushroom();
        baul.add(mushroom);
    }
    public void newHoney(){
        HoneySyrup honey = new HoneySyrup();
        baul.add(honey);
    }



}
