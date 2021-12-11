package aventurasMarcoyLuis.personajes;

import aventurasMarcoyLuis.AttackType;
import aventurasMarcoyLuis.Visitors.Visitor;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidAttackException;
import aventurasMarcoyLuis.personajes.abstract_clases.AbstractPlayer;
import aventurasMarcoyLuis.interfaces.Ienemy;

public class Marco extends AbstractPlayer {

    //Constructor de clase del personaje Marco
    public Marco(){
        super(1, 9, 2, 20, 25);
        setName("Marco");
    }

    @Override
    public void attack(Ienemy p1, AttackType t1) throws InvalidAttackException {
        if(t1==AttackType.MARTILLO){
            usoFP(2);
            p1.siendoAtacadoMarco(this, t1);
        }else {
            usoFP(1);
            p1.siendoAtacadoMarco(this, t1);
        }
    }

    @Override
    public void siendoAtacadoporGoomba(Goomba g){
        this.atacado(g);
    }
    @Override
    public void siendoAtacadoporSpiny(Spiny s){
        this.atacado(s);
    }

    @Override
    public void siendoAtacadoporBoo(Boo boo) throws InvalidAttackException {
        throw new InvalidAttackException("Marco no puede ser atacado por BOO");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitMarco(this);
    }
}

