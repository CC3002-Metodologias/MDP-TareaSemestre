package personaje.controller;

import personaje.*;
import personaje.Items.HoneySyrup;
import personaje.Items.RedMushroom;
import personaje.interfaces.Ienemy;
import personaje.interfaces.Ipersonaje;
import personaje.interfaces.Items;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Battle {
    private int Kills;
    private int Score;
    private int Round;
    PlayerIn PMarco;
    PlayerIn PLuis;
    private List<PlayerIn> PlayersList;
    private List<Ienemy> EnemyList;
    private List<Items> Baul;
    private boolean ItOver;
    private boolean Win;

    public Battle(PlayerIn playerMarco, PlayerIn playerLuis){
        Kills = 0; Score = 0;
        Round = 0; Baul = new ArrayList<Items>();

        PMarco = playerMarco;
        PLuis = playerLuis;
        Baul = new ArrayList<Items>();
        EnemyList = new ArrayList<Ienemy>();
        PlayersList = new ArrayList<PlayerIn>();

        ItOver = false;
        Win = false;
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
    private void start_newRound() throws IOException {
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
    private void round1() throws IOException {
        //senteciamos y añadimos 3 Honey Syrup y 3 Red Mushroom al baul compartido
        newHoney(); newHoney(); newHoney();
        newMushroom(); newMushroom(); newMushroom();

        //generamos 3 randoms enemigos y lo añadimos a lista de enemigos.
        newRandomEnemy(); newRandomEnemy(); newRandomEnemy();

        //llamamos el metodo turns
        turns();
    }
    //metodo que ejecuta la ronda i completa
    private void roundn(int i) throws IOException {
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
        PMarco.getPlayer().setAtaque(PMarco.getPlayer().getAtaque() * 1.15);
        PMarco.getPlayer().setDefensa(PLuis.getPlayer().getDefensa() * 1.15);
        PMarco.getPlayer().setHP(PMarco.getPlayer().getHPmax());
        PMarco.getPlayer().setFPmax( (int) (PMarco.getPlayer().getFPmax() * 1.15) );
        PMarco.getPlayer().setFPactual(PMarco.getPlayer().getFPmax());
        PMarco.getPlayer().setNivel(PMarco.getPlayer().getNivel() + 1);

        PLuis.getPlayer().setAtaque(PLuis.getPlayer().getAtaque() * 1.15);
        PLuis.getPlayer().setDefensa(PLuis.getPlayer().getDefensa() * 1.15);
        PLuis.getPlayer().setHPmax(PLuis.getPlayer().getHPmax() * 1.15);
        PLuis.getPlayer().setHP(PLuis.getPlayer().getHPmax());
        PLuis.getPlayer().setFPmax( (int) (PLuis.getPlayer().getFPmax() * 1.15) );
        PLuis.getPlayer().setFPactual(PLuis.getPlayer().getFPmax());
        PLuis.getPlayer().setNivel(PLuis.getPlayer().getNivel() + 1);

    }

    //Metodo que para hacer el cambio a la siguiente ronda una vez esta esta finalizada y no se ha perdido.
    private void round_over() throws IOException {
        if (Round<=4) {
            Round++;
            start_newRound();
        }
        else{win();}
    }

    private void win() {
        Win = true;
        ItOver = true;
    }
    private void gameover() {
        ItOver = true;
    }

    private void pturns(PlayerIn player) throws IOException {
        player.turn(this);


    }
    //metodo para ejecutar un turno de un enemigo. (Solo ataque a un jugador)
    private void eturn(Ienemy ienemy){
        if (ienemy != null) {
            Random aleatorio = new Random();
            int pj = aleatorio.nextInt(PlayersList.size());
            ienemy.ataque(PlayersList.get(pj).getPlayer());

            if (PlayersList.get(pj).getPlayer().isKO()) {
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

    //metodo que remueve a un enemy de EnemyList luego de que este esté ko
    private void ko_remove(Ienemy e1){
        if (e1.isKO()){
            e1.deleteEList(EnemyList);
        }
    }

    //metodo para ejecutar un ataque de PlayerIn con sus respectivas consecuencias
    public void playerAttack(PlayerIn player, int i, AttackType at){
        player.getPlayer().ataque(EnemyList.get(i), at);

        ko_remove(EnemyList.get(i));
        if (player.getPlayer().isKO()){
            player.deletePList(PlayersList);
        }
    }
    public void playerUseItem(PlayerIn player, int i){
        Baul.get(i).use(player.getPlayer());
        Baul.get(i).deleteBaul(Baul);
    }

    //metodo que distribuye los turnos en cada ronda para las batallas
    private void turns() throws IOException {
        do{
            for (PlayerIn player : PlayersList) {
                pturns(player);
            }
            for (Ienemy ienemy : EnemyList) {
                eturn(ienemy);
            }
        } while ( !(players_isKO()) && !(enemys_isKO()) );

        if(players_isKO()) {gameover();}

        else if(enemys_isKO()) {round_over();}
    }
}
