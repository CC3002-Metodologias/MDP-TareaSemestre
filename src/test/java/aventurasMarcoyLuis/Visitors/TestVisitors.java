package aventurasMarcoyLuis.Visitors;

import aventurasMarcoyLuis.interfaces.Ienemy;
import aventurasMarcoyLuis.interfaces.Iplayer;
import aventurasMarcoyLuis.model.personajes.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestVisitors {
    private final ListBooAttackable visitorBooAttackable = new ListBooAttackable();
    private final ListLuisAttackable visitorLuisAttackable = new ListLuisAttackable();
    private final List<Iplayer> listPlayers= new ArrayList<>();
    private final List<Iplayer> expectedPlayers = new ArrayList<>();
    private final List<Ienemy> listEnem= new ArrayList<>();
    private final List<Ienemy> expectedEnem = new ArrayList<>();
    private final Marco marco1 = new Marco();
    private final Marco marco2 = new Marco();
    private final Luis luis1 = new Luis();
    private final Luis luis2 = new Luis();

    @Test
    public void testVisitor(){
        visitorBooAttackable.exploreList_Iplayer(listPlayers);
        Assertions.assertEquals(expectedPlayers, visitorBooAttackable.getResult());

        listPlayers.add(marco1);
        listPlayers.add(marco2);
        visitorBooAttackable.exploreList_Iplayer(listPlayers);
        Assertions.assertEquals(expectedPlayers, visitorBooAttackable.getResult());

        expectedPlayers.add(luis1);
        expectedPlayers.add(luis2);
        listPlayers.add(luis1);
        listPlayers.add(luis2);
        visitorBooAttackable.exploreList_Iplayer(listPlayers);
        Assertions.assertEquals(expectedPlayers, visitorBooAttackable.getResult());

        visitorLuisAttackable.exploreList_Iplayer(listPlayers);
        assert visitorLuisAttackable.getResult().isEmpty();
    }

    @Test
    public void testLuisAttackableVisitor(){
        Goomba goomba1 = new Goomba();
        Goomba goomba2 = new Goomba();
        Spiny spiny1 = new Spiny();
        Boo boo1 = new Boo();
        Boo boo2 = new Boo();

        visitorLuisAttackable.exploreList_Ienemy(listEnem);
        Assertions.assertEquals(expectedEnem, visitorLuisAttackable.getResult());

        listEnem.add(boo1);
        Assertions.assertEquals(expectedEnem, visitorLuisAttackable.getResult());

        listEnem.add(goomba1);
        listEnem.add(goomba2);
        listEnem.add(spiny1);
        listEnem.add(boo2);

        expectedEnem.add(goomba1);
        expectedEnem.add(goomba2);
        expectedEnem.add(spiny1);

        visitorLuisAttackable.exploreList_Ienemy(listEnem);
        Assertions.assertEquals(expectedEnem, visitorLuisAttackable.getResult());

        visitorBooAttackable.exploreList_Ienemy(listEnem);
        assert visitorBooAttackable.getResult().isEmpty();
    }

}
