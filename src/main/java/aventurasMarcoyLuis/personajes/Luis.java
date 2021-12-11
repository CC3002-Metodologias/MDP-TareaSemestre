package aventurasMarcoyLuis.personajes;

import aventurasMarcoyLuis.AttackType;
import aventurasMarcoyLuis.Visitors.Visitor;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidAttackException;
import aventurasMarcoyLuis.personajes.abstract_clases.AbstractPlayer;
import aventurasMarcoyLuis.interfaces.Ienemy;

public class Luis extends AbstractPlayer {

    //Constructor de clase del personaje Marco
    public Luis(){
        super(1, 11, 3, 15, 25);
        setName("Luis");
    }

    @Override
    public void attack(Ienemy p1, AttackType t1) throws InvalidAttackException {
        if(t1==AttackType.MARTILLO){
            usoFP(2);
            p1.siendoAtacadoLuis(this, t1);
        }else {
            usoFP(1);
            p1.siendoAtacadoLuis(this, t1);
        }
    }
    @Override
    public void siendoAtacadoporBoo(Boo b){
        this.atacado(b);
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
    public void accept(Visitor visitor) {
        visitor.visitLuis(this);
    }
}
