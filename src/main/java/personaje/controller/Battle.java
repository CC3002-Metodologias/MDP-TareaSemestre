package personaje.controller;

import personaje.*;
import personaje.Items.HoneySyrup;
import personaje.Items.RedMushroom;
import personaje.interfaces.Ienemy;
import personaje.interfaces.Ipersonaje;
import personaje.interfaces.Items;

import java.util.ArrayList;
import java.util.List;

public class Battle {
    public int Kills;
    public int Score;
    public int Round;
    public personaje.Marco PMarco;
    public personaje.Luis PLuis;
    public List<Ipersonaje> PlayersList;
    public List<Ienemy> EnemyList;
    public List<Items> Baul;

    public Battle(int kills, int score, int round, List<Items> baul){
        Kills = kills; Score = score;
        Round = round; Baul = baul;

        PMarco = new Marco();
        PLuis = new Luis();
        Baul = new ArrayList<Items>();
        EnemyList = new ArrayList<Ienemy>();
        PlayersList = new ArrayList<Ipersonaje>();
    }
    public void newMushroom(){
        RedMushroom mushroom = new RedMushroom();
        Baul.add(mushroom);
    }
    public void newHoney(){
        HoneySyrup honey = new HoneySyrup();
        Baul.add(honey);
    }
    public void newRandomEnemy(){
        double azar = Math.random() * 3;
        if (azar<1){
            Goomba goomba = new Goomba();
            EnemyList.add(goomba);
        }
        else if(azar<2){
            Spiny spiny = new Spiny();
            EnemyList.add(spiny);
        } else{
            Boo boo = new Boo();
            EnemyList.add(boo);
        }
    }

    public void start_battle(){

    }
    public void round1(){
        //senteciamos y añadimos 3 Honey Syrup y 3 Red Mushroom al baul compartido
        newHoney(); newHoney(); newHoney();
        newMushroom(); newMushroom(); newMushroom();

        //generamos 3 randoms enemigos y lo añadimos a lista de enemigos.
        newRandomEnemy(); newRandomEnemy(); newRandomEnemy();




    }

    public boolean players_isKO(){
        return (PlayersList.size() == 0);
    }
    public boolean enemys_isKO(){
        return (EnemyList.size() == 0);
    }

    public void ko_remove(Ipersonaje p1){
        if (p1.isKO()){
            p1.deletePList(PlayersList);
        }
    }
    public void ko_remove(Ienemy e1){
        if (e1.isKO()){
            e1.deleteEList(EnemyList);
        }
    }

    public void turns(){

    }
}
