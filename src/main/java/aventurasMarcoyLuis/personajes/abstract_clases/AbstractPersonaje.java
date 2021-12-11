package aventurasMarcoyLuis.personajes.abstract_clases;

import aventurasMarcoyLuis.interfaces.Ipersonaje;

/**
 * personaje base, tanto enemigos como players
 */
public abstract class AbstractPersonaje implements Ipersonaje {

    //Se crean las variables correspondientes a los atributos de cada personaje

    /** Variable entera que referencia al nivel de un personaje */
    private int Nivel;
    /** Variable tipo double que referencia el atributo de Ataque de un personaje */
    private double Ataque;
    /** Variable tipo double que referencia el atributo de Defensa de un personaje */
    private double Defensa;
    /** Variable tipo double que referencia el atributo de HPmax de un personaje */
    private double HPmax;
    /** Variable tipo double que referencia el atributo de HPactual de un personaje,
     * No puede ser mayor a HPmax ni menor a 0 */
    private double HPactual;
    /** Variable que se ocupa para guardar el tipo de personaje que es en formato String */
    private String Name;

    /** Constructor base */
    public AbstractPersonaje(int nivel, double ataque, double defensa, double hpmax){
        this.Ataque = ataque;
        this.Defensa = defensa;
        this.HPmax = hpmax;
        this.Nivel = nivel;
        this.HPactual = hpmax;
    }

    //Metodos para obtener la información de los personajes(Sus variables)

    @Override
    public double getAtaque() {return Ataque;}
    @Override
    public double getDefensa() {return Defensa;}
    @Override
    public double getHPactual() {return HPactual;}
    @Override
    public double getHPmax() {return HPmax;}
    @Override
    public int getNivel() {return Nivel;}
    @Override
    public String getName(){return Name;}

    //Metodos para setear los atributos de los personajes
    @Override
    public void setNivel(int nivel) {
        this.Nivel = nivel;
    }
    @Override
    public void setAtaque(double ataque) {
        Ataque = ataque;
    }
    @Override
    public void setDefensa(double defensa) {
        Defensa = defensa;
    }
    @Override
    public void setHPmax(double HPmax) {
        this.HPmax = HPmax;
        if (HPactual>HPmax){
            HPactual = HPmax;
        }
    }
    @Override
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

    /**
     * metodo para setear el parametro Name, que guarda el nombre del tipo en formato String
     * @param name
     */
    public void setName(String name){
        Name = name;
    }

    //Metodo para saber si un personaje esta derrotado
    @Override
    public boolean isKO(){
        return (HPactual==0);
    }


    /**
     * Metodo que se ocupa de reducir el HpActual segun el daño recibido
     * @param daño
     */
    public void receiveDamage(double daño){
        double hpa = getHPactual();
        setHP(hpa-daño);
    }


}
