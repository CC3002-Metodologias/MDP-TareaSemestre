package aventurasMarcoyLuis.model.personajes;

import aventurasMarcoyLuis.Visitors.Visitor;
import aventurasMarcoyLuis.interfaces.IEnemAbyLuis;
import aventurasMarcoyLuis.interfaces.Iplayer;
import aventurasMarcoyLuis.model.AttackType;
import aventurasMarcoyLuis.model.personajes.abstract_clases.AbstractEnemy;

//Clase del enemigo Goomba
public class Goomba extends AbstractEnemy implements IEnemAbyLuis {
    //Constructor de la clase, usa el mismo de AbstractEnemy
    public Goomba(){
        super( 1, 5, 2, 8);
        setName("Goomba");
    }

    @Override
    public void siendoAtacadoLuis(Iplayer p1, AttackType t1) {
        if(t1 == AttackType.MARTILLO){
            atacadoporMartillo(p1);
        }
        else if (t1 == AttackType.SALTO){
            atacadoporSalto(p1);
        }
    }
    @Override
    public void siendoAtacadoMarco(Iplayer p1, AttackType t1) {
        if(t1 == AttackType.MARTILLO){
            atacadoporMartillo(p1);
        }
        else if (t1 == AttackType.SALTO){
            atacadoporSalto(p1);
        }
    }


    @Override
    public void attack(Iplayer p1) {
        p1.siendoAtacadoporGoomba(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitGoomba(this);
    }
}
