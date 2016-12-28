package geometrywars.GameObjects.Weapons;

import geometrywars.GameObjects.Bullet;
import geometrywars.GameObjects.Player.Player;





public class Shotgun extends Weapon{

    public Shotgun(Player owner) {
        super(owner, 7, 2, 35, 600);
    }

    @Override
    public void fire(double angle) {
        reloading = false;
        if (currentAmmo > 0) {
            currentAmmo--;
            owner.getObjectHandler().addObject(new Bullet(owner, angle*(0.85), fireRate));
            owner.getObjectHandler().addObject(new Bullet(owner, angle, fireRate));
            owner.getObjectHandler().addObject(new Bullet(owner, angle*(-0.85), fireRate));
            
        }
    }

    @Override
    public String toString(){
        return "Shotgun";
    }
}
