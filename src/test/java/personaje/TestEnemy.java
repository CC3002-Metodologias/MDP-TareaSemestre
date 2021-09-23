package personaje;

import org.junit.jupiter.api.*;

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

        System.out.print(testSpiny.getNivel());
        System.out.print(testBoo.getAtaque());
        System.out.print(testGoomba.getDefensa());
        System.out.print(testBoo.getHPactual());
        System.out.print(testSpiny.getHPmax());
    }

    @Test
    public void setsTest(){
        //Primero comprobamos que todos los enemigos esten con sus variables correctamente
        Assertions.assertEquals(testBoo.getAtaque(), 4);
        Assertions.assertEquals(testGoomba.getDefensa(), 4);
        Assertions.assertEquals(testSpiny.getNivel(), 2);
        Assertions.assertEquals(testBoo.getHPactual(), 5);
        Assertions.assertEquals(testGoomba.getHPmax(), 7);

        //Ahora usamos el metodo set para cambiar los valores de las diferentes variables
        testBoo.setAtaque(3);
        testGoomba.setDefensa(5);
        testSpiny.setNivel(4);
        testBoo.setHP(2);
        testGoomba.setHPmax(10);

        //Comprobamos que las variables que fueron cambiadas tengan los nuevos valores entregados
        Assertions.assertEquals(testBoo.getAtaque(), 3);
        Assertions.assertEquals(testGoomba.getDefensa(), 5);
        Assertions.assertEquals(testSpiny.getNivel(), 4);
        Assertions.assertEquals(testBoo.getHPactual(), 2);
        Assertions.assertEquals(testGoomba.getHPmax(), 10);
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
}
