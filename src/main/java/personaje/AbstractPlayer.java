package personaje;
import java.util.List;

//Se crea la clase abstracta de Player con todos los metodos y variables en comun que comparten Luis y Carlos (posibles personajes a usar)
public abstract class AbstractPlayer extends AbstractPersonaje implements Ipersonaje{

    protected double FPmax; //FP exclusivo de Player (Marco o Luis por el momento)
    protected double FPactual; //FP actual del jugador
    public List<Items> armamento; //Se agrega una variable correspondiente al equipamento de objetos

    //Constructor que usa el mismo que AbstractPersonaje, pero se agrega la varible FP
    public AbstractPlayer(int nivel, double ataque, double defensa, double hpmax, double fp){
        super(nivel,ataque,defensa,hpmax);
        this.FPmax = fp;
        this.FPactual = fp;
    }

    //Metodos para saber el valor de FP y tambien poder setearla
    public double getFPmax() {return this.FPmax;}
    public double getFPactual() { return FPactual; }
    public void setFPmax(double FPmax) {
        this.FPmax = FPmax;
    }
    public void setFPactual(double FP) {this.FPactual = FP;}

    //Metodo para ver los elementos en el armamento
    public List<Items> inventario() {return armamento;}

    //Metodo para añadir elementos en el armamento
    public void addItem(Items i){
        armamento.add(i);
    }

    //Metodo para usar un item de tipo Star
    public void useStar(){
        //invisible
    }
    //Metodo para usar un item de tipo RedMushroom
    public void useRedMushroom(){
        double hpmas = this.getHPmax() * 0.1;
        double hpnuevo = this.getHPactual() + hpmas;
        this.setHP(hpnuevo); //aumenta la vida segun su HP maxima
    }
    //Metodo para usar un item de tipo HoneySyrup
    public void useHoneySyrup(){
        double fpnuevo = this.getFPactual() + 3;
        this.setFPactual(fpnuevo); //aumenta 3 puntos de FP
    }

    //Implementacion de los ataques de players a enemigos
    public void attack(Ienemy p1, AttackType attackType) { //metodo para atacar a un enemigo

        if (this.isKO()==Boolean.FALSE){ //solo si el atacante NO esta KO
            p1.SiendoAtacado(this, attackType); //se implementa el metodo 'siendoAtacado' en el jugador que recibe el ataque
        }
    }

    //Metodo que da las instrucciones de que hacer en caso de hacer atacado, esto según los personajes involucrados por lo cual-
    //solo se deja la firma, ya que en la clase de cada personaje se define instrucciones diferentes segun el caso.
    public abstract void SiendoAtacado(Ienemy p1);
}
