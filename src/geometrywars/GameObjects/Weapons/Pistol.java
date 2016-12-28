package geometrywars.GameObjects.Weapons;

import geometrywars.GameObjects.Player.Player;





public class Pistol extends Weapon{
    
    
    public Pistol(Player owner) {
        super(owner, 12, 4, 10, 100);
    }
    
    @Override
    public String toString(){
        return "Pistol";
    }

}
