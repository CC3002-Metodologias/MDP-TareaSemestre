package aventurasMarcoyLuis.interfaces;

import aventurasMarcoyLuis.Visitors.Visitor;

public interface Ipersonaje {
    /** metodo para obtener el ataque del jugador */
    double getAtaque();
    /** metodo para obtener el nivel del jugador */
    int getNivel();
    /** metodo para obtener la defensa del jugador */
    double getDefensa();
    /** metodo para obtener el HpActual del jugador */
    double getHPactual();
    /** metodo para obtener el HpMax del jugador */
    double getHPmax();
    /** metodo para setear el ataque del jugador */
    void setAtaque(double v);
    /** metodo para setear el nivel del jugador */
    void setNivel(int v);
    /** metodo para setear la defensa del jugador */
    void setDefensa(double v);
    /** metodo para setear el HpMax del jugador */
    void setHPmax(double v);
    /** metodo para setear el HpActual del jugador */
    void setHP(double v);
    /** metodo para obtener el nombre del tipo en formato string del jugador */
    String getName();
    /** metodo que entrega un booleano referenciando si el Ipersonaje esta KO */
    boolean isKO();
    /**
     * metodo para aceptar un visitor
     */
    void accept(Visitor visitor);
}
