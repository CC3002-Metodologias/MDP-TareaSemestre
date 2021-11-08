package aventurasMarcoyLuis.interfaces;

import aventurasMarcoyLuis.AttackType;

import java.util.List;

public interface Ienemy {

    /** metodo para obtener el ataque del enemigo */
    double getAtaque();
    /** metodo para obtener el nivel del enemigo */
    int getNivel();
    /** metodo para obtener el HpActual del enemigo */
    double getHPactual();
    /** metodo para obtener el nombre del tipo en formato string del enemigo */
    String getName();

    /** metodo para implementar el ataque solo si el Ipersonaje atacante NO esta KO. */
    void ataque(Ipersonaje e1);


    /**
     * metodos siendoAtacadopor-Player- que recibe que player lo esta atacando y el tipo de ataque para luego por
     * cada clase de referencia a un enemigo decida que efectos tendrá en sus variables según el player y tipo de araque.
     */
    void siendoAtacadoLuis(Ipersonaje p1, AttackType t1);
    /**
     * metodos siendoAtacadopor-Player- que recibe que player lo esta atacando y el tipo de ataque para luego por
     * cada clase de referencia a un enemigo decida que efectos tendrá en sus variables según el player y tipo de araque.
     */
    void siendoAtacadoMarco(Ipersonaje p1, AttackType t1);

    /** metodo empleado para recivir un ataque de tipo Martillo por el Ipersonaje p1 */
    void atacadoporMartillo(Ipersonaje p1);

    /** metodo empleado para recivir un ataque de tipo Salto por el Ipersonaje p1 */
    void atacadoporSalto(Ipersonaje p1);

    /** Metodo para eleminar el iEnemy de la lista de enemigos luego de ser KO. */
    void deleteEList(List<Ienemy> ienemyList);

    /** metodo que entrega un booleano referenciando si el Ipersonaje esta KO */
    boolean isKO();

    /**
     * Metodo para plantar semilla en la variable random
     */
    void setSeed(long seed);
}
