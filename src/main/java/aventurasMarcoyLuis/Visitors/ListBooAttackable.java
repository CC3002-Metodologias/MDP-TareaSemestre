package aventurasMarcoyLuis.Visitors;

import aventurasMarcoyLuis.interfaces.Iplayer;
import aventurasMarcoyLuis.model.personajes.Luis;


import java.util.ArrayList;
import java.util.List;

/**
 * Visitor para guardar en una lista los Ipersonajes atacables por Boo(Solo Marco por ahora).
 */
public class ListBooAttackable extends Visitor{
    private final List<Iplayer> list = new ArrayList<Iplayer>();

    @Override
    public void visitLuis(Luis luis){
        list.add(luis);
    }
    public List<Iplayer> getResult(){
        return list;
    }

}
