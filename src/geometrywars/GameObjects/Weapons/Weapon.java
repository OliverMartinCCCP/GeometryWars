package geometrywars.GameObjects.Weapons;

import geometrywars.GameObjects.Bullet;
import geometrywars.GameObjects.Player.Player;
import geometrywars.utilities.WeaponReload;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Weapon {

    protected int maxAmmo;
    protected int currentAmmo;

    protected int fireRate;
    
    protected boolean active;
    protected boolean reloading;

    protected Player owner;
    
    protected int reloadTime;
    protected int bulletSpeed;
    

    public Weapon(Player owner, int clipSize, int fireRate, int bulletSpeed, int reloadTime) {
        this.owner = owner;
        this.maxAmmo = clipSize;
        this.active = true;
        this.reloading = false;
        this.fireRate = fireRate;
        this.bulletSpeed = bulletSpeed;
        this.reloadTime = reloadTime;
        
        currentAmmo = maxAmmo;
    }

    public void fire(double angle){
        if (!reloading){
            if (currentAmmo > 0) {
                currentAmmo--;
                owner.getObjectHandler().addObject(new Bullet(owner, angle, bulletSpeed));
            }
        }
        
    }

    public void addBullet() {
        if (currentAmmo < maxAmmo) {
            currentAmmo++;
            reloading = true;
        } else {
            reloading = false;
        }
    }

    public void isReloading(boolean state) {
        reloading = state;
    }

    // getters
    public boolean isAbleToFire() {
        return (!reloading && active);
    }

    public int getClipSize() {
        return maxAmmo;
    }

    public int getAmmo() {
        return currentAmmo;
    }

    public int getFireRate() {
        return fireRate;
    }

    public boolean isReloading() {
        return reloading;
    }

    public void reload() {
        if (currentAmmo < maxAmmo) {
            Timer t = new Timer();
            TimerTask ReloadWeapon = new WeaponReload(this);
            t.schedule(ReloadWeapon, 0, reloadTime);
        }
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    public void setClipSize(int newClipSize) {
        this.maxAmmo = newClipSize;
    }

}
