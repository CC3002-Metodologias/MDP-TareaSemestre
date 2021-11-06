package aventurasMarcoyLuis.interfaces;

import aventurasMarcoyLuis.AttackType;

import java.util.List;

public interface Ienemy {

    //metodos para obtener la estadistica de ataque y nivel, util para calcular el daño
    double getAtaque();
    int getNivel();

    //metodo para implementar el ataque solo si el Ienemy atacante NO esta KO.
    void ataque(Ipersonaje e1);

    //metodos siendoAtacadopor-Player- que recibe que player lo esta atacando y el tipo de ataque para luego por
    //cada clase de referencia a un enemigo decida que efectos tendrá en sus variables según el player y tipo de araque.
    void siendoAtacadoLuis(Ipersonaje p1, AttackType t1);
    void siendoAtacadoMarco(Ipersonaje p1, AttackType t1);

    //metodos para ajustar la vida(HPactual) tras ser atacado con el daño recibido según el tipo de ataque y atacante,
    void atacadoporMartillo(Ipersonaje p1);
    void atacadoporSalto(Ipersonaje p1);

    //Metodo para eleminar el iEnemy de la lista de enemigos luego de ser KO.
    void deleteEList(List<Ienemy> ienemyList);

    boolean isKO();
}
