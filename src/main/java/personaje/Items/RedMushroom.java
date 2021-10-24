package personaje.Items;

import personaje.interfaces.Ipersonaje;
import personaje.interfaces.Items;

import java.util.ArrayList;

public class RedMushroom implements Items {

    @Override
    public void use(Ipersonaje p1){
        p1.useRedMushroom();
    }
    @Override
    public void deleteBaul(ArrayList<Items> baul) {
        baul.remove(this);

    }
}
