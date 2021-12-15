package aventurasMarcoyLuis.model.personajes;
import aventurasMarcoyLuis.interfaces.Ienemy;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

//Clase para testear los enemigos
public class TestEnemy {

    private Boo testBoo;
    private Goomba testGoomba;
    private Spiny testSpiny;

    @BeforeEach
    public void setUp() {
        testGoomba= new Goomba();
        testBoo = new Boo();
        testSpiny = new Spiny();
    }

    @Test
    public void constructorTest(){
        Assertions.assertEquals(testSpiny,testSpiny);
        Assertions.assertEquals(testBoo,testBoo);
        Assertions.assertEquals(testGoomba,testGoomba);

        Assertions.assertNotEquals(testBoo, testSpiny);
        Assertions.assertNotEquals(testGoomba, testSpiny);
        Assertions.assertNotEquals(testSpiny, new Object());

        System.out.println(testSpiny.getNivel());
        System.out.println(testBoo.getAtaque());
        System.out.println(testGoomba.getDefensa());
        System.out.println(testBoo.getHPactual());
        System.out.println(testSpiny.getHPmax());
    }


    @Test
    public void isKO_Test(){

        Boolean f = Boolean.FALSE;
        Boolean t = Boolean.TRUE;

        //Aseguramos que al momento de crear a los enemigos no esten KO
        Assertions.assertEquals(testBoo.isKO(), f);
        Assertions.assertEquals(testGoomba.isKO(), f);
        Assertions.assertEquals(testSpiny.isKO(), f);

        //Le bajamos la vida a 0 a los tres enemigos (variable HPactual).
        testBoo.setHP(0);
        testGoomba.setHP(0);
        testSpiny.setHP(0);

        //Comprobamos que ahora todos los enemigos (objetos) esten KO.
        Assertions.assertEquals(testBoo.isKO(), t);
        Assertions.assertEquals(testGoomba.isKO(), t);
        Assertions.assertEquals(testSpiny.isKO(), t);

    }

    @Test
    public void deleteEnemyinList(){
        List<Ienemy> list = new ArrayList<Ienemy>();
        list.add(testBoo);
        list.add(testGoomba);

        testBoo.deleteEList(list);
        assert !list.contains(testBoo);

        testGoomba.deleteEList(list);
        assert !list.contains(testGoomba);
    }
}
