package personaje.abstract_clases;

import personaje.AttackType;
import personaje.interfaces.Ienemy;
import personaje.interfaces.Ipersonaje;

public abstract class  AbstractEnemy extends AbstractPersonaje implements Ienemy {

    //Constructor base
    public AbstractEnemy(int nivel, double ataque, double defensa, double hpmax){
        super(nivel, ataque, defensa, hpmax);
    }

    //Metodo que da las instrucciones de que hacer en caso de hacer atacado, esto según los personajes involucrados por lo cual-
    //solo se deja la firma, ya que en la clase de cada personaje se define instrucciones diferentes segun el caso.

    public abstract void attack(Ipersonaje p1);

    public void atacadoporMartillo(Ipersonaje p1){
        double daño= 2 * p1.getAtaque() * (p1.getNivel() / this.getDefensa());
        this.receiveDamage(daño);
    }
    public void atacadoporSalto(Ipersonaje p1){
        double daño= 1 * p1.getAtaque() * (p1.getNivel() / this.getDefensa());
        this.receiveDamage(daño);
    }

}
