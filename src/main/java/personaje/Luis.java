package personaje;

import personaje.abstract_clases.AbstractPlayer;
import personaje.interfaces.Ienemy;

public class Luis extends AbstractPlayer {

    //Constructor de clase del personaje Marco
    public Luis(){
        super(1, 2, 1.5, 20, 50);
    }

    @Override
    public void siendoAtacado(Ienemy p1){}
}
