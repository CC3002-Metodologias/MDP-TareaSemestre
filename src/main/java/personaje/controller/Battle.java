package personaje.controller;

import personaje.*;
import personaje.Items.HoneySyrup;
import personaje.Items.RedMushroom;
import personaje.interfaces.Ienemy;
import personaje.interfaces.Ipersonaje;
import personaje.interfaces.Items;

import java.util.ArrayList;
import java.util.*;

public class Battle {
    public int Kills;
    public int Score;
    public int Round;
    private personaje.Marco PMarco;
    private personaje.Luis PLuis;
    private List<Ipersonaje> PlayersList;
    private List<Ienemy> EnemyList;
    private List<Items> Baul;

    public Battle(int kills, int score, int round, List<Items> baul){
        Kills = kills; Score = score;
        Round = round; Baul = baul;

        PMarco = new Marco();
        PLuis = new Luis();
        Baul = new ArrayList<Items>();
        EnemyList = new ArrayList<Ienemy>();
        PlayersList = new ArrayList<Ipersonaje>();
    }
    private void newMushroom(){
        RedMushroom mushroom = new RedMushroom();
        Baul.add(mushroom);
    }
    private void newHoney(){
        HoneySyrup honey = new HoneySyrup();
        Baul.add(honey);
    }
    private void newRandomEnemy(){
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

    //Metodo que empieza un nueva ronda segun sea el numero de esta
    private void start_newRound(){
        if (Round == 1) {
            round1();
        }
        else if (Round == 2){
            roundn(3);
        }
        else if (Round == 3 || Round == 4){
            roundn(5);
        }
    }

    // metodo que ejecuta la ronda 1 completa
    private void round1(){
        //senteciamos y añadimos 3 Honey Syrup y 3 Red Mushroom al baul compartido
        newHoney(); newHoney(); newHoney();
        newMushroom(); newMushroom(); newMushroom();

        //generamos 3 randoms enemigos y lo añadimos a lista de enemigos.
        newRandomEnemy(); newRandomEnemy(); newRandomEnemy();

        //llamamos el metodo turns
        turns();
    }
    //metodo que ejecuta la ronda i completa
    private void roundn(int i) {
        newHoney();
        newMushroom();
        PlayersList.clear();
        playersLevelUp();
        PlayersList.add(PLuis);
        PlayersList.add(PMarco);
        for (int j = 1; j <= i; j++) {
            newRandomEnemy();
        }
        turns();

    }
    //Se suben las stats de cada Player, y se dejan con la vida y Fp al maximo
    private void playersLevelUp() {
        PMarco.setAtaque(PMarco.getAtaque() * 1.15);
        PMarco.setDefensa(PLuis.getDefensa() * 1.15);
        PMarco.setHPmax(PMarco.getHPmax() * 1.15);
        PMarco.setHP(PMarco.getHPmax());
        PMarco.setFPmax( (int) (PMarco.getFPmax() * 1.15) );
        PMarco.setFPactual(PMarco.getFPmax());
        PMarco.setNivel(PMarco.getNivel() + 1);

        PLuis.setAtaque(PLuis.getAtaque() * 1.15);
        PLuis.setDefensa(PLuis.getDefensa() * 1.15);
        PLuis.setHPmax(PLuis.getHPmax() * 1.15);
        PLuis.setHP(PLuis.getHPmax());
        PLuis.setFPmax( (int) (PLuis.getFPmax() * 1.15) );
        PLuis.setFPactual(PLuis.getFPmax());
        PLuis.setNivel(PLuis.getNivel() + 1);

    }

    //Metodo que para hacer el cambio a la siguiente ronda una vez esta esta finalizada y no se ha perdido.
    private void round_over() {
        if (Round<=4) {
            Round++;
            start_newRound();
        }
        else{win();}
    }

    private void win() {
    }
    private void gameover() {
    }

    private void pturns(Ipersonaje p1) {
    }
    //metodo para ejecutar un turno de un enemigo. (Solo ataque a un jugador)
    private void eturn(Ienemy ienemy){
        if (ienemy != null) {
            Random aleatorio = new Random();
            int pj = aleatorio.nextInt(PlayersList.size());
            ienemy.ataque(PlayersList.get(pj));

            if (PlayersList.get(pj).isKO()) {
                PlayersList.get(pj).deletePList(PlayersList);
            }
        }
    }

    //metodo para revisar si todos los players estan ko
    public boolean players_isKO(){
        return (PlayersList.size() == 0);
    }
    //metodo para revisar si todos los enemigos estan ko
    public boolean enemys_isKO(){
        return (EnemyList.size() == 0);
    }

    //metodo que remueve a un player de PlayersList luego de que este esté ko
    private void ko_remove(Ipersonaje p1){
        if (p1.isKO()){
            p1.deletePList(PlayersList);
        }
    }
    //metodo que remueve a un enemy de EnemyList luego de que este esté ko
    private void ko_remove(Ienemy e1){
        if (e1.isKO()){
            e1.deleteEList(EnemyList);
        }
    }


    private void turns(){

        do{
            for (Ipersonaje ipersonaje : PlayersList) {
                pturns(ipersonaje);
            }
            for (Ienemy ienemy : EnemyList) {
                eturn(ienemy);
            }
        } while ( !(players_isKO()) && !(enemys_isKO()) );

        if(players_isKO()) {gameover();}

        if(enemys_isKO()) {round_over();}
    }
}
