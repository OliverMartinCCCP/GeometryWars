package geometrywars.GameObjects.Weapons;

import geometrywars.GameObjects.Player.Player;





public class MachineGun extends Weapon{

    public MachineGun(Player owner){
        super(owner, 100, 13, 40,50);
    }
    
    @Override
    public String toString(){
        return "MachineGun";
    }
    
}
