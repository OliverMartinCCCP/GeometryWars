package geometrywars.GameObjects.Player;

import Main.Game;
import geometrywars.GameObjects.GameObject;
import geometrywars.GameObjects.Powers.Power;
import geometrywars.GameObjects.Weapons.MachineGun;
import geometrywars.GameObjects.Weapons.Pistol;
import geometrywars.GameObjects.Weapons.Shotgun;
import geometrywars.GameObjects.Weapons.SubMachineGun;
import geometrywars.GameObjects.Weapons.Weapon;
import geometrywars.GameStates.ID;
import geometrywars.GameStates.ArcadeState;
import geometrywars.gfx.Assets;
import geometrywars.sounds.Sound;
import geometrywars.utilities.Clock;
import geometrywars.utilities.GameObjectHandler;
import geometrywars.utilities.MultiplyReset;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Player extends GameObject{
    long timeNow = System.currentTimeMillis();
    private Clock clock = new Clock();

    private int id;
    private String name;
    
    
    private int velocity;
    private int currentLevel;
    private int lives;
    private int score, coins = 0;
    private double multiPlier = 1;
    private int highscore;
    private int multiplayerHighscore;
    
    private Weapon weapon;
    private boolean triggerWeapon;
    private List<Power> powerInventory;
    private Power powerSlot;
    private ArrayList<Weapon> weaponInventory;
    private int currentWeapon;
    
    private boolean immuun;    
    
    private boolean controllerSet;
    
    private TimerTask multiplyTimer = new MultiplyReset(this);
    
    
    public Player( int x, int y, int id, String name){
        super(x, y, ID.Player);
        this.name = name;
        this.id = id;
        width = (int) (45/Game.widthScaler);
        height = (int) (45/Game.heightScaler);
        
        powerInventory = new ArrayList<>();
        weaponInventory = new ArrayList<>();
        
        weaponInventory.add(new Pistol(this));        
        weaponInventory.add(new Shotgun(this));
        weaponInventory.add(new SubMachineGun(this));
        weaponInventory.add(new MachineGun(this));
        
        currentWeapon = 0;
        
        weapon = weaponInventory.get(currentWeapon);
        
        
        lives = 5;
        immuun = false;
        
        controllerSet = false;
        
        
        velocity = 4;
    }

    @Override
    public void tick() {
        if (score>highscore){
            highscore = score;
        }
        
        if (clock.timePassed() > 2000){
            immuun = false;
        }
        
        if (controllerSet){
            if (!(ArcadeState.clamp((int)x+(int)velX, 0, Game.width-40))){
                this.x += velX;
            }
            if (!(ArcadeState.clamp((int)y+(int)velY, 0, Game.height-70))){
                this.y += velY;
            } 
        } 
    }

    @Override
    public void render(Graphics g) {
        
        if (immuun){
            g.drawImage(Assets.immuun, (int)x, (int)y, width, height, null);
        }else{
            g.drawImage(Assets.santa, (int)x, (int)y, width, height, null);
        }
    }
    
    @Override
    public void hit(){
        // whenever the player gets hit he loses 1 life and is immuun for 3 seconds
        if (!immuun){
            Sound.playSound("Music_hit.wav", 5);
            lives--;
            if (lives <= 0){
                lives = 0;
                alive = false;
            }
            clock = new Clock();
            clock.start();
            immuun = true;
        }
        resetMultiplier();
    }
    
    public void usePower(){
        if (powerSlot != null){
            
            powerSlot.execute(objectHandler, this);
            
            powerInventory.remove(powerSlot);
            if (powerInventory.size() > 0){
                powerSlot = powerInventory.get(0);
            } else {
                powerSlot = null;
            }
        }
    }
    public void resetMultiplier(){
        multiPlier = 1;
    }
    public void addLife(){
        this.lives++;
    }
    public void addScore(int score){
        this.score += score*multiPlier;
    }
    
    public void addCoins(int amount){
        this.coins += amount;
    }
    public void reload(){
        weapon.reload();
    }    
    public void startMultiPlier(){
        // only start the multiplier when player is not immuun
        if (!immuun){
            // cancel the multiplier and extend the previous multiplier
            multiplyTimer.cancel();

            Timer t = new Timer();
            multiplyTimer = new MultiplyReset(this);
            
            multiPlier += 0.25;
            t.schedule(multiplyTimer, 2000, 2000);
        }
    }

    
    // Shooting & aiming
    public void fireController(float controllerX, float controllerY){
        weapon.fire(calculateAngleController(controllerX * 100, controllerY * 100));
    }  
    public double calculateAngleController(float controllerX, float controllerY){
        return Math.atan2( controllerX, controllerY) + Math.PI/2;
    }
    public void fireMouse(float mouseX, float mouseY){
        weapon.fire(calculateAngleMouse(mouseX, mouseY));
    }
    public double calculateAngleMouse(float mouseX, float mouseY){
        return Math.atan2( mouseX - x, mouseY - y) + Math.PI/2;
    }
    
    
    // Movement
    public void moveLeft(){
        if (!ArcadeState.clamp((int)x-velocity, 0, Game.width))
            this.x -= velocity;
    }
    public void moveUp(){
        if (!ArcadeState.clamp((int)y-velocity, 0, Game.height))
            this.y -= velocity;
    }
    public void moveRight(){
        if (!ArcadeState.clamp((int)x+velocity, 0, Game.width-width))
            this.x += velocity;
    }
    public void moveDown(){
        if (!ArcadeState.clamp((int)y+velocity, 0, Game.height-height-25))
            this.y += velocity;
    }
    public void move(float x, float y){
        controllerSet = true;
        this.velX = x*velocity;
        this.velY = y*velocity;
    }
    // getters
    public String getName(){
        return name;
    }
    public int getMultiplayerHighscore(){
        return multiplayerHighscore;
    }
    public int getScore(){
        return score;
    }
    public int getCoins(){
        return coins;
    }    
    public int getHighscore(){
        return highscore;
    }
    public int getLevel(){
        return currentLevel;
    }
    public int getPlayerId(){
        return id;
    }
    public int getLives(){
        return lives;
    }    
    public double getMultiPlier(){
        return multiPlier;
    }
    public Weapon getWeapon(){
        return weapon;
    }
    @Override
    public GameObjectHandler getHandler(){
        return objectHandler;
    }
    
    public boolean isReloading(){
        return weapon.isReloading();
    }
    public boolean isAbleToFire(){
        return weapon.isAbleToFire();
    }
    public boolean isImmuun(){
        return immuun;
    }
    
    // setters
    public void setScore(int newScore){
        this.score = newScore;
    }
    public void setName( String name ){
        this.name = name;
    }
    public void addPower(Power power){
        powerInventory.add(power);
        if (powerSlot == null)
            powerSlot = power;
    }
    public void setHighscore(int highscore){
        this.highscore = highscore;
    }
    public void setCurrentLevel(int level){
        currentLevel = level;
    }    
    //TODO: fix me
    public void setCredits(int credits){
        multiplayerHighscore = credits;
    }
    public void setActiveWeapon(Weapon weapon){
        this.weapon = weapon;
    }
    public void setMultiplier(double multiplier){
        this.multiPlier = multiplier;
    }
    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
    public long getTime(){
        return timeNow;
    }
    public void setTime(long time){
        this.timeNow = time;
    }
    public void addWeapon(Weapon weapon){        
        if (!(weaponInventory.contains(weapon)))
            weaponInventory.add(weapon);
    }
    
    public void previousWeapon(){
        if (currentWeapon-1 >= 0 && !weapon.isReloading()){
            currentWeapon--;
            weapon = weaponInventory.get(currentWeapon);
        }
        
    }
    public void nextWeapon(){
        if ( (currentWeapon+1) <= (weaponInventory.size()-1) && (!weapon.isReloading())) {
            currentWeapon++;
            weapon = weaponInventory.get(currentWeapon);
        }
    }
    
    public void clearPowers(){
        powerInventory.clear();
    }
    @Override
    public void setHandler(GameObjectHandler handler){
        this.objectHandler = handler;
    }
    
}
