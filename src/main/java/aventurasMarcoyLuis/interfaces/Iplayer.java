package aventurasMarcoyLuis.interfaces;

import aventurasMarcoyLuis.AttackType;
import aventurasMarcoyLuis.controller.PlayerIn;
import aventurasMarcoyLuis.personajes.Boo;
import aventurasMarcoyLuis.personajes.Goomba;
import aventurasMarcoyLuis.personajes.Spiny;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidAttackException;


//Interfaz para poder llamar a ciertos metodos a nivel de clases abstractas que aún no estan definidos y que comparten diferentes clases.
public interface Iplayer extends Ipersonaje{

    /** metodo para obtener el FpMax del jugador */
    int getFPmax();
    /** metodo para setear el FpMax del jugador */
    void setFPmax(int v);
    /** metodo para setear el FpMax del jugador */
    void setFPactual(int v);
    /** metodo para implementar el ataque solo si el Ipersonaje atacante NO esta KO. */
    void ataque(Ienemy e1, AttackType a1) throws InvalidAttackException;

    /**
     * metodos siendoAtacadopor-Enemigo- que recibe que enemigo lo esta atacando para luego por
     * cada clase de referencia a un player decida que efectos tendrá en sus variables según el enemigo que lo ataque
     * */
    void siendoAtacadoporBoo(Boo boo) throws InvalidAttackException;
    /**
     * metodos siendoAtacadopor-Enemigo- que recibe que enemigo lo esta atacando para luego por
     * cada clase de referencia a un player decida que efectos tendrá en sus variables según el enemigo que lo ataque
     * */
    void siendoAtacadoporGoomba(Goomba goomba);
    /**
     * metodos siendoAtacadopor-Enemigo- que recibe que enemigo lo esta atacando para luego por
     * cada clase de referencia a un player decida que efectos tendrá en sus variables según el enemigo que lo ataque
     * */
    void siendoAtacadoporSpiny(Spiny spiny);

    /**
     * Metodo que calcula y cambia la variable de HPactual del player atacado segun las variables del atacante y del jugador
     * siendo atacado. Este se usa en caso de que el player pueda ser atacado por el Ienemy en cuestión.
     */
    void atacado(Ienemy e1);
    /** metodo para producir la consecuencia de ser pinchado por Spiny sobre un player. */
    void pinchado();
    /** metodo para reducir el FPactual que recibe un int de la cantidad que hay reducir */
    void usoFP(int f);

    /** Metodo para usar un item de tipo HoneySyrup */
    void useHoneySyrup();

    /** Metodo para usar un item de tipo RedMushroom */
    void useRedMushroom();
    /**
     * Setea la variable playerIn, entregandole un nuevo valor
     */
    void setPlayerIn(PlayerIn playerIn);

    /**
     * @return playerIn, el controlador del Ipersonaje.
     */
    PlayerIn getPlayerIn();


}
