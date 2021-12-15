package aventurasMarcoyLuis.model.Items;

import aventurasMarcoyLuis.interfaces.Iplayer;
import aventurasMarcoyLuis.interfaces.Items;

import java.util.List;

/**
 * Clase para representar un Item de tipo HoneySyrup
 */
public class HoneySyrup implements Items {

    private String Name;

    public HoneySyrup(){
        Name = "HoneySyrup";
    }
    @Override
    public void use(Iplayer p1){
        p1.useHoneySyrup();
    }

    @Override
    public void deleteBaul(List<Items> baul) {
        baul.remove(this);
    }

    @Override
    public String getName() {
        return Name;
    }
}
