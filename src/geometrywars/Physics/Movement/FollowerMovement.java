package geometrywars.Physics.Movement;

import geometrywars.GameObjects.GameObject;
import static java.lang.Math.abs;
import java.util.Random;


public class FollowerMovement implements Movement {
    private GameObject object;

    boolean insideScreenX = true;
    boolean insideScreenY = true;
    
    private double velX, velY;
    private double angle;
    
    private GameObject target;
    private GameObject target1;
    private GameObject target2;
    private double distance1;
    private double distance2;
    
    private double targetX;
    private double targetY;
    
    public FollowerMovement(GameObject object){
        this.object = object;
        
    }
    
    @Override
    public void move() {/*
        object.setX( object.getX() + cos(angle));
        object.setY( object.getY() + cos(angle));
        */

        Random r = new Random();
        
        if (target2 != null){
            distance1 = abs(object.getX() - target1.getX()) + abs(object.getY() - target1.getY());
            distance2 = abs(object.getX() - target2.getX()) + abs(object.getY() - target2.getY());
            if (distance1 < distance2){
                target = target1;
            }else{
                target = target2;
            }
        }
 
        targetX = target.getX();
        targetY = target.getY();
        
        
        
        angle = Math.atan2(targetX - object.getX(), targetY - object.getY()) + Math.PI/2;       
        float newX = (float) (2 * Math.cos(angle));
        float newY = (float) (2 * Math.sin(angle));
        object.setX(object.getX()-newX);
        object.setY(object.getY()+newY);
        
    }
    @Override
    public void setTarget(GameObject target){
        this.target = target;
    }

    @Override
    public void setTargets(GameObject target1, GameObject target2){
        this.target1 = target1;
        this.target2 = target2;
    }
    
}
