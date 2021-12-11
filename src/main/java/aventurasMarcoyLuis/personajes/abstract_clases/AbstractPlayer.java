package aventurasMarcoyLuis.personajes.abstract_clases;
import aventurasMarcoyLuis.AttackType;
import aventurasMarcoyLuis.controller.PlayerIn;
import aventurasMarcoyLuis.controller.phasesAndExceptions.InvalidAttackException;
import aventurasMarcoyLuis.interfaces.Ienemy;
import aventurasMarcoyLuis.interfaces.Ipersonaje;
import aventurasMarcoyLuis.interfaces.Iplayer;


import java.util.List;

/**
 * Se crea la clase abstracta de Player con todos los metodos y variables en comun que comparten
 * Luis y Carlos (posibles personajes a usar).
 */
public abstract class AbstractPlayer extends AbstractPersonaje implements Iplayer {

    /** FP exclusivo de Player (Marco o Luis por el momento) */
    private int FPmax;
    /** FP actual del jugador, No puede ser mayor al FP Max ni menor a 0 */
    private int FPactual;
    /** PlayerIn que funciona como controlodar del personaje */
    private PlayerIn playerIn;

    /**
     * Constructor que usa el mismo que AbstractPersonaje, pero se agrega la varible FP
     */
    public AbstractPlayer(int nivel, double ataque, double defensa, double hpmax, int fp){
        super(nivel,ataque,defensa,hpmax);
        this.FPmax = fp;
        this.FPactual = fp;
    }

    @Override
    public int getFPmax() {return this.FPmax;}
    /** metodo para obtener el FpActual */
    public int getFPactual() { return FPactual; }
    @Override
    public void setFPmax(int FPmax) {
        this.FPmax = FPmax;
        if (FPactual>FPmax){
            FPactual = FPmax;
        }
    }
    @Override
    public void setFPactual(int NewFp) {
        if (NewFp > FPmax){
            this.FPactual = FPmax;
        }
        else if  (NewFp < 0){
            this.FPactual = 0;
        } else {
            this.FPactual = NewFp;
        }

    }

    @Override
    public void useRedMushroom(){
        double hpmas = this.getHPmax() * 0.1;
        double hpnuevo = this.getHPactual() + hpmas;
        this.setHP(hpnuevo); //aumenta la vida segun su HP maxima
    }

    @Override
    public void setPlayerIn(PlayerIn playerIn) {
        this.playerIn = playerIn;
    }

    @Override
    public PlayerIn getPlayerIn() {
        return playerIn;
    }


    @Override
    public void useHoneySyrup(){
        int fpnuevo = this.getFPactual() + 3;
        this.setFPactual(fpnuevo); //aumenta 3 puntos de FP
    }

    //Implementacion de los ataques de players a enemigos
    @Override
    public void ataque(Ienemy e1, AttackType t1) throws InvalidAttackException {
        if (this.isKO() == Boolean.FALSE) {
            attack(e1, t1);
        }
    }

    /**
     * Metodo que da las instrucciones de que hacer en caso de hacer atacado, esto según los personajes involucrados por lo cual-
     * solo se deja la firma, ya que en la clase de cada personaje se define instrucciones diferentes segun el caso.
     */
    protected abstract void attack(Ienemy p1, AttackType attackType) throws InvalidAttackException;

    @Override
    public void atacado(Ienemy p1){
        double daño= 0.75 * p1.getAtaque() * (p1.getNivel() / this.getDefensa());
        this.receiveDamage(daño);
    }
    @Override
    public void pinchado(){
        double daño = getHPactual()*0.05;
        this.receiveDamage(daño);
    }
    @Override
    public void usoFP(int f){
        int fpa = getFPactual();
        setFPactual(fpa-f);

    }

}
