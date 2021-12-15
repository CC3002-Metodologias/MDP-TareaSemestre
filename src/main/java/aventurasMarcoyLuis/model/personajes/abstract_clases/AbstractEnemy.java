package aventurasMarcoyLuis.model.personajes.abstract_clases;

import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidAttackException;
import aventurasMarcoyLuis.interfaces.Ienemy;
import aventurasMarcoyLuis.interfaces.Iplayer;

import java.util.List;
import java.util.Random;

public abstract class  AbstractEnemy extends AbstractPersonaje implements Ienemy {

    protected Random random = new Random();

    //Constructor base
    public AbstractEnemy(int nivel, double ataque, double defensa, double hpmax) {
        super(nivel, ataque, defensa, hpmax);
    }


    @Override
    public void ataque(Iplayer e1) throws InvalidAttackException {
        if (this.isKO() == Boolean.FALSE) {
            attack(e1);
        }
    }

    //Metodo que da las instrucciones de que hacer en caso de hacer atacado, esto según los personajes involucrados por lo cual-
    //solo se deja la firma, ya que en la clase de cada personaje se define instrucciones diferentes segun el caso.
    protected abstract void attack(Iplayer p1) throws InvalidAttackException;

    @Override
    public void atacadoporMartillo(Iplayer p1) {
        double azar = random.nextInt(4);
        if (azar >= 1) {
            double daño = 1.5 * p1.getAtaque() * (p1.getNivel() / this.getDefensa());
            this.receiveDamage(daño);
        }
    }

    @Override
    public void atacadoporSalto(Iplayer p1) {
        double daño = 1 * p1.getAtaque() * (p1.getNivel() / this.getDefensa());
        this.receiveDamage(daño);
    }

    @Override
    public Iplayer randomAttack(List<Iplayer> list) throws InvalidAttackException {
        Iplayer victim = list.get(random.nextInt(list.size()));
        ataque(victim);
        return victim;
    }
    @Override
    public void deleteEList(List<Ienemy> ienemyList) {
        ienemyList.remove(this);
    }

    @Override
    public void setSeed(long seed){
        random.setSeed(seed);
    }
}


