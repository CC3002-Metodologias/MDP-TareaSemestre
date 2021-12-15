package aventurasMarcoyLuis.interfaces;

import aventurasMarcoyLuis.model.AttackType;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidAttackException;

import java.util.List;

/**
 * Interfaz de un enemigo
 */
public interface Ienemy extends Ipersonaje{

    /** metodo para implementar el ataque solo si el Ipersonaje atacante NO esta KO. */
    void ataque(Iplayer e1) throws InvalidAttackException;


    /**
     * metodos siendoAtacadopor-Player- que recibe que player lo esta atacando y el tipo de ataque para luego por
     * cada clase de referencia a un enemigo decida que efectos tendrá en sus variables según el player y tipo de araque.
     */
    void siendoAtacadoLuis(Iplayer p1, AttackType t1) throws InvalidAttackException;
    /**
     * metodos siendoAtacadopor-Player- que recibe que player lo esta atacando y el tipo de ataque para luego por
     * cada clase de referencia a un enemigo decida que efectos tendrá en sus variables según el player y tipo de araque.
     */
    void siendoAtacadoMarco(Iplayer p1, AttackType t1) throws InvalidAttackException;

    /** metodo empleado para recivir un ataque de tipo Martillo por el Ipersonaje p1 */
    void atacadoporMartillo(Iplayer p1);

    /** metodo empleado para recivir un ataque de tipo Salto por el Ipersonaje p1 */
    void atacadoporSalto(Iplayer p1);

    /** Metodo para eleminar el iEnemy de la lista de enemigos luego de ser KO. */
    void deleteEList(List<Ienemy> ienemyList);
    /**
     * Metodo para plantar semilla en la variable random
     */
    void setSeed(long seed);

    /**
     * Ataca a un Iplayer que es escogido al azar desde @param list
     * @return el Iplayer atacado.
     * @throws InvalidAttackException
     */
    Iplayer randomAttack(List<Iplayer> list) throws InvalidAttackException;

}
