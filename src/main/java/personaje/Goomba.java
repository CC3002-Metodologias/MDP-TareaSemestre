package personaje;

import personaje.abstract_clases.AbstractEnemy;
import personaje.interfaces.Ipersonaje;

//Clase del enemigo Goomba
public class Goomba extends AbstractEnemy {
    //Constructor de la clase, usa el mismo de AbstractEnemy
    public Goomba(){
        super( 1, 2, 4, 7);
    }

    @Override
    public void siendoAtacado(Ipersonaje p1, AttackType t1){
    }
}
