package aventurasMarcoyLuis.controller.phasesAndExceptions;

/**
 * Fase de cuando finaliza el turno de un personaje o de los enemigos.
 */
public class EndTurnPhase extends Phase{

    public EndTurnPhase(){
        canStartRound = false;
        canChangeTurn = true;
        canChooseAction = false;
        canChooseItem = false;
        canChooseAttack = false;
        canChooseEnemy = false;
        canEnemiesAttack = false;
        canEndRound = true;
        waitForAttack = false;
        waitForUseItem = false;
        finishRound = false;
    }

    public void toEnemiesTurnPhase(){
        changePhase(new EnemiesTurnPhase());
    }
    public void toChoosingActionPhase(){
        changePhase(new ChoosingActionPhase());
    }
}
