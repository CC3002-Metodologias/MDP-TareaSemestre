package aventurasMarcoyLuis.controller.phasesAndExceptions;

import aventurasMarcoyLuis.controller.PlayerIn;

/**
 * Fase de cuando un personaje principal ataca a un enemigo
 */
public class AttackingPhase extends Phase {
    private PlayerIn playerAttacker;

    public AttackingPhase(PlayerIn playerIn){
        playerAttacker = playerIn;
        canStartRound = false;
        canChangeTurn = false;
        canChooseAction = false;
        canChooseItem = false;
        canChooseAttack = false;
        canChooseEnemy = false;
        canEnemiesAttack = false;
        canEndRound = false;
        waitForAttack = true;
        waitForUseItem = false;
        finishRound = false;
    }

    @Override
    public void toEndTurnPhase(){
        changePhase(new EndTurnPhase());
    }
    @Override
    public void toEndRoundPhase(){
        changePhase(new EndRoundPhase());
    }


}
