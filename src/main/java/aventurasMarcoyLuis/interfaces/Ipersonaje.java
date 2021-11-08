package aventurasMarcoyLuis.interfaces;

import aventurasMarcoyLuis.AttackType;
import aventurasMarcoyLuis.Boo;
import aventurasMarcoyLuis.Goomba;
import aventurasMarcoyLuis.Spiny;

import java.util.List;

//Interfaz para poder llamar a ciertos metodos a nivel de clases abstractas que aún no estan definidos y que comparten diferentes clases.
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
    /** metodo para obtener el FpMax del jugador */
    int getFPmax();
    /** metodo para obtener el nombre del tipo en formato string del jugador */
    String getName();

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
    /** metodo para setear el FpMax del jugador */
    void setFPmax(int v);
    /** metodo para setear el FpMax del jugador */
    void setFPactual(int v);


    /** metodo para implementar el ataque solo si el Ipersonaje atacante NO esta KO. */
    void ataque(Ienemy e1, AttackType a1);

    /**
     * metodos siendoAtacadopor-Enemigo- que recibe que enemigo lo esta atacando para luego por
     * cada clase de referencia a un player decida que efectos tendrá en sus variables según el enemigo que lo ataque
     * */
    void siendoAtacadoporBoo(Boo boo);
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
    /** Metodo para eleminar el Ipersonaje de la lista de personajes principales luego de ser KO. */
    void deletePList(List<Ipersonaje> ipersonajeList);

    /** metodo que entrega un booleano referenciando si el Ipersonaje esta KO */
    boolean isKO();



}
