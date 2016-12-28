package geometrywars.Physics.Movement;

import Main.Game;
import geometrywars.GameObjects.GameObject;
import geometrywars.GameStates.ArcadeState;


public class BasicMovement implements Movement {
    private GameObject object;

    boolean insideScreenX = true;
    boolean insideScreenY = true;
    
    private double velX, velY;
    private double angle;
    
    private GameObject target;
    
    private double targetX;
    private double targetY;
    
    public BasicMovement(GameObject object){
        this.object = object;
        
    }
    
    @Override
    public void move() {        
        velX = object.getVelX();
        velY = object.getVelY();
        
        int x = (int) object.getX();
        int y = (int) object.getY();
        
        object.setX(object.getX()+object.getVelX());
        object.setY(object.getY()+object.getVelY());
        
        
        // borders
        if (ArcadeState.clamp((int)object.getX(), 0-object.getWidth()/2, Game.width-(object.getWidth()/2)-10)){
            object.setVelX(object.getVelX()*(-1));
        }
        if (ArcadeState.clamp((int)object.getY(), 0-object.getHeight()/2, Game.height-(object.getHeight()/2)-25)){
            object.setVelY(object.getVelY()*(-1));
        }      
    }
    @Override
    public void setTarget(GameObject target){
        this.target = target;
    }

    @Override
    public void setTargets(GameObject target1, GameObject target2) {
    }
    
}
