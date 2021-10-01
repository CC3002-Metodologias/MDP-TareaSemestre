package personaje;
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
    }

    @Test
    public void testEAttack(){

        //Boo ataca a Marco, lo que no deberia afectar a Marco.
        tBoo.attack(tMarco);
        Assertions.assertEquals(tMarco.getHPmax(),tMarco.getHPactual());

        //Boo ataca a Luis, lo que debería bajar la vida según el ataque y nivel del atacante, tambien de la defensa del atacado
        tBoo.attack(tLuis);
        Assertions.assertEquals(tLuis.getHPactual(), tLuis.getHPmax() -
                0.75*tBoo.getAtaque()*(tBoo.getNivel()/tLuis.getDefensa()));

        //Spiny ataca a Marco, lo que debería bajar la vida según el ataque y nivel del atacante, tambien de la defensa del atacado
        tSpiny.attack(tMarco);
        Assertions.assertEquals(tMarco.getHPactual(), tMarco.getHPmax() -
                0.75*tSpiny.getAtaque()*(tSpiny.getNivel()/tMarco.getDefensa()));

        //Goomba ataca a Luis, lo que debería bajar la vida según el ataque y nivel del atacante, tambien de la defensa del atacado
        double hpLuis = tLuis.getHPactual(); //Se calcula la vida que tiene Luis antes de ser atacado.
        tGoomba.attack(tLuis);
        Assertions.assertEquals(tLuis.getHPactual(), hpLuis -
                0.75*tGoomba.getAtaque()*(tGoomba.getNivel()/tLuis.getDefensa()));
    }

    @Test
    public  void testPAttack(){
        
    }
}
