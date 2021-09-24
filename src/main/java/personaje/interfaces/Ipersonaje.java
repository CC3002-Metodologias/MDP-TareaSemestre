package personaje.interfaces;

import personaje.interfaces.Ienemy;

//Interfaz para poder llamar a ciertos metodos a nivel de clases abstractas que aún no estan definidos y que comparten diferentes clases.
public interface Ipersonaje {
    public void siendoAtacado(Ienemy p1);
}
