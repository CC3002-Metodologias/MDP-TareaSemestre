package aventurasMarcoyLuis.Visitors;

import aventurasMarcoyLuis.interfaces.Ienemy;
import aventurasMarcoyLuis.model.personajes.Goomba;
import aventurasMarcoyLuis.model.personajes.Spiny;

import java.util.ArrayList;
import java.util.List;

/**
 * Visitor para guardar en una lista los Ienemy's atacables por Luis(Goomba y Spiny).
 */
public class ListLuisAttackable extends Visitor{
    private final List<Ienemy> list = new ArrayList<>();

    @Override
    public void visitGoomba(Goomba goomba){
        list.add(goomba);
    }
    @Override
    public void visitSpiny(Spiny spiny){
        list.add(spiny);
    }

    public List<Ienemy> getResult(){
        return list;
    }
}
