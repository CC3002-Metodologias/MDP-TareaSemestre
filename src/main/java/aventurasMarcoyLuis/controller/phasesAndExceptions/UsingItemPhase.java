package aventurasMarcoyLuis.controller.phasesAndExceptions;

import aventurasMarcoyLuis.controller.PlayerIn;

/**
 * Fase de cuando un personaje principal usa un Item dentro de su turno.
 */
public class UsingItemPhase extends Phase{
    private PlayerIn player;


    public UsingItemPhase(PlayerIn playerIn){
        player = playerIn;
        canStartRound = false;
        canChangeTurn = false;
        canChooseAction = false;
        canChooseItem = false;
        canChooseAttack = false;
        canChooseEnemy = false;
        canEnemiesAttack = false;
        canEndRound = false;
        waitForAttack = false;
        waitForUseItem = true;
        finishRound = false;
    }

    @Override
    public void toEndTurnPhase(){
        changePhase(new EndTurnPhase());
    }
}
