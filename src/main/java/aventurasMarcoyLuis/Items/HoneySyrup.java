package aventurasMarcoyLuis.Items;

import aventurasMarcoyLuis.interfaces.Ipersonaje;
import aventurasMarcoyLuis.interfaces.Items;

import java.util.List;

public class HoneySyrup implements Items {

    private String Name;

    public HoneySyrup(){
        Name = "HoneySyrup";
    }
    @Override
    public void use(Ipersonaje p1){
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
