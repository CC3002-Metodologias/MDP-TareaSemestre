package aventurasMarcoyLuis.controller.phasesAndExceptions;

import aventurasMarcoyLuis.controller.PlayerIn;

/**
 * Fase de cuando un personaje principal escoje un item para usar en su turno
 */
public class ChoosingItemPhase extends Phase{
    private PlayerIn player;

    public ChoosingItemPhase(PlayerIn playerIn){
        canStartRound = false;
        canChangeTurn = false;
        canChooseAction = false;
        canChooseItem = true;
        canChooseAttack = false;
        canChooseEnemy = false;
        canEnemiesAttack = false;
        canEndRound = false;
        waitForAttack = false;
        waitForUseItem = false;
        finishRound = false;
        player = playerIn;
    }

    @Override
    public void toUsingItemPhase(PlayerIn player){
        changePhase(new UsingItemPhase(player));
    }

}
