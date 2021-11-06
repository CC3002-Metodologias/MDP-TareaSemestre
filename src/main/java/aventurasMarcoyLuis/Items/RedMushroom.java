package aventurasMarcoyLuis.Items;

import aventurasMarcoyLuis.interfaces.Ipersonaje;
import aventurasMarcoyLuis.interfaces.Items;

import java.util.List;

public class RedMushroom implements Items {

    @Override
    public void use(Ipersonaje p1){
        p1.useRedMushroom();
    }
    @Override
    public void deleteBaul(List<Items> baul) {
        baul.remove(this);

    }
}
