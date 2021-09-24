package personaje;

import personaje.abstract_clases.AbstractEnemy;
import personaje.interfaces.Ipersonaje;

public class Boo extends AbstractEnemy {
    //Constructor de la clase, usa el mismo de AbstractEnemy
    public Boo(){
        super(3, 4, 2, 5);
    }

    @Override
    public void siendoAtacado(Ipersonaje p1, AttackType t1){

    }
}
