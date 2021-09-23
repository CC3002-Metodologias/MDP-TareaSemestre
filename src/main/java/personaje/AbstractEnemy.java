package personaje;

public abstract class  AbstractEnemy extends AbstractPersonaje implements Ienemy{

    //Constructor base
    public AbstractEnemy(int nivel, double ataque, double defensa, double hpmax){
        super(nivel, ataque, defensa, hpmax);
    }

    //Metodo que da las instrucciones de que hacer en caso de hacer atacado, esto según los personajes involucrados por lo cual-
    //solo se deja la firma, ya que en la clase de cada personaje se define instrucciones diferentes segun el caso.
    public abstract void SiendoAtacado(Ipersonaje p1, AttackType t1);

    public void attack(Ipersonaje p1){
        if (this.isKO()==Boolean.FALSE){
            p1.SiendoAtacado(this);
        }
    }
}
