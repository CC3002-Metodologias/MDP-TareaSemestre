package aventurasMarcoyLuis.model.personajes;
import aventurasMarcoyLuis.model.*;
import aventurasMarcoyLuis.interfaces.Iplayer;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidAttackException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class TestAttack {

    private Boo tBoo;
    private Goomba tGoomba;
    private Spiny tSpiny;
    private Marco tMarco;
    private Luis tLuis;

    @BeforeEach
    public void setUp(){
        tBoo = new Boo();
        tGoomba = new Goomba();
        tSpiny = new Spiny();
        tMarco = new Marco();
        tLuis = new Luis();
        tMarco.setAtaque(5);
        tLuis.setAtaque(7);
        tGoomba.setSeed(1);
        tSpiny.setSeed(1687268492);
    }

    @Test
    public void testEAttack() throws InvalidAttackException {

        //Boo intenta atacar a Marco por lo que no debería ser atacado y deberia tirar error.
        try {
            tBoo.ataque(tMarco);
            assert false;
        } catch (InvalidAttackException exception){
            assert true;
        }
        Assertions.assertEquals(tMarco.getHPmax(),tMarco.getHPactual());

        //Boo ataca a Luis, lo que debería bajar la vida según el ataque y nivel del atacante, tambien de la defensa del atacado
        tBoo.ataque(tLuis);
        Assertions.assertEquals(tLuis.getHPactual(), tLuis.getHPmax() -
                0.75*tBoo.getAtaque()*(tBoo.getNivel()/tLuis.getDefensa()));

        //Spiny ataca a Marco, lo que debería bajar la vida según el ataque y nivel del atacante, tambien de la defensa del atacado
        tSpiny.ataque(tMarco);
        Assertions.assertEquals(tMarco.getHPactual(), tMarco.getHPmax() -
                0.75*tSpiny.getAtaque()*(tSpiny.getNivel()/tMarco.getDefensa()));

        //Goomba ataca a Luis, lo que debería bajar la vida según el ataque y nivel del atacante, tambien de la defensa del atacado
        double hpLuis = tLuis.getHPactual(); //Se calcula la vida que tiene Luis antes de ser atacado.
        tGoomba.ataque(tLuis);
        Assertions.assertEquals(tLuis.getHPactual(), hpLuis -
                0.75*tGoomba.getAtaque()*(tGoomba.getNivel()/tLuis.getDefensa()));

        tLuis.setHP(10);
        tSpiny.attack(tLuis);
        Assertions.assertEquals(10 -
                0.75*tSpiny.getAtaque()*(tSpiny.getNivel()/tLuis.getDefensa()), tLuis.getHPactual());

        tMarco.setHP(10);
        tGoomba.attack(tMarco);
        Assertions.assertEquals(10 -
                0.75*tGoomba.getAtaque()*(tGoomba.getNivel()/tMarco.getDefensa()), tMarco.getHPactual());
        //se baja la vida a tSpiny a 0, entonces este esta KO.
        tSpiny.setHP(0);
        hpLuis = tLuis.getHPactual();
        //Como spiny esta KO, tLuis no deberia recibir el ataque
        tSpiny.ataque(tMarco);
        Assertions.assertEquals(tLuis.getHPactual(), hpLuis);

    }

    @Test
    public void testPAttack() throws InvalidAttackException {
        //Luis intenta atacar a tBoo con martillo por lo que no debería ser atacado y deberia tirar error.
        try {
            tLuis.ataque(tBoo, AttackType.MARTILLO);
            assert false;
        } catch (InvalidAttackException exception){
            assert true;
        }
        Assertions.assertEquals(tBoo.getHPmax(), tBoo.getHPactual());
        //Luis uso 2 FP.
        Assertions.assertEquals(tLuis.getFPactual(), tLuis.getFPmax() - 2);

        //tMarco ataca a tBoo por salto por lo que debería recudir la vida tBoo según la formula.
        tMarco.ataque(tBoo, AttackType.SALTO);
        Assertions.assertEquals(tBoo.getHPactual(), tBoo.getHPmax() -
                tMarco.getAtaque() * (tMarco.getNivel() / tBoo.getDefensa()));
        //tMarco uso 1 FP.
        Assertions.assertEquals(tMarco.getFPactual(), tMarco.getFPmax() - 1);


        //tMarco ataca a tGoomba con martillo por lo que debería ser dañar a tGoomba dada la semilla impuesta.
        double hpGoomba = tGoomba.getHPactual();
        tMarco.ataque(tGoomba, AttackType.MARTILLO);
        assert (tGoomba.getHPactual() == hpGoomba -
                1.5 * tMarco.getAtaque() * (tMarco.getNivel() / tGoomba.getDefensa()));
        //tMarco uso 2 FP.
        Assertions.assertEquals(tMarco.getFPactual(), tMarco.getFPmax() - 3);

        //tMarco ataca a tSpiny con martillo por lo que debería no dañar a tSpiny dada la semilla puesta(75% prob).
        tMarco.ataque(tSpiny, AttackType.MARTILLO);
        assert (tSpiny.getHPactual() == tSpiny.getHPmax());
        //tMarco uso 2 FP.
        Assertions.assertEquals(tMarco.getFPactual(), tMarco.getFPmax() - 5);

        //tLuis ataca a Spiny con salto por lo que tLuis debería recibir daño tras ser pinchado y tSpiny debería no recibir daño.
        double hpSpiny = tSpiny.getHPactual();
        tLuis.ataque(tSpiny, AttackType.SALTO);
        Assertions.assertEquals(tSpiny.getHPactual(), hpSpiny);
        Assertions.assertEquals(tLuis.getHPactual(), tLuis.getHPmax() * 0.95);
        //tLuis uso 1 FP.
        Assertions.assertEquals(tLuis.getFPactual(), tLuis.getFPmax() - 3);

        //se baja la vida a tMarco a 0, entonces este esta KO.
        tMarco.setHP(0);
        hpSpiny = tSpiny.getHPactual();
        //Como tMarco esta KO, tLuis no deberia recibir el ataque
        tMarco.ataque(tSpiny, AttackType.MARTILLO);
        Assertions.assertEquals(tSpiny.getHPactual(), hpSpiny);
        //tMarco no ejecuto el ataque entonces no uso FP.
        Assertions.assertEquals(tMarco.getFPactual(), tMarco.getFPmax() - 5);
    }

    @Test
    public void testPAttack2() throws InvalidAttackException {
        //Debe lanzar una exception si esta correcto.
        try {
            tMarco.attack(tBoo, AttackType.MARTILLO);
            assert false;
        } catch (InvalidAttackException exception) {
            exception.printStackTrace();
            assert true;
        }

        double goombaLife = tGoomba.getHPactual();
        tMarco.attack(tGoomba, AttackType.SALTO);
        Assertions.assertEquals(goombaLife -
                tMarco.getAtaque() * (tMarco.getNivel() / tGoomba.getDefensa()), tGoomba.getHPactual());

        goombaLife = tGoomba.getHPactual();
        tLuis.attack(tGoomba,AttackType.SALTO);
        Assertions.assertEquals(goombaLife -
                tLuis.getAtaque() * (tLuis.getNivel() / tGoomba.getDefensa()), tGoomba.getHPactual());

        tGoomba.setHP(tGoomba.getHPmax());
        goombaLife = tGoomba.getHPactual();
        tGoomba.setSeed(1);
        tLuis.attack(tGoomba, AttackType.MARTILLO);
        Assertions.assertEquals(goombaLife -
                1.5*tLuis.getAtaque() * (tLuis.getNivel() / tGoomba.getDefensa()), tGoomba.getHPactual());

        double lifeSpiny = tSpiny.getHPactual();
        tMarco.attack(tSpiny, AttackType.SALTO);
        Assertions.assertEquals(lifeSpiny, tSpiny.getHPactual());
        Assertions.assertEquals(tMarco.getHPmax()*0.95, tMarco.getHPactual());

    }


    @Test
    public void randomAttackEnemies() throws InvalidAttackException {
        List<Iplayer> iplayerList = new ArrayList<Iplayer>();
        iplayerList.add(tMarco);

        Iplayer victimBoo = tBoo.randomAttack(iplayerList);
        assert victimBoo == null;
        iplayerList.add(tLuis);
        victimBoo = tBoo.randomAttack(iplayerList);
        assert victimBoo == tLuis;
        Assertions.assertEquals(tLuis.getHPmax() -
                0.75*tBoo.getAtaque()*(tBoo.getNivel()/tLuis.getDefensa()), tLuis.getHPactual());

        tLuis.setHP(tLuis.getHPmax());
        tGoomba.setSeed(23);
        Iplayer victimGoomba = tGoomba.randomAttack(iplayerList);
        assert (victimGoomba.getClass() == Marco.class | victimGoomba.getClass() == Luis.class);
        Assertions.assertEquals(victimGoomba.getHPmax() -
                0.75*tGoomba.getAtaque()*(tGoomba.getNivel()/victimGoomba.getDefensa()), victimGoomba.getHPactual());



    }
    //test para ver si el ataque con martillo a veces falla por su probabilidad
    @Test
    public void testMartillo() throws InvalidAttackException {
        tMarco.ataque(tGoomba, AttackType.MARTILLO);
        System.out.println("HP tGoomba antes de ser atacado: "+ tGoomba.getHPmax()+ "HPactual: "+tGoomba.getHPactual());
        tMarco.ataque(tSpiny, AttackType.MARTILLO);
        System.out.println("HP tSpiny antes de ser atacado: "+ tSpiny.getHPmax()+ "HPactual: "+tSpiny.getHPactual());
        double hpSpiny = tSpiny.getHPactual();
        double hpGoomba = tGoomba.getHPactual();
        tMarco.ataque(tGoomba, AttackType.MARTILLO);
        System.out.println("HP tGoomba antes de ser atacado: "+ hpGoomba+ "HPactual: "+tGoomba.getHPactual());
        tMarco.ataque(tSpiny, AttackType.MARTILLO);
        System.out.println("HP tSpiny antes de ser atacado: "+ hpSpiny+ "HPactual: "+tSpiny.getHPactual());

    }
}
