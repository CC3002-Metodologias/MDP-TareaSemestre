package personaje.controller;

import personaje.interfaces.Ienemy;
import personaje.interfaces.Ipersonaje;
import personaje.interfaces.Items;

import java.util.List;

public class Partida {
    public int Kills;
    public int Score;
    public int Round;
    public personaje.Marco Marco;
    public personaje.Luis Luis;
    public List<Ipersonaje> PlayersList;
    public List<Ienemy> EnemyList;
    public List<Items> Baul;
}
