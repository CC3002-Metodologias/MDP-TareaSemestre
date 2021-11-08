package aventurasMarcoyLuis.controller;

import aventurasMarcoyLuis.*;
import aventurasMarcoyLuis.Items.HoneySyrup;
import aventurasMarcoyLuis.Items.RedMushroom;
import aventurasMarcoyLuis.interfaces.Ienemy;
import aventurasMarcoyLuis.interfaces.Items;
import aventurasMarcoyLuis.personajes.Boo;
import aventurasMarcoyLuis.personajes.Goomba;
import aventurasMarcoyLuis.personajes.Spiny;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.*;

public class BattleController {

    /**
     * int que referencia a que ronda es la actual
     */
    private int Round;
    /**
     * PlayerIN para guardar un jugador
     */
    PlayerIn PMarco;
    /**
     * PlayerIN para guardar un jugador
     */
    PlayerIn PLuis;
    /**
     * Lista de jugadores activos(PlayerIn) en la ronda
     */
    private List<PlayerIn> PlayersList;
    /**
     * Lista de enemigos activos en la ronda
     */
    private List<Ienemy> EnemyList;
    /**
     * Lista de nombre baul para almacenar Items y asi poder usarlos
     */
    private List<Items> Baul;
    /**
     * Booleano que dice si se ha terminado la batalla o no.
     */
    private boolean ItOver;
    /**
     * Booleano que dice si se ha ganado la batalla o no.
     */
    private boolean Win;
    /**
     * Variable que indica el indice al player que tiene el turno en PlayerList,
     * cuando es igual al largo de PlayerList indica que le toca a los enemigos.
     */
    private int Pturn; //Variable que indica el indice al player que tiene el turno en PlayerList,
                        //cuando es igual al largo de PlayerList indica que le toca a los enemigos.
    /**
     * Variable tipo Random usada para generar enemigos al azar
     */
    private Random random = new Random();
    /**
     * Variable tipo Random usada para generar los ataques aleatorios de los enemigos
     */
    private Random random2 = new Random();

    /**
     * Para printear o no
     */
    private PrintStream out = System.out;
    /**
     * Constructor de Battle que inicializa las variables para que puedan ser usadas
     * @param playerMarco
     * @param playerLuis
     */
    public BattleController(PlayerIn playerMarco, PlayerIn playerLuis){
        Round = 1; Baul = new ArrayList<Items>();
        PMarco = playerMarco; PLuis = playerLuis;
        Baul = new ArrayList<Items>();
        EnemyList = new ArrayList<Ienemy>(); PlayersList = new ArrayList<PlayerIn>();
        PlayersList.add(PMarco); PlayersList.add(PLuis);

        ItOver = false;
        Win = false;
        Pturn = 0;
    }
    /**
     * Metodo para crear y agregar un nuevo item
     */
    private void newMushroom(){
        RedMushroom mushroom = new RedMushroom();
        Baul.add(mushroom);
    }
    /**
     * Metodo para crear y agregar un nuevo item
     */
    private void newHoney(){
        HoneySyrup honey = new HoneySyrup();
        Baul.add(honey);
    }
    /**
     * Metodo para crear y agregar un nuevo enemigo de tipo Random
     */
    private void newRandomEnemy(){

        int numeroAzar = random.nextInt(3);

        if (numeroAzar == 0){
            Boo boo = new Boo();
            boo.setSeed(random2.nextInt());
            EnemyList.add(boo);
        }
        else if(numeroAzar == 1){
            Goomba goomba = new Goomba();
            goomba.setSeed(random2.nextInt());
            EnemyList.add(goomba);
        } else{
            Spiny spiny = new Spiny();
            spiny.setSeed(random2.nextInt());
            EnemyList.add(spiny);
        }
    }

    /**
     * Metodo que empieza un nueva ronda segun sea el numero de esta, llama a roundn() o round1
     */
    private void start_newRound(){
        Pturn = 0;
        if (Round == 1) {
            round1();
        }
        else if (Round == 2){
            roundn(3);
        }
        else if (Round == 3 || Round == 4){
            roundn(5);
        }
        else if (Round == 5){
            roundn(6);
        }
    }

    /**
     * metodo que empieza y setea la primera ronda
     */
    public void round1() {
        //senteciamos y añadimos 3 Honey Syrup y 3 Red Mushroom al baul compartido
        newHoney(); newHoney(); newHoney();
        newMushroom(); newMushroom(); newMushroom();

        //generamos 3 randoms enemigos y lo añadimos a lista de enemigos.
        newRandomEnemy(); newRandomEnemy(); newRandomEnemy();
    }

    /**
     * metodo que setea y empieza un ronda (rondas entre 2 y 5)
     * @param i
     */
    private void roundn(int i){
        newHoney();
        newMushroom();
        PlayersList.clear();
        playersLevelUp();
        PlayersList.add(PMarco);
        PlayersList.add(PLuis);

        for (int j = 1; j <= i; j++) {
            newRandomEnemy();
        }

    }

