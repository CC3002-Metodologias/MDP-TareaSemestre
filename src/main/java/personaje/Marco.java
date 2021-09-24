package personaje;

import personaje.abstract_clases.AbstractPlayer;
import personaje.interfaces.Ienemy;

public class Marco extends AbstractPlayer {

    //Constructor de clase del personaje Marco
    public Marco(){
        super(1, 4, 5, 10, 50);
    }

    @Override
    public void siendoAtacado(Ienemy p1){}

}
