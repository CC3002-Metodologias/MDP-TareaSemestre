package aventurasMarcoyLuis.controller.phasesAndExceptions;

/**
 * Fase de cuando se empieza una nueva ronda.
 */
public class StartRoundPhase extends Phase{

    public StartRoundPhase(){
        canStartRound = true;
        canChangeTurn = false;
        canChooseAction = false;
        canChooseItem = false;
        canChooseAttack = false;
        canChooseEnemy = false;
        canEnemiesAttack = false;
        canEndRound = false;
        waitForAttack = false;
        waitForUseItem = false;
        finishRound = false;
    }
    @Override
    public void toChoosingActionPhase(){
        changePhase(new ChoosingActionPhase());
    }
}
