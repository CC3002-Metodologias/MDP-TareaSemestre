package aventurasMarcoyLuis.personajes;
import aventurasMarcoyLuis.controller.PlayerIn;
import org.junit.jupiter.api.*;

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

        Assertions.assertEquals("Marco", testMarco.getName());
        Assertions.assertEquals("Luis", testLuis.getName());


    }

    @Test
    public void set_getTest(){
        testMarco.setHP(12);
        Assertions.assertEquals(12,testMarco.getHPactual());
        testMarco.setAtaque(15);
        Assertions.assertEquals(15,testMarco.getAtaque());
        //Le seteamos en FP una cantidad mayor a la max por lo que deberia quedar solo en la max
        testMarco.setFPactual(100);
        Assertions.assertEquals(testMarco.getFPmax(), testMarco.getFPactual());
        //Le seteamos un HP max menor a la cantidad de HP actual por lo que tambien deberia bajar esa stats
        testMarco.setHPmax(10);
        Assertions.assertEquals(10,testMarco.getHPmax());
        Assertions.assertEquals(10,testMarco.getHPactual());

        testMarco.setHP(15);
        Assertions.assertEquals(10,testMarco.getHPactual());
        testMarco.setHP(-5);
        Assertions.assertEquals(0,testMarco.getHPactual());

        testLuis.setDefensa(7);
        Assertions.assertEquals(7,testLuis.getDefensa());
        testLuis.setNivel(4);
        Assertions.assertEquals(4,testLuis.getNivel());
        testLuis.setFPactual(5);
        Assertions.assertEquals(5,testLuis.getFPactual());

        testLuis.setFPactual(-1);
        Assertions.assertEquals(0,testLuis.getFPactual());



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

        //Bajamos tanto HP Actual como FP actual de Luis y Marco para que puedan ser subidos al usar los Items
        testLuis.setHP(5);
        testLuis.setFPmax(45);
        testLuis.setFPactual(40);
        testMarco.setHP(9.5);
        testMarco.setFPmax(35);
        testMarco.setFPactual(30);

        //Utiizamos los metodos para utilizar los diferentes items.
        testLuis.useHoneySyrup();
        testMarco.useHoneySyrup();
        testLuis.useRedMushroom();
        testMarco.useRedMushroom();

        //Ahora comprobamos que los items hayan hecho efecto en las diferentes variables(HPactual y FPactual)
        Assertions.assertEquals(testLuis.getHPactual(),5 + testLuis.getHPmax()*0.1);
        Assertions.assertEquals(testLuis.getFPactual(), 43);
        Assertions.assertEquals(testMarco.getHPactual(),9.5 + testMarco.getHPmax()*0.1);
        Assertions.assertEquals(testMarco.getFPactual(), 33);

    }

    @Test
    public void controllerPLayer(){
        PlayerIn playerIn = new PlayerIn(testMarco);
        Assertions.assertEquals(playerIn, testMarco.getPlayerIn());

    }
}
