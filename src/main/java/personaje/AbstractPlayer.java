package personaje;
import Items.Items;
import java.util.List;

//Se crea la clase abstracta de Player con todos los metodos y variables en comun que comparten Luis y Carlos
public abstract class AbstractPlayer extends AbstractPersonaje{

    private double FP; //FP exclusivo de Player (Marco o Luis por el momento)
    public List<Items> armamento; //Se agrega una variable correspondiente al equipamento de objetos

    public AbstractPlayer(int nivel, double ataque, double defensa, double hpmax, double fp){
        super(nivel,ataque,defensa,hpmax);
        this.FP = fp;
    }

    public double getFP() {return this.FP;}
    public void setFP(double FP) {this.FP = FP;}

}
