package personaje;

public class Goomba extends AbstractEnemy{
    //Constructor de la clase, usa el mismo de AbstractEnemy
    public Goomba(){
        super( 1, 2, 4, 7);
    }

    @Override
    public void SiendoAtacado(Ipersonaje p1, AttackType t1){
    }
}
