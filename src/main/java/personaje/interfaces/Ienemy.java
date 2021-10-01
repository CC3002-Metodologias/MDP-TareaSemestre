package personaje.interfaces;

import personaje.AttackType;

public interface Ienemy {
    public double getAtaque();
    public int getNivel();
    void siendoAtacadoLuis(Ipersonaje p1, AttackType t1);
    void siendoAtacadoMarco(Ipersonaje p1, AttackType t1);
}