    /**
     * Se suben las stats de cada Player, y se dejan con la vida y Fp al maximo
     */
    private void playersLevelUp() {
        PMarco.getPlayer().setAtaque(PMarco.getPlayer().getAtaque() * 1.15);
        PMarco.getPlayer().setDefensa(PMarco.getPlayer().getDefensa() * 1.15);
        PMarco.getPlayer().setHPmax(PMarco.getPlayer().getHPmax() * 1.15);
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

    /**
     * Se ocupa para finalizar una ronda luego de haberla ganado y pasa a la siguiente ronda o llama a Win()
     * @throws IOException
     */
    private void round_over() throws IOException {
        if (Round<=4) {
            Round++;
            out.println("You win round");
            out.println("Welcome to the round " + Round);
            start_newRound();
        }
        else{win();}

    }

    /**
     * Termina el juego cambiando la variable ItOver pero ademas cambia la var Win a true
     */
    private void win() {
        out.println("You win the Battle");
        Win = true;
        ItOver = true;
    }

    /**
     * Termina el juego cambiando la variable ItOver
     */
    private void gameover() {
        out.println("You Lost");
        ItOver = true;
    }

    /**
     * llama al metodo turn de PlayerIn
     * @param player
     * @throws IOException
     */
    private void pturns(PlayerIn player) throws IOException {

        out.println("Player "+ player.getPlayer().getName());
        for(int j=0; j < Baul.size(); j++){
            out.print(j + "= " + Baul.get(j).getName() + ", ");
        }
        out.print("\n");
        for(int j=0; j < EnemyList.size(); j++){
            out.print(j + "= "+ EnemyList.get(j).getName() + " HP="+EnemyList.get(j).getHPactual()+", ");
        }
        out.print("\n");

        player.turn(this);
    }

    /**
     * metodo para ejecutar un turno de un enemigo. (Solo ataque a un jugador)
     */
    private void eturn(Ienemy ienemy){
        if (ienemy != null) {
            int pj = random2.nextInt(PlayersList.size());
            PlayerIn PlayerAtacado = PlayersList.get(pj);

            ienemy.ataque(PlayerAtacado.getPlayer());
            out.println(ienemy.getName()+ " ataca a "+ PlayerAtacado.getPlayer().getName()
                    + " con HP=" + PlayerAtacado.getPlayer().getHPactual());

            if (PlayerAtacado.getPlayer().isKO()) {
                PlayerAtacado.deletePList(PlayersList);
                out.println("Jugador"+ PlayerAtacado.getPlayer().getName()+ "esta KO");
            }
        }
    }

    /**
     * metodo para revisar si todos los players estan ko
     */
    public boolean players_isKO(){
        return (PlayersList.size() == 0);
    }

    /**
     * metodo para revisar si todos los enemigos estan ko
     */
    public boolean enemys_isKO(){
        return (EnemyList.size() == 0);
    }

    /**
     * metodo que remueve a un enemy de EnemyList luego de que este esté ko
     */
    private void ko_remove(Ienemy e1){
        if (e1.isKO()){
            e1.deleteEList(EnemyList);
        }
    }

    /**
     * metodo para ejecutar un ataque de PlayerIn con sus respectivas consecuencias
     */
    public void playerAttack(PlayerIn player, int i, AttackType at){
        if (EnemyList.get(i) != null) {
            player.getPlayer().ataque(EnemyList.get(i), at);
            ko_remove(EnemyList.get(i));
        }
        if (player.getPlayer().isKO()){
            player.deletePList(PlayersList);
        }
    }

    /**
     * metodo para que el Ipersonaje asociado a un PlayerIn pueda usar un Item del baul
     * @param player
     * @param i
     */
    public void playerUseItem(PlayerIn player, int i) throws IOException {
        if (i < Baul.size() && i >= 0) {
            Baul.get(i).use(player.getPlayer());
            Baul.get(i).deleteBaul(Baul);
        }else{
            throw new IOException("indice fuera de rango");
        }
    }

    /**
     * Metodo que se usa para ejecutar un turno de un personaje
     * @throws IOException
     */
    public void update() throws IOException{
        //los turnos de los enemigos se ejecutan bajo un mismo update por el momento.
        if (Pturn == PlayersList.size()) {
            for (Ienemy ienemy : EnemyList) {
                eturn(ienemy);
                if (players_isKO()) {
                    gameover();
                    break;
                }
            }
            Pturn = 0;
        } else{
            pturns(PlayersList.get(Pturn));
            Pturn += 1;
            if (players_isKO()){gameover();}
            if (enemys_isKO()){round_over();}
        }
    }


    /**
     * Metodo que dice si la batalla ha terminado o no
     * @return Bool
     */
    public boolean notOver() {
        return !(ItOver);
    }

    /**
     * Metodo para retornar el baúl, que usamos para probar test
     */
    public List<Items> getBaul(){
        return Baul;
    }

    /**
     * Metodo para retornar EnemyList, que usamos para probar test
     */
    public List<Ienemy> getEnemyList() {
        return EnemyList;
    }

    /**
     * Metodo para retornar EnemyList, que usamos para probar test
     */
    public List<PlayerIn> getPlayerList() {
        return PlayersList;
    }

    /**
     * Metodo para detallar quien tiene el turno
     */
    public String getdTurno(){
        if (Pturn < PlayersList.size()){
            return PlayersList.get(Pturn).getPlayer().getName();
        } else{
            return "Enemigos turno";
        }
    }

    /**
     * Metodo para plantar semillas a random y random2
     */
    public void setSeeds(final long seed1, final long seed2){
        random.setSeed(seed1);
        random2.setSeed(seed2);
    }

    public int getRound() {
        return Round;
    }

    /**
     * metodo para saber si se ha ganado la batalla o no.
     * @return
     */
    public boolean isWin(){
        return Win;
    }

    /**
     * Para cambiar la variable out.
     * Útil para colocar  new PrintStream(new NullOutputStream()) y así no printear.
     */
    public void setOut(PrintStream printStream){
        out = printStream;
    }
}
