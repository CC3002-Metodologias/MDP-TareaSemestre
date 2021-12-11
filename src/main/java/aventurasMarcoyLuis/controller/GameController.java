package aventurasMarcoyLuis.controller;

import aventurasMarcoyLuis.AttackType;
import aventurasMarcoyLuis.Items.HoneySyrup;
import aventurasMarcoyLuis.Items.RedMushroom;
import aventurasMarcoyLuis.controller.observers.Iobserver;
import aventurasMarcoyLuis.controller.observers.LostObserver;
import aventurasMarcoyLuis.controller.observers.WinObserver;
import aventurasMarcoyLuis.controller.phasesAndExceptions.*;
import aventurasMarcoyLuis.interfaces.Ienemy;
import aventurasMarcoyLuis.interfaces.Ipersonaje;
import aventurasMarcoyLuis.interfaces.Iplayer;
import aventurasMarcoyLuis.interfaces.Items;
import aventurasMarcoyLuis.personajes.*;


import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {

    /**
     * int que referencia a que ronda es la actual
     */
    protected int Round;
    /**
     * PlayersIN que participaran en el juego
     */
    private final List<PlayerIn> Players;
    /**
     * Lista de jugadores activos(PlayerIn) en la ronda
     */
    private List<PlayerIn> playerInList;
    /**
     * Lista de personajes principales(Ipersonaje) activos en la ronda, los que controla los PlayerIn de playerInList
     */
    private List<Iplayer> playersList;
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
    private int Pturn;
    /**
     * PlayerIn dueño del turno actual. Cuando es null, indica que no hay una ronda activa jugandose o
     * el turno pertenece a los enemigos.
     */
    private PlayerIn ownerTurn;
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
     * Phase en la que se encuentra la Batalla, esta va cambiando.
     */
    private Phase phase;

    /**
     * Observador que mira si en la batalla los jugadores han ganado
     */
    private final Iobserver iWinRoundObs = new WinObserver(this);
    /**
     * Observador que mira si en la batalla los jugadores han perdido
     */
    private final Iobserver lostObs = new LostObserver(this);
    /**
     * Notificador para lostObs
     */
    private final PropertyChangeSupport lostNotifaction= new PropertyChangeSupport(this);
    /**
     * Notificador para iWinRoundObs
     */
    private final PropertyChangeSupport winRoundNotifaction= new PropertyChangeSupport(this);

    //------------------------------------------------------------//
    /**
     * Constructor de Battle que inicializa las variables para que puedan ser usadas
     */
    public GameController(){
        Baul = new ArrayList<>();
        Players = new ArrayList<>();
        EnemyList = new ArrayList<>();
        playerInList = new ArrayList<>();
        playersList = new ArrayList<>();
        Round = 1;
        Pturn = 0;
        ItOver = false;
        Win = false;
        phase = new StartRoundPhase();
        phase.setController(this);
    }

    /**
     * Añade un PlayerIn al juego.
     * @param player
     * @return
     */
    public GameController addPlayer(PlayerIn player){
        Players.add(player);
        return this;
    }
    /**
     * Crea un nuevo PlayerIn con un personaje Marco y lo añade al juego
     */
    public PlayerIn newMarcoPlayer(){
        PlayerIn ret = new PlayerIn(new Marco());
        addPlayer(ret);
        return ret;
    }

    /**
     * Crea un nuevo PlayerIn con un personaje Luis y lo añade al juego
     */
    public PlayerIn newLuisPlayer(){
        PlayerIn ret = new PlayerIn(new Luis());
        addPlayer(ret);
        return ret;
    }

    /**
     * Añade un PlayerIn a la lista de playerInList para que sea considerado en los turnos de ronda
     */
    public void addPlayerInToRound(PlayerIn playerIn){
        playerInList.add(playerIn);
        playersList.add(playerIn.getPlayer());
    }

    /**
     * Elimina la Ipersonaje de playerList y su controlador playerIn de playerInList, para ya no ser considerado activo en la ronda.
     */
    public void removePlayerRound(Iplayer iplayer){
        playersList.remove(iplayer);
        playerInList.remove(iplayer.getPlayerIn());
    }

    //Metodos utilies para transiciones entre rondas y setear estas.

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
    public void start_newRound() throws InvalidTransitionException {
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
        winRoundNotifaction.firePropertyChange("Empieza una nueva ronda",true,false);
        phase.toChoosingActionPhase();
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

        for(PlayerIn player: Players){
            addPlayerInToRound(player);
        }
        upgradeOwner();
        lostNotifaction.addPropertyChangeListener(lostObs);
        winRoundNotifaction.addPropertyChangeListener(iWinRoundObs);
    }

    /**
     * Actualiza la ownerTurn que indica que PlayerIn tiene el turno.
     */
    private void upgradeOwner() {
        if (Pturn == Players.size()){
            ownerTurn = null;
        }
        else {
            ownerTurn = Players.get(Pturn);
        }
    }
    /**
     * @return ownerTurn, PlayerIn dueño del turno.
     */
    public PlayerIn getOwnerTurn() {
        return ownerTurn;
    }

    /**
     * metodo que setea y empieza un ronda (rondas entre 2 y 5)
     * @param i
     */
    private void roundn(int i){
        newHoney();
        newMushroom();
        playerInList.clear();
        playersList.clear();
        playersLevelUp();
        for(PlayerIn player: Players){
            addPlayerInToRound(player);
        }
        Pturn = 0;
        upgradeOwner();

        for (int j = 1; j <= i; j++) {
            newRandomEnemy();
        }
    }

    /**
     * Se suben las stats de cada Player, y se dejan con la vida y Fp al maximo
     */
    private void playersLevelUp() {

        for(PlayerIn player: Players){
            Iplayer p= player.getPlayer();
            p.setAtaque(p.getAtaque() * 1.15);
            p.setDefensa(p.getDefensa() * 1.15);
            p.setHPmax(p.getHPmax() * 1.15);
            p.setHP(p.getHPmax());
            p.setFPmax( (int) (p.getFPmax() * 1.15) );
            p.setFPactual(p.getFPmax());
            p.setNivel(p.getNivel() + 1);
        }

    }

    /**
     * metodo para revisar si todos los players estan ko
     */
    private boolean players_isKO(){
        return (playerInList.size() == 0);
    }

    /**
     * metodo para revisar si todos los enemigos estan ko
     */
    private boolean enemys_isKO(){
        return (EnemyList.size() == 0);
    }

    /**
     * metodo que remueve a un enemy de EnemyList luego de que este esté ko
     * retorna true si el Ienemy estaba efectivamente KO
     */
    private boolean ko_remove(Ienemy e1){
        if (e1.isKO()){
            e1.deleteEList(EnemyList);
            return true;
        }
        else return false;
    }

    /**
     * Se ocupa para finalizar una ronda luego de haberla ganado y pasa a la siguiente ronda o llama a Win()
     */
    public void round_over(boolean b) throws InvalidTransitionException {
        if (b) {
            if (Round <= 4) {
                Round++;
                out.println("You win round");
                phase.toStartRoundPhase();
                out.println("Welcome to the round " + Round);
                start_newRound();
            } else {
                win();
            }
        }
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
    public void gameover() {
        out.println("You Lost");
        ItOver = true;

    }

    /**
     * metodo para ejecutar un ataque de PlayerIn con sus respectivas consecuencias
     */
    public void playerAttack(PlayerIn player, int i, AttackType at) throws  InvalidAttackException {
        if (EnemyList.get(i) != null) {
            player.getPlayer().ataque(EnemyList.get(i), at);
            if (ko_remove(EnemyList.get(i))){
                if (enemys_isKO()) {
                    winRoundNotifaction.firePropertyChange("Win round", false, true);
                }
            }

        }
    }

    /**
     * metodo para que el Ipersonaje asociado a un PlayerIn pueda usar un Item del baul
     * @param player
     * @param i
     */
    public void playerUseItem(PlayerIn player, int i) throws InvalidTransitionException {
        Baul.get(i).use(player.getPlayer());
        Baul.get(i).deleteBaul(Baul);
    }

    //Flujo de juego

    /**
     * Se intenta iniciar una nueva ronda
     */
    public void tryToStartNewRound(){
        try {
            phase.startRound();
        } catch (InvalidOptionException | InvalidTransitionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Jugador elige una accion a realizar en su turno si esta en la fase correcta
     */
    public void tryToSelectAction(){
        PlayerIn player = ownerTurn;
        try {
            phase.chooseAction(player);
        } catch (InvalidOptionException | InvalidTransitionException | IOException | InvalidInputException e) {
            e.printStackTrace();
        }
    }

    /**
     * jugador seleciona una acción
     * @param player
     * @throws InvalidTransitionException
     * @throws IOException
     * @throws InvalidInputException Si el input ingresado no corresponde a una opcion válida
     */
    public void selectAction(PlayerIn player) throws InvalidTransitionException, IOException, InvalidInputException {
        player.selectAction(this);

    }

    /**
     * Se intenta seleccionar el tipo ataque para posteriormente seleccionar a un enemigo
     * @param player
     */
    public void tryToSelectAttack(PlayerIn player) {
        try {
            phase.chooseAttack(player);
        } catch (InvalidOptionException | IOException | InvalidInputException e) {
            e.printStackTrace();
        }
    }

    /**
     * Seleciona el tipo de ataque a realizar
     * @param player
     * @throws IOException
     * @throws InvalidInputException si el input ingresado no corresponde a opción válida
     */
    public void selectAttack(PlayerIn player) throws IOException, InvalidInputException {
        player.selectAttack(this);
    }

    /**
     * Se intenta seleccionar un enemigo para atacarlo
     * @param player
     * @param attackType
     */
    public void tryToSelectVictim(PlayerIn player, AttackType attackType) {
        try {
            phase.chooseVictimAttack(player, attackType);
        } catch (InvalidOptionException | InvalidTransitionException | IOException | InvalidInputException | InvalidAttackException e){
            e.printStackTrace();
        }
    }

    /**
     * Selecciona el enemigo a atacar
     * @param player
     * @param attackType
     * @throws InvalidTransitionException
     * @throws IOException
     * @throws InvalidInputException si el input ingresado no corresponde a opción válida
     */
    public void selectVictimAttack(PlayerIn player, AttackType attackType) throws InvalidTransitionException, IOException, InvalidInputException, InvalidAttackException {
        player.selectVictimAttack(this, attackType);
    }

    /**
     * Se intenta seleccionar un item para usarlo posteriormente
     * @param player
     */
    public void tryToSelectItem(PlayerIn player) {
        try {
            phase.selectItem(player);
        } catch (InvalidOptionException | IOException | InvalidTransitionException | InvalidInputException e){
            e.printStackTrace();
        }
    }

    /**
     * Se selecciona un item para posteriormente usarlo
     * @param player
     * @throws InvalidTransitionException
     * @throws IOException
     * @throws InvalidInputException si el input ingresado no corresponde a opción válida
     */
    public void selectItem(PlayerIn player) throws InvalidTransitionException, IOException, InvalidInputException {
        player.selectItem(this);
    }

    /**
     * Se intenta ejecutar el turno de todos los enemigos no KO
     */
    public void tryToRunEnemiesTurn() {
        try {
            phase.runEnemiesTurn();
        } catch (InvalidOptionException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    /**
     * Se intenta terminar un turno, ya sea de un jugador o de los enemigos
     */
    public void tryToEndTurn(){
        try{
            phase.endTurn();
        } catch (InvalidOptionException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    /**
     * Se realiza el cambio de turno
     * @throws InvalidTransitionException
     */
    public void changeTurn() throws InvalidTransitionException {

        Pturn = (Pturn+1)%(Players.size()+1);
        upgradeOwner();

        if (Pturn == Players.size()) {
            phase.toEnemiesTurnPhase();
            tryToRunEnemiesTurn();
        }
        //Caso en el que el jugador(PlayerIn) que le tocaria el turno esta KO, por lo que hay que saltarlo.
        else if (!playerInList.contains(ownerTurn)){
            changeTurn();
        }
        else phase.toChoosingActionPhase();
    }

    /**
     * Se intenta terminar la ronda una vez ganada esta
     */
    public void tryToRoundOver() {
        try{
            phase.roundOver();
        } catch (InvalidOptionException | InvalidTransitionException e) {
            e.printStackTrace();
        }

    }

    /**
     * Se intenta terminar el juego luego de haber perdido, dependiendo de boolean
     * @param b solo si es true se llama a tryToGameOver
     */
    public void tryToGameOver(boolean b){
        if (b) tryToGameOver();
    }

    /**
     * Se intenta terminar el juego luego de haber perdido
     */
    public void tryToGameOver(){
        try{
            phase.gameOver();
        } catch (InvalidOptionException e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo para ejecutar un turno de un enemigo. (Solo ataque a un jugador)
     */
    private void eturn(Ienemy ienemy) {

        try {
            Iplayer playerAtacado = ienemy.randomAttack(playersList);
            if (playerAtacado !=null) {
                out.println(ienemy.getName() + " ataca a " + playerAtacado.getName()
                        + " con HP=" + playerAtacado.getHPactual());

                if (playerAtacado.isKO()) {
                    removePlayerRound(playerAtacado);
                    out.println("Jugador" + playerAtacado.getName() + "esta KO");
                    if (players_isKO()) {
                        lostNotifaction.firePropertyChange("I lost?",
                                false, true);
                    }
                }
            }
        } catch (InvalidAttackException e) {
            e.printStackTrace();
        }

    }

    /**
     * Ejecuta los turnos de todos los enemigos en orden y, dependiendo de lo que sucede,
     * pasa de turno o termina la ronda.
     * @throws InvalidTransitionException
     */
    public void enemiesTurns() throws InvalidTransitionException {
        for (Ienemy e : EnemyList){
            eturn(e);
        }
        if (notOver()) {
            phase.toEndTurnPhase();
            tryToEndTurn();
        }

    }

    // Metodos para saber informacion de la partida y obtener Variables como Baul, turno, etc.

    /**
     * Metodo que dice si la batalla ha terminado o no
     * @return Bool
     */
    public boolean notOver() {
        return !(ItOver);
    }

    public boolean iLost(){
        if (ItOver && !Win){
            return true;
        } else return false;
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
     * Metodo para retornar playerInList, que usamos para probar test
     */
    public List<PlayerIn> getPlayerInList() {
        return playerInList;
    }

    /**
     * Retorna la lista de PlayerIN en el juego
     */
    public List<PlayerIn> getPlayersIn(){
        return Players;
    }

    /**
     * Metodo para plantar semillas a random y random2
     */
    public void setSeeds(final long seed1, final long seed2){
        random.setSeed(seed1);
        random2.setSeed(seed2);
    }

    /**
     * @return retorna la ronda actual del juego
     */
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
        for(PlayerIn player: Players){
            player.setOut(printStream);
        }
    }

    /**
     * Cambia la phase en el controlador
     * @param phase nueva phase
     */
    public void setPhase(Phase phase) {
        this.phase = phase;
        phase.setController(this);
    }

    /**
     * @return la phase actual del controlador
     */
    public Phase getPhase() {
        return phase;
    }

    /**
     * printea todos los enemigos activos en la ronda por su nombre
     */
    public void printEnemyList(){
        int i = 1;
        for (Ienemy e: EnemyList){
            out.print(i+":" + e.getName()+ " | ");
            i++;
        }
        out.print("\n");
    }

    /**
     * printea todos los items del baul por su nombre
     */
    public void printBaul() {
        int i = 1;
        for (Items item: Baul){
            out.print(i+":" + item.getName()+ " | ");
            i++;
        }
        out.print("\n");
    }
    public List<Ipersonaje> getAllPersOfTurns(){
        List<Ipersonaje> ret = new ArrayList<Ipersonaje>();
        ret.addAll(playersList);
        ret.addAll(EnemyList);
        return ret;

    }

}
