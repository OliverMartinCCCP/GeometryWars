package geometrywars.utilities;


import geometrywars.GameObjects.Player.Player;
import geometrywars.GameObjects.Geoms.Geom;
import geometrywars.GameObjects.Drones.KillerDrone;
import geometrywars.GameObjects.Drones.Drone;
import geometrywars.GameObjects.Enemies.CircleEnemy;
import geometrywars.GameObjects.Enemies.Enemy;
import geometrywars.Physics.Collision.Quadtree;
import geometrywars.GameObjects.Bullet;
import geometrywars.GameObjects.Enemies.TriangleEnemy;
import geometrywars.GameObjects.GameObject;
import geometrywars.GameObjects.Powers.Downs.PowerDown;
import geometrywars.GameObjects.Powers.Power;
import geometrywars.GameStates.ID;
import geometrywars.sounds.Sound;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;



public class GameObjectHandler {
    
    private final LinkedList<GameObject> gameObjects;
    private boolean waveCleared;
    
    private Quadtree quad;
    
    public GameObjectHandler( ){
        gameObjects = new LinkedList<>();
        quad = new Quadtree( 0, new Rectangle(0,0,1920,1080) );
       
    }
    
    public void tick() {
        
        for (GameObject obj : gameObjects){
            obj.tick();
            if (obj instanceof Drone){
                //TODO: Observer maken voor om objecten te updaten
                ((Drone)obj).updateGeomList(getGeoms());
            }
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < gameObjects.size(); i++){
            gameObjects.get(i).render(g);
        }
    }

    public void addObject(GameObject obj) {
        obj.setHandler(this);
        gameObjects.add(obj);
        
    }
    
    public void removeObject(GameObject obj) {
        gameObjects.remove(obj);
        
    }
    
    public ArrayList<Geom> getGeoms(){
        ArrayList<Geom> geomList = new ArrayList<>();
        for (GameObject obj : gameObjects){
            if (obj instanceof Geom)
                geomList.add((Geom)obj);
        }
        return geomList;
    }
    
    public boolean processWave(){
        waveCleared = true;
        for (GameObject obj : gameObjects){
            if (obj instanceof Enemy){
                if (obj.isAlive()){
                    waveCleared = false;
                }
            }
        }
        return waveCleared;
    }
    public boolean processEnemies(){
        boolean cleared = true;
        for (GameObject obj : gameObjects){
            if (obj instanceof Enemy){
                if (obj.isAlive()){
                    cleared = false;
                }
            }
        }
        return cleared;
    }
    
    public void processCollision(){
        
        quad.clear();
        
        for (GameObject obj : gameObjects){
            quad.insert(obj);
        }
        
        List<GameObject> returnObjects = new ArrayList<>();
        for (int i = 0; i < gameObjects.size();i++){
            GameObject source = gameObjects.get(i);
            
            returnObjects.clear();
            quad.retrieve(returnObjects, gameObjects.get(i));
            
            
            // check for collision between objects ( from the same quadtree node )
            for (int y = 0; y < returnObjects.size(); y++){
                GameObject target = returnObjects.get(y);
                
                // COLLISION DETECTED
                if ( target != source && source.getBounds().intersects(target.getBounds()) ){

                      ////////////////////////////////////////////////////////////////
                     ///WARNING/////////!! H A R D   C O D E  D !!//////////WARNING//
                    ////////////////////////////////////////////////////////////////
                    
                    // Collision with player
                    if (source instanceof Player){
                        if (target instanceof Enemy){
                            if (!((Player) source).isImmuun()){
                                // Destroy the enemy
                                target.destroy();
                                // hit the player
                                ((Player)source).hit();
                                if (target instanceof TriangleEnemy){
                                    ((Player)source).setVelocity(1);
                                    ((Player)source).setTime(System.currentTimeMillis());
                                }
                            }
                        }
                        
                        
                        if (target instanceof Geom){
                            ((Player)source).addCoins(50);
                            target.destroy();
                        }
                        if (target.getId() == ID.Power){
                            if (target instanceof PowerDown)
                                ((Power)target).execute(this, (Player)source);
                            else
                                ((Player)source).addPower((Power)target);
                            target.destroy();
                        }
                        
                    }
                    // Collision with Enemy
                    if (source instanceof Enemy){
                        if (target instanceof Player){
                            if (!((Player)target).isImmuun()){
                                source.destroy();
                                System.out.println("Destroying "+source);
                                ((Player)target).hit();
                            }
                        }
                    }
                    // Collision with Drone
                    if (source instanceof KillerDrone){
                        if (target instanceof CircleEnemy){
                            ((KillerDrone)source).getOwner().addScore(15);
                            
                            target.destroy();
                            
                        }
                        if (target instanceof Geom){
                            ((Drone)source).getOwner().addCoins(25);
                            target.destroy();
                        }
                    }
                    // Collision with Bullet
                    if (source instanceof Bullet){
                        if (target instanceof Enemy){
                            Bullet bullet = (Bullet)source;
                            Enemy enemy = (Enemy)target;
                            
                            enemy.hit();
                            bullet.getOwner().addScore(15);
                            
                            bullet.getOwner().startMultiPlier();
                            // multiplier active for 5 seconds, then reset
                            removeObject(source);
                        }
                    }
                    
                }
                
            }
            
            
            ///delete the enemy at the end if its not isAlive
            if (!(source instanceof Player)){
                if (!source.isAlive()){
                    
                    // randomly drop powerup/down when the source is Enemy
                    if (source instanceof Enemy){
                        int posX = (int)source.getX();
                        int posY = (int)source.getY();
                        Random r = new Random();
                        Sound.playSound("Music_pop.wav", -5);
                        // drop power up / down
                        
                        
                    }
                    //delete from objectHandler
                    removeObject(source);
                }
            }
        }
    }
    public int getSize(){
        return  gameObjects.size();
    }
    public ArrayList<Enemy> getEnemies(){
        ArrayList<Enemy> tmp = new ArrayList<>();
        for (GameObject obj : gameObjects){
            if (obj instanceof Enemy)
                tmp.add((Enemy)obj);
        }
        return tmp;
    }
}
