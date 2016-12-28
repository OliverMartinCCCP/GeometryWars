package geometrywars.GameObjects;

import Main.Game;
import geometrywars.GameStates.ID;
import geometrywars.utilities.GameObjectHandler;
import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class GameObject {
   
    protected ID id;
    protected double x, y, velX, velY;
    
    protected int width, height;
    protected int lives = 1;
    protected boolean alive;
    
    protected GameObjectHandler objectHandler;
    
    public GameObject( double x, double y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
        
        velX = 0;
        velY = 0;
        
        width = (int) (32/Game.widthScaler);
        height = (int) (32/Game.heightScaler);
        
        alive = true;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    
    public void bounce(double power){
        this.velX += power;
        this.velY += power;
    }
    public void hit(){
        lives--;
        if (lives <= 0){
            destroy();
        }
    }
    public void destroy(){
        alive = false;
    }
    
    // Setters
    public void setX( double x){
        this.x = x;
    }
    public void setY( double y){
        this.y = y;
    }
    public void setVelX(double velX){
        this.velX = velX;
    }
    public void setVelY(double velY){
        this.velY = velY;
    }
    public void setHandler(GameObjectHandler objectHandler){
        this.objectHandler = objectHandler;
    }
    public GameObjectHandler getObjectHandler(){
        return objectHandler;
    }
    // Getters
    public ID getId(){
        return id;
    }    
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }    
    
    public double getVelX(){
        return velX;
    }
    public double getVelY(){
        return velY;
    }
    public boolean isAlive(){
        return alive;
    }
    public Rectangle getBounds(){
        return new Rectangle( (int)x, (int)y, width, height );
    }
    public GameObjectHandler getHandler(){
        return objectHandler;
    }
    
    
}
