package personaje;

//personaje base, tanto enemigos como players
public abstract class AbstractPersonaje {

    //Se crean las variables correspondientes a los atributos de cada personaje
    private int Nivel;
    private double Ataque;
    private double Defensa;
    private double HPmax;
    private double HPactual; //No puede ser mayor a HPmax ni menor a 0

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
    public void setHP(double newHp) {
        if (newHp > this.HPmax){ //HPactual no puede ser mayor al HPmax
            this.HPactual = HPmax;
        }
        else if (newHp<0){ //HPactual no puede ser menor a 0
            this.HPactual = 0;
        } else{
            this.HPactual = newHp;
        }
    }

    //Metodo para saber si un personaje esta derrotado
    public Boolean isKO(){
        return (HPactual==0);
    }

}
