package aventurasMarcoyLuis.Visitors;

import aventurasMarcoyLuis.interfaces.Ienemy;
import aventurasMarcoyLuis.interfaces.Iplayer;
import aventurasMarcoyLuis.personajes.*;

import java.util.List;

/**
 * Clase genérica y base de un Visitor puntual.
 */
public class Visitor {

    public void visitGoomba(Goomba goomba){

    }
    public void visitSpiny(Spiny spiny){

    }
    public void visitBoo(Boo boo){

    }
    public void visitMarco(Marco marco){

    }
    public void visitLuis(Luis luis){}
    public void exploreList_Ipersonaje(List<Iplayer> l){
        for (Iplayer iplayer : l){
            iplayer.accept(this);
        }
    }
    public void exploreList_Ienemy(List<Ienemy> l){
        for (Ienemy ienemy: l){
            ienemy.accept(this);
        }
    }

}
