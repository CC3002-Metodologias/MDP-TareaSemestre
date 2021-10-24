package personaje.interfaces;

import personaje.AttackType;
import personaje.Boo;
import personaje.Goomba;
import personaje.Spiny;
import personaje.interfaces.Ienemy;

import java.util.List;

//Interfaz para poder llamar a ciertos metodos a nivel de clases abstractas que aún no estan definidos y que comparten diferentes clases.
public interface Ipersonaje {

    //metodos para obtener la estadistica de ataque y nivel, util para calcular el daño
    double getAtaque();
    int getNivel();

    //metodo para implementar el ataque solo si el Ipersonaje atacante NO esta KO.
    void ataque(Ienemy e1, AttackType a1);

    //metodos siendoAtacadopor-Enemigo- que recibe que enemigo lo esta atacando para luego por
    //cada clase de referencia a un player decida que efectos tendrá en sus variables según el enemigo que lo ataque
    void siendoAtacadoporBoo(Boo boo);
    void siendoAtacadoporGoomba(Goomba goomba);
    void siendoAtacadoporSpiny(Spiny spiny);

    //Metodo que calcula y cambia la variable de HPactual del player atacado segun las variables del atacante y del jugador
    //siendo atacado. Este se usa en caso de que el player pueda ser atacado por el Ienemy en cuestión.
    void atacado(Ienemy e1);
    //metodo para producir la consecuencia de ser pinchado por Spiny sobre un player.
    void pinchado();
    //metodo para reducir el FPactual que recibe un int de la cantidad que hay reducir
    void usoFP(int f);

    //Metodo para usar un item de tipo HoneySyrup
    void useHoneySyrup();
    //Metodo para usar un item de tipo RedMushroom
    void useRedMushroom();
    //Metodo para eleminar el Ipersonaje de la lista de personajes principales luego de ser KO.
    void deletePList(List<Ipersonaje> ipersonajeList);

    boolean isKO();
}
