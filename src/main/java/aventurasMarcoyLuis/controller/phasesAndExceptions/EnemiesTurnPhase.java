package aventurasMarcoyLuis.controller.phasesAndExceptions;

/**
 * Fase de cuando los enemigos realizan sus turnos y atacan
 */
public class EnemiesTurnPhase extends Phase{

    public EnemiesTurnPhase(){
        canStartRound = false;
        canChangeTurn = false;
        canChooseAction = false;
        canChooseItem = false;
        canChooseAttack = false;
        canChooseEnemy = false;
        canEnemiesAttack = true;
        canEndRound = false;
        waitForAttack = false;
        waitForUseItem = false;
        finishRound = false;
    }

    @Override
    public void toEndRoundPhase(){
        changePhase(new EndRoundPhase());
    }

    @Override
    public void toEndTurnPhase(){
        changePhase(new EndTurnPhase());
    }


}
