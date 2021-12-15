package aventurasMarcoyLuis.controller.phasesAndExceptions;

/**
 * Fase de cuando finaliza una ronda, ya sea porque se gano o perdio.
 */
public class EndRoundPhase extends Phase{

    public EndRoundPhase(){
        canStartRound = false;
        canChangeTurn = false;
        canChooseAction = false;
        canChooseItem = false;
        canChooseAttack = false;
        canChooseEnemy = false;
        canEnemiesAttack = false;
        canEndRound = true;
        waitForAttack = false;
        waitForUseItem = false;
        finishRound = true;
    }

    @Override
    public void toStartRoundPhase(){
        changePhase(new StartRoundPhase());
    }
}
