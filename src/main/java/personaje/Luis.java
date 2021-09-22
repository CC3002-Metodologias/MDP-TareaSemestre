package personaje;

public class Luis extends AbstractPlayer{

    public Luis(){
        super(1, 2, 1.5, 10, 50);
    }

    @Override
    public void SiendoAtacado(Ipersonaje p1, AttackType attackType){}
}
