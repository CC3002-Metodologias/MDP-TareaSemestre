package aventurasMarcoyLuis.personajes;

import aventurasMarcoyLuis.AttackType;
import aventurasMarcoyLuis.abstract_clases.AbstractEnemy;
import aventurasMarcoyLuis.interfaces.Ipersonaje;

public class Spiny extends AbstractEnemy {
    //Constructor de la clase, usa el mismo de AbstractEnemy
    public Spiny(){
        super(2, 1, 3, 5);
        setName("Spiny");
    }

    @Override
    public void siendoAtacadoLuis(Ipersonaje p1, AttackType t1) {
        if(t1 == AttackType.MARTILLO){
            atacadoporMartillo(p1);
        }
        else if (t1 == AttackType.SALTO){
            p1.pinchado();
        }
    }
    @Override
    public void siendoAtacadoMarco(Ipersonaje p1, AttackType t1) {
        if(t1 == AttackType.MARTILLO){
            atacadoporMartillo(p1);
        }
        else if (t1 == AttackType.SALTO){
            p1.pinchado();
        }
    }

    @Override
    public void attack(Ipersonaje p1) {
        p1.siendoAtacadoporSpiny(this);
    }
}
