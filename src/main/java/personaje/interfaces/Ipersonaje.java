package personaje.interfaces;

import personaje.Boo;
import personaje.Goomba;
import personaje.Spiny;
import personaje.interfaces.Ienemy;

//Interfaz para poder llamar a ciertos metodos a nivel de clases abstractas que aún no estan definidos y que comparten diferentes clases.
public interface Ipersonaje {
    double getAtaque();
    int getNivel();

    void siendoAtacadoporBoo(Boo boo);
    void siendoAtacadoporGoomba(Goomba goomba);
    void siendoAtacadoporSpiny(Spiny spiny);

    void pinchado();
}
