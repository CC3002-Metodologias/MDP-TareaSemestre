package aventurasMarcoyLuis.controller.phasesAndExceptions;

import aventurasMarcoyLuis.controller.PlayerIn;

/**
 * Fase de cuando un personaje principal escoje una accion a realizar en su turno
 */
public class ChoosingActionPhase extends Phase{
    public ChoosingActionPhase(){
        canStartRound = false;
        canChangeTurn = false;
        canChooseAction = true;
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
    public void toSetAttackPhase(PlayerIn player){
        changePhase(new SetAttackPhase(player));
    }
    @Override
    public void toEndTurnPhase(){
        changePhase(new EndTurnPhase());
    }
    @Override
    public void toChoosingItemPhase(PlayerIn player){
        changePhase(new ChoosingItemPhase(player));
    }

}
