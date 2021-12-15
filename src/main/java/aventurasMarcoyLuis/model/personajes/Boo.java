package aventurasMarcoyLuis.model.personajes;

import aventurasMarcoyLuis.Visitors.ListBooAttackable;
import aventurasMarcoyLuis.Visitors.Visitor;
import aventurasMarcoyLuis.model.AttackType;
import aventurasMarcoyLuis.model.personajes.abstract_clases.AbstractEnemy;
import aventurasMarcoyLuis.interfaces.Iplayer;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidAttackException;

import java.util.List;

/**
 * Representa a un personaje de tipo BOO.
 */
public class Boo extends AbstractEnemy {
    //Constructor de la clase, usa el mismo de AbstractEnemy
    public Boo(){
        super(3, 4, 2, 5);
        setName("Boo");
    }


    @Override
    public void siendoAtacadoLuis(Iplayer p1, AttackType t1) throws InvalidAttackException {
       throw new InvalidAttackException("Boo no puede ser atacado por Luis");
    }
    @Override
    public void siendoAtacadoMarco(Iplayer p1, AttackType t1) throws InvalidAttackException {
        if(t1 == AttackType.MARTILLO){
            throw new InvalidAttackException("Boo no puede ser atacado por Martillo");
        }
        else if (t1 == AttackType.SALTO){
            atacadoporSalto(p1);
        }
    }
    @Override
    public void attack(Iplayer p1) throws InvalidAttackException {
        p1.siendoAtacadoporBoo(this);
    }
    @Override
    public Iplayer randomAttack(List<Iplayer> list) throws InvalidAttackException {
        List<Iplayer> AtacablePorBooList;
        ListBooAttackable v = new ListBooAttackable();
        v.exploreList_Iplayer(list);
        AtacablePorBooList = v.getResult();

        if (AtacablePorBooList.isEmpty()){
            return null;
        }
        else {
            Iplayer victim = AtacablePorBooList.get(random.nextInt(AtacablePorBooList.size()));
            ataque(victim);
            return victim;
        }

    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visitBoo(this);
    }

}
