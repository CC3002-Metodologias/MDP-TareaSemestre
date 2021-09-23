package personaje;

//personaje base, tanto enemigos como players
public abstract class AbstractPersonaje {

    //Se crean las variables correspondientes a los atributos de cada personaje
    protected int Nivel;
    protected double Ataque;
    protected double Defensa;
    protected double HPmax;
    protected double HPactual;

    //Constructor base
    public AbstractPersonaje(int nivel, double ataque, double defensa, double hpmax){
        this.Ataque = ataque;
        this.Defensa = defensa;
        this.HPmax = hpmax;
        this.Nivel = nivel;
        this.HPactual = hpmax;
    }

    //Metodos para obtener la información de los personajes(Sus variables)
    public double getAtaque() {return Ataque;}
    public double getDefensa() {return Defensa;}
    public double getHPactual() {return HPactual;}
    public double getHPmax() {return HPmax;}
    public int getNivel() {return Nivel;}

    //Metodos para setear los atributos de los personajes
    public void setNivel(int nivel) {
        this.Nivel = nivel;
    }

    public void setAtaque(double ataque) {
        Ataque = ataque;
    }

    public void setDefensa(double defensa) {
        Defensa = defensa;
    }

    public void setHPmax(double HPmax) {
        this.HPmax = HPmax;
    }

    public void setHP(double HPactual) {
        this.HPactual = HPactual;
    }

    //Metodo para saber si un personaje esta derrotado
    public Boolean isKO(){
        return (HPactual==0);
    }

}
