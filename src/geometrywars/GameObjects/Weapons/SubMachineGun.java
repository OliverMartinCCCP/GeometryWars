package geometrywars.GameObjects.Weapons;

import geometrywars.GameObjects.Player.Player;





public class SubMachineGun extends Weapon{

    public SubMachineGun(Player owner) {
        super(owner, 30, 8, 20, 75);
    }

    @Override
    public String toString(){
        return "Submachine";
    }

}
