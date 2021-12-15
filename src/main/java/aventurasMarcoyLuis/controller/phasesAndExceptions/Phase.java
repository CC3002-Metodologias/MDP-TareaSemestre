package aventurasMarcoyLuis.controller.phasesAndExceptions;

import aventurasMarcoyLuis.model.AttackType;
import aventurasMarcoyLuis.controller.GameController;
import aventurasMarcoyLuis.controller.PlayerIn;

import java.io.IOException;

/**
 * Clase base y generica de una fase puntual.
 */
public class Phase {
    public boolean canStartRound;
    public boolean canChangeTurn;
    public boolean canChooseAction;
    public boolean canChooseItem;
    public boolean canChooseAttack;
    public boolean canChooseEnemy;
    public boolean canEnemiesAttack;
    public boolean canEndRound;
    public boolean waitForAttack;
    public boolean waitForUseItem;
    public boolean finishRound;
    protected GameController controller;

    /**
     * @param controller la setea como el controller de la Phase
     */
    public void setController(GameController controller){
        this.controller=controller;
    }

    /**
     * Cambia la Phase del controlodar por una nueva
     * @param phase la nueva fase.
     */
    public void changePhase(Phase phase){
        phase.setController(controller);
        controller.setPhase(phase);

    }

    /**
     * Empieza una nueva ronda solo si la phase puede hacerlo
     */
    public void startRound() throws InvalidOptionException, InvalidTransitionException {
        if (!canStartRound){
            throw new InvalidOptionException("Can´t start a new Round at moment");
        }
        else{
            controller.start_newRound();
        }
    }

    /**
     * @param player escoje una accion a realizar en su turno
     */
    public void chooseAction(PlayerIn player) throws InvalidOptionException, InvalidTransitionException, IOException, InvalidInputException {
        if (!canChooseAction){
            throw new InvalidOptionException("Can´t choose an action at moment");
        }
        else{
            controller.selectAction(player);
        }
    }

    /**
     * @param player escoje un enemigo a atacar teniendo ya seleccionado el tipo de ataque a realizar
     */
    public void chooseVictimAttack(PlayerIn player, AttackType attackType) throws InvalidOptionException, InvalidTransitionException, IOException, InvalidInputException, InvalidAttackException {
        if (!canChooseEnemy){
            throw new InvalidOptionException("Can´t choose an action at moment");
        }
        else{
            controller.selectVictimAttack(player, attackType);
        }
    }

    /**
     * @param player escoje el tipo de ataque a realizar luego de haber escojido prev atacar
     */
    public void chooseAttack(PlayerIn player) throws InvalidOptionException, IOException, InvalidInputException {
        if (!canChooseAttack){
            throw new InvalidOptionException("Can´t choose an action at moment");
        }
        else{
            controller.selectAttack(player);
        }
    }

    /**
     * @param player escoje el Item a ocupar luego de haber escojido prev usar un Item
     */
    public void selectItem(PlayerIn player) throws InvalidOptionException, InvalidTransitionException, IOException, InvalidInputException {
        if (!canChooseItem){
            throw new InvalidOptionException("Can´t choose an action at moment");
        }
        else{
            controller.selectItem(player);
        }
    }
    /**
     * Cuando se desea terminar el turno
     */
    public void endTurn() throws InvalidOptionException, InvalidTransitionException {
        if (!canChangeTurn) {
            throw new InvalidOptionException("Can't end turn at this moment");
        }
        controller.changeTurn();
    }

    /**
     * Ejecuta el turno de todos los enemigos activos
     */
    public void runEnemiesTurn() throws InvalidOptionException, InvalidTransitionException {
        if (!canEnemiesAttack){
            throw new InvalidOptionException("It can´t run the Enemies's turn right now");
        }
        controller.enemiesTurns();

    }

    /**
     * Termina una ronda luego de haber ganado esta
     */
    public void roundOver() throws InvalidOptionException, InvalidTransitionException {
        if (!canEndRound){
            throw  new InvalidOptionException("Can´t finsih round at this moment");
        }
        controller.round_over(true);
    }

    /**
     * Termina el juego luego de haber perdido
     */
    public void gameOver() throws InvalidOptionException {
        if (!canEndRound){
            throw  new InvalidOptionException("Can´t finsih round at this moment");
        }
        controller.gameover();
    }

    /**
     * Cambia la phase a SetAttackPhase solo si la fase actual es ChoosingActionPhase
     */
    public void toSetAttackPhase(PlayerIn player) throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from this Phase to SetAttackPhase phase");

    }

    /**
     * Cambia la phase a ChoosingItemPhase solo si la fase actual es ChoosingActionPhase
     */
    public void toChoosingItemPhase(PlayerIn player) throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from this Phase to ChoosingItemPhase phase");

    }

    /**
     * Cambia la phase a UsingItemPhase solo si la fase actual es ChoosingItemPhase
     */
    public void toUsingItemPhase(PlayerIn player) throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from this Phase to UsingItemPhase phase");

    }

    /**
     * Cambia la phase a AttackingPhase solo si la fase actual es SetAttackPhase
     */
    public void toAttackingPhase(PlayerIn player) throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from this Phase to AttackingPhase phase");
    }

    /**
     * Cambia la phase a EndTurnPhase solo si la fase actual es ChoosingActionPhase o AttackingPhase o
     * UsingItemPhase o EnemiesTurnPhase
     */
    public void toEndTurnPhase() throws InvalidTransitionException{
        throw new InvalidTransitionException("Can't change from this Phase to EndTurnPhase phase");
    }

    /**
     * Cambia la phase a EnemiesTurnPhase solo si la fase actual es EndTurnPhase
     */
    public void toEnemiesTurnPhase() throws InvalidTransitionException{
        throw new InvalidTransitionException("Can't change from this Phase to EnemiesTurnPhase phase");
    }

    /**
     * Cambia la phase a ChoosingActionPhase solo si la fase actual es EndTurnPhase o StartRoundPhase
     */
    public void toChoosingActionPhase() throws InvalidTransitionException{
        throw new InvalidTransitionException("Can't change from this Phase to ChoosingActionPhase phase");
    }

    /**
     * Cambia la phase a EndRoundPhase solo si la fase actual es AttackingPhase o EnemiesTurnPhase
     */
    public void toEndRoundPhase() throws InvalidTransitionException{
        throw new InvalidTransitionException("Can't change from this Phase to EndRound phase");
    }

    /**
     * Cambia la phase a StartRoundPhase solo si la fase actual es EndRoundPhase
     */
    public void toStartRoundPhase() throws InvalidTransitionException{
        throw new InvalidTransitionException("Can't change from this Phase to StartRound phase");
    }

    /**
     * El jugador (player) ataca a un enemigo.
     * @throws InvalidAttackException Si el ataque no es permitido por la naturaleza de los personajes involucrados y tipo de ataque
     * @throws InvalidOptionException Si la fase no permite efectuar ataques
     */
    public void attack(PlayerIn player, int i, AttackType at) throws InvalidAttackException, InvalidOptionException {
        if (waitForAttack){
            controller.playerAttack(player, i, at);
        }
        else {
            throw new InvalidOptionException("You can´t attack in this moment");
        }
    }

    /**
     * El jugador (player) usa un item
     * @throws InvalidOptionException Si la fase no permite usar un item.
     */
    public void useItem(PlayerIn player, int i) throws InvalidOptionException {
        if (waitForUseItem){
            controller.playerUseItem(player, i);
        }
        else {
            throw new InvalidOptionException("You can´t use an item in this moment");
        }
    }
}
