package aventurasMarcoyLuis.abstract_clases;

import aventurasMarcoyLuis.interfaces.Ienemy;
import aventurasMarcoyLuis.interfaces.Ipersonaje;

import java.util.List;

public abstract class  AbstractEnemy extends AbstractPersonaje implements Ienemy {

    //Constructor base
    public AbstractEnemy(int nivel, double ataque, double defensa, double hpmax) {
        super(nivel, ataque, defensa, hpmax);
    }


    @Override
    public void ataque(Ipersonaje e1) {
        if (this.isKO() == Boolean.FALSE) {
            attack(e1);
        }
    }

    //Metodo que da las instrucciones de que hacer en caso de hacer atacado, esto según los personajes involucrados por lo cual-
    //solo se deja la firma, ya que en la clase de cada personaje se define instrucciones diferentes segun el caso.
    protected abstract void attack(Ipersonaje p1);

    @Override
    public void atacadoporMartillo(Ipersonaje p1) {
        double azar = Math.random() * 10;
        if (azar >= 2.5) {
            double daño = 1.5 * p1.getAtaque() * (p1.getNivel() / this.getDefensa());
            this.receiveDamage(daño);
        }
    }

    @Override
    public void atacadoporSalto(Ipersonaje p1) {
        double daño = 1 * p1.getAtaque() * (p1.getNivel() / this.getDefensa());
        this.receiveDamage(daño);
    }

    @Override
    public void deleteEList(List<Ienemy> ienemyList) {
        ienemyList.remove(this);
    }
}


