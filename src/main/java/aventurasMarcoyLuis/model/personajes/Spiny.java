package aventurasMarcoyLuis.model.personajes;

import aventurasMarcoyLuis.Visitors.Visitor;
import aventurasMarcoyLuis.interfaces.IEnemAbyLuis;
import aventurasMarcoyLuis.interfaces.Iplayer;
import aventurasMarcoyLuis.model.AttackType;
import aventurasMarcoyLuis.model.personajes.abstract_clases.AbstractEnemy;

/**
 * Representa a un personaje de tipo Spiny.
 * */
public class Spiny extends AbstractEnemy implements IEnemAbyLuis {
    //Constructor de la clase, usa el mismo de AbstractEnemy
    public Spiny(){
        super(2, 5, 2, 10);
        setName("Spiny");
    }

    @Override
    public void siendoAtacadoLuis(Iplayer p1, AttackType t1) {
        if(t1 == AttackType.MARTILLO){
            atacadoporMartillo(p1);
        }
        else if (t1 == AttackType.SALTO){
            p1.pinchado();
        }
    }
    @Override
    public void siendoAtacadoMarco(Iplayer p1, AttackType t1) {
        if(t1 == AttackType.MARTILLO){
            atacadoporMartillo(p1);
        }
        else if (t1 == AttackType.SALTO){
            p1.pinchado();
        }
    }

    @Override
    public void attack(Iplayer p1) {
        p1.siendoAtacadoporSpiny(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitSpiny(this);
    }
}
