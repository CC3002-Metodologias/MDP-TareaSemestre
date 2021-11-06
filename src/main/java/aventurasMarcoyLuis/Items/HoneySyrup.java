package aventurasMarcoyLuis.Items;

import aventurasMarcoyLuis.interfaces.Ipersonaje;
import aventurasMarcoyLuis.interfaces.Items;

import java.util.List;

public class HoneySyrup implements Items {

    @Override
    public void use(Ipersonaje p1){
        p1.useHoneySyrup();
    }

    @Override
    public void deleteBaul(List<Items> baul) {
        baul.remove(this);

    }
}
