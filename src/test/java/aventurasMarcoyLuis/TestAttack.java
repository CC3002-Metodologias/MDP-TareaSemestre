package aventurasMarcoyLuis;
import aventurasMarcoyLuis.personajes.*;
import org.junit.jupiter.api.*;

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
    }

    @Test
    public void testEAttack(){

        //Boo ataca a Marco, lo que no deberia afectar a Marco.
        tBoo.ataque(tMarco);
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


        //se baja la vida a tSpiny a 0, entonces este esta KO.
        tSpiny.setHP(0);
        hpLuis = tLuis.getHPactual();
        //Como spiny esta KO, tLuis no deberia recibir el ataque
        tSpiny.ataque(tMarco);
        Assertions.assertEquals(tLuis.getHPactual(), hpLuis);

    }

    @Test
    public void testPAttack() {
        //Luis ataca a tBoo con martillo por lo que no debería ser atacado.
        tLuis.ataque(tBoo, AttackType.MARTILLO);
        Assertions.assertEquals(tBoo.getHPmax(), tBoo.getHPactual());
        //Luis uso 2 FP.
        Assertions.assertEquals(tLuis.getFPactual(), tLuis.getFPmax() - 2);

        //tMarco ataca a tBoo por salto por lo que debería recudir la vida tBoo según la formula.
        tMarco.ataque(tBoo, AttackType.SALTO);
        Assertions.assertEquals(tBoo.getHPactual(), tBoo.getHPmax() -
                tMarco.getAtaque() * (tMarco.getNivel() / tBoo.getDefensa()));
        //tMarco uso 1 FP.
        Assertions.assertEquals(tMarco.getFPactual(), tMarco.getFPmax() - 1);


        //tMarco ataca a tGoomba con martillo por lo que debería ser dañar a tGoomba si es que no tiene mala suerte(75% prob).
        double hpGoomba = tGoomba.getHPactual();
        tMarco.ataque(tGoomba, AttackType.MARTILLO);
        assert (tGoomba.getHPactual() == hpGoomba -
                1.5 * tMarco.getAtaque() * (tMarco.getNivel() / tGoomba.getDefensa()) || tGoomba.getHPactual() == hpGoomba);
        //tMarco uso 2 FP.
        Assertions.assertEquals(tMarco.getFPactual(), tMarco.getFPmax() - 3);

        //tMarco ataca a tSpiny con martillo por lo que debería ser dañar a tSpiny si es que no tiene mala suerte(75% prob).
        tMarco.ataque(tSpiny, AttackType.MARTILLO);
        assert (tSpiny.getHPactual() == tSpiny.getHPmax() -
                1.5 * tMarco.getAtaque() * (tMarco.getNivel() / tSpiny.getDefensa()) || tSpiny.getHPactual() == tSpiny.getHPmax());
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

    //test para ver si el ataque con martillo a veces falla por su probabilidad
    @Test
    public void testMartillo(){
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
