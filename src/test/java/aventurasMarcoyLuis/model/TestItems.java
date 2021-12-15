package aventurasMarcoyLuis.model;

import aventurasMarcoyLuis.interfaces.Items;
import aventurasMarcoyLuis.model.Items.HoneySyrup;
import aventurasMarcoyLuis.model.Items.RedMushroom;
import aventurasMarcoyLuis.model.personajes.Luis;
import aventurasMarcoyLuis.model.personajes.Marco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestItems {
    private HoneySyrup honeySyrup = new HoneySyrup();
    private RedMushroom redMushroom = new RedMushroom();
    private Marco marco = new Marco();
    private Luis luis = new Luis();

    @Test
    public void useTest(){
        marco.setHP(marco.getHPmax()/2);
        luis.setFPactual(luis.getFPmax()-3);

        honeySyrup.use(luis);
        redMushroom.use(marco);

        Assertions.assertEquals(luis.getFPmax(), luis.getFPactual());
        Assertions.assertEquals((marco.getHPmax()/2) + marco.getHPmax()*0.1, marco.getHPactual());
    }

    @Test
    public void getNameTest(){
        Assertions.assertEquals("HoneySyrup", honeySyrup.getName());
        Assertions.assertEquals("RedMushroom", redMushroom.getName());
    }

    @Test
    public void deleteMeListTest(){
        List<Items> list = new ArrayList<>();
        list.add(honeySyrup);
        list.add(redMushroom);

        honeySyrup.deleteBaul(list);
        assert !list.contains(honeySyrup);

        redMushroom.deleteBaul(list);
        assert !list.contains(redMushroom);
    }
}
