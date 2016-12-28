package geometrywars.utilities;

import geometrywars.GameObjects.Weapons.Weapon;
import java.util.TimerTask;


public class WeaponReload extends TimerTask {
    
    private Weapon weapon;
    
    
    public WeaponReload(Weapon weapon) {
        this.weapon = weapon;
               
    }
    @Override
    public void run() {
        if ( weapon.getAmmo() < weapon.getClipSize() )
            weapon.addBullet();
        else{
            weapon.isReloading(false);
            cancel();
        }        
    }

}
