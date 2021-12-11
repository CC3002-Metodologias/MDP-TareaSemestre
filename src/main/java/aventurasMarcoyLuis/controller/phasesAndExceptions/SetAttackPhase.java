package aventurasMarcoyLuis.controller.phasesAndExceptions;

import aventurasMarcoyLuis.controller.PlayerIn;

/**
 * Fase de cuando un personaje principal escoje el tipo de ataque y el enemigo a atacar en su turno.
 */
public class SetAttackPhase extends Phase{
    private PlayerIn player;
    public SetAttackPhase(PlayerIn playerIn){
        canStartRound = false;
        canChangeTurn = false;
        canChooseAction = false;
        canChooseItem = false;
        canChooseAttack = true;
        canChooseEnemy = true;
        canEnemiesAttack = false;
        canEndRound = false;
        waitForAttack = false;
        waitForUseItem = false;
        finishRound = false;
        player = playerIn;
    }
    @Override
    public void toAttackingPhase(PlayerIn player){
        changePhase(new AttackingPhase(player));
    }
}
