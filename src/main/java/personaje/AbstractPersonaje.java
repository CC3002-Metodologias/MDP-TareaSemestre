package personaje;

//personaje base, tanto enemigos como players
public abstract class AbstractPersonaje implements Ipersonaje {

    //Se crean las variables correspondientes a los atributos de cada personaje
    private final int Nivel;
    private final double Ataque;
    private final double Defensa;
    private final double HPmax;
    private double HPactual;

    //Constructor base
    public AbstractPersonaje(int nivel, double ataque, double defensa, double hpmax){
        this.Ataque = ataque;
        this.Defensa = defensa;
        this.HPmax = hpmax;
        this.Nivel = nivel;
        this.HPactual = hpmax;
    }

    //Metodos para obtener la información de los personajes
    public double getAtaque() {return Ataque;}
    public double getDefensa() {return Defensa;}
    public double getHPactual() {return HPactual;}
    public double getHPmax() {return HPmax;}
    public int getNivel() {return Nivel;}

    //Metodos para setear los atributos de los personajes
    public void setHP(double HPactual) {
        this.HPactual = HPactual;
    }
    public Boolean isKO(){
        return (HPactual==0);
    }

    //Implementacion de los ataques entre personajes
    public void attack(Ipersonaje p1, AttackType attackType) {p1.SiendoAtacado(this, attackType);};
    public abstract void SiendoAtacado(Ipersonaje p1, AttackType attackType);

}
