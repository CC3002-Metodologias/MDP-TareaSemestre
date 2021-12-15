package aventurasMarcoyLuis.model.Items;

import aventurasMarcoyLuis.interfaces.Iplayer;
import aventurasMarcoyLuis.interfaces.Items;

import java.util.List;

/**
 * Clase para representar un Item de tipo RedMushroom
 */
public class RedMushroom implements Items {

    private String Name;

    public RedMushroom(){
        Name = "RedMushroom";
    }
    @Override
    public void use(Iplayer p1){
        p1.useRedMushroom();
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
