package aventurasMarcoyLuis.personajes;

import aventurasMarcoyLuis.AttackType;
import aventurasMarcoyLuis.Visitors.Visitor;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidAttackException;
import aventurasMarcoyLuis.interfaces.Iplayer;
import aventurasMarcoyLuis.personajes.abstract_clases.AbstractEnemy;
import aventurasMarcoyLuis.interfaces.Ipersonaje;

import java.util.List;

//Clase del enemigo Goomba
public class Goomba extends AbstractEnemy {
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
