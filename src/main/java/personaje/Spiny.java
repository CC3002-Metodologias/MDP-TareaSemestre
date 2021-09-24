package personaje;

import personaje.abstract_clases.AbstractEnemy;
import personaje.interfaces.Ipersonaje;

public class Spiny extends AbstractEnemy {
    //Constructor de la clase, usa el mismo de AbstractEnemy
    public Spiny(){
        super(2, 1, 3, 15);
    }

    @Override
    public void siendoAtacado(Ipersonaje p1, AttackType t1){
    }
}
