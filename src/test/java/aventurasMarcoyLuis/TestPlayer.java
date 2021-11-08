package aventurasMarcoyLuis;
import aventurasMarcoyLuis.personajes.Luis;
import aventurasMarcoyLuis.personajes.Marco;
import org.junit.jupiter.api.*;
import aventurasMarcoyLuis.Items.HoneySyrup;
import aventurasMarcoyLuis.Items.RedMushroom;

//Clase para testear los players
public class TestPlayer {

    private Marco testMarco;
    private Luis testLuis;


    @BeforeEach
    public void setUp() {
        testMarco= new Marco();
        testLuis = new Luis();
    }

    @Test
    public void constructorTest(){
        Assertions.assertEquals(testMarco,testMarco);
        Assertions.assertEquals(testMarco,testMarco);

        Assertions.assertNotEquals(testLuis, testMarco);
        Assertions.assertNotEquals(testMarco, new Object());

        System.out.println("Nivel de Marco = " + testMarco.getNivel());
        System.out.println("Ataque de Marco = " + testMarco.getAtaque());
        System.out.println("HP maximo de Luis = " + testLuis.getHPmax());
        System.out.println("Defensa de Luis = " + testLuis.getDefensa());

    }


    @Test
    public void isKO_Test(){

        Boolean f = Boolean.FALSE;
        Boolean t = Boolean.TRUE;

        //Aseguramos que al momento de crear a los players no esten KO
        Assertions.assertEquals(testLuis.isKO(), f);
        Assertions.assertEquals(testMarco.isKO(), f);

        //Le bajamos la vida a 0 a los tres players (variable HPactual).
        testLuis.setHP(0);
        testMarco.setHP(0);

        //Comprobamos que ahora todos los players (objetos) esten KO.
        Assertions.assertEquals(testLuis.isKO(), t);
        Assertions.assertEquals(testMarco.isKO(), t);
    }

    @Test
    public void itemsTest(){

        HoneySyrup honey = new HoneySyrup();
        RedMushroom mushroom = new RedMushroom();


        //Bajamos tanto HP Actual como FP actual de Luis y Marco para que puedan ser subidos al usar los Items
        testLuis.setHP(5);
        testLuis.setFPactual(40);
        testMarco.setHP(9.5);
        testMarco.setFPactual(30);

        //Utiizamos los metodos para utilizar los diferentes items.
        testLuis.useStar();
        testMarco.useStar();
        testLuis.useHoneySyrup();
        testMarco.useHoneySyrup();
        testLuis.useRedMushroom();
        testMarco.useRedMushroom();

        //Ahora comprobamos que los items hayan hecho efecto en las diferentes variables(HPactual y FPactual)
        Assertions.assertEquals(testLuis.getHPactual(),7);
        Assertions.assertEquals(testLuis.getFPactual(), 43);
        Assertions.assertEquals(testMarco.getHPactual(),10);
        Assertions.assertEquals(testMarco.getFPactual(), 33);

        System.out.println("HP max de Marco = " + testMarco.getHPmax());
        System.out.println("HP actual de Marco = " + testMarco.getHPactual());

    }
}
