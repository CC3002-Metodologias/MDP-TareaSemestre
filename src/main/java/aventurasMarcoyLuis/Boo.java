package aventurasMarcoyLuis;

import aventurasMarcoyLuis.abstract_clases.AbstractEnemy;
import aventurasMarcoyLuis.interfaces.Ipersonaje;

public class Boo extends AbstractEnemy {
    //Constructor de la clase, usa el mismo de AbstractEnemy
    public Boo(){
        super(3, 4, 2, 5);
        setName("Boo");
    }


    @Override
    public void siendoAtacadoLuis(Ipersonaje p1, AttackType t1) {
       ;
    }
    @Override
    public void siendoAtacadoMarco(Ipersonaje p1, AttackType t1) {
        if(t1 == AttackType.MARTILLO){
        }
        else if (t1 == AttackType.SALTO){
            atacadoporSalto(p1);
        }
    }
    @Override
    public void attack(Ipersonaje p1) {
        p1.siendoAtacadoporBoo(this);
    }


}
