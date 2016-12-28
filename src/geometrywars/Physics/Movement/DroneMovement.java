package geometrywars.Physics.Movement;

import geometrywars.GameObjects.Drones.Drone;
import geometrywars.GameObjects.GameObject;
import geometrywars.GameObjects.Geoms.Geom;
import static java.lang.Math.abs;
import java.util.ArrayList;


public class DroneMovement implements Movement {
    private Drone drone;

    boolean insideScreenX = true;
    boolean insideScreenY = true;
    
    private double velX, velY;
    private double angle;
    
    private GameObject target;
    
    private double targetX;
    private double targetY;
    private int velocity;
    
    private int distanceX,distanceY;
    
    private boolean changeX,changeY;
    
    public DroneMovement(Drone drone){
        this.drone = drone;
        this.velocity = 3;
        
    }
    
    @Override
    public void move() {
        
       ArrayList<Geom> geoms = drone.getGeomList();
        if (geoms.size() > 0){
            distanceX = 301;
            distanceY = 301;
            
            
            for (Geom g : geoms){
                //distance between drone and geom
                changeX = false;
                changeY = false;
                
                if ((int) abs(g.getX() - drone.getX()) < distanceX){
                    changeX = true;
                    distanceX = (int) abs(g.getX() - drone.getX());
                }
                
                if ((int) abs(g.getY() - drone.getY()) < distanceY){
                    changeY = true;
                    distanceY = (int) abs(g.getY() - drone.getY());
                }
                
                
                // set target to closest geom
                if ((distanceX <= 300)&&(distanceY <= 300)){
                    if (changeX || changeY){
                        targetX = g.getX();
                        targetY = g.getY();
                    }
                }else {     
                    targetX = drone.getOwner().getX();
                    targetY = drone.getOwner().getY();
                }
                // follow player ( = set target player)
            }
        }
        else {
            // follow player ( = set target player)
            targetX = drone.getOwner().getX();
            targetY = drone.getOwner().getY();
        }
        angle = Math.atan2(targetX - drone.getX(), targetY - drone.getY()) + Math.PI/2;       
        float newX = (float) (velocity * Math.cos(angle));
        float newY = (float) (velocity * Math.sin(angle));
        drone.setX(drone.getX()-newX);
        drone.setY(drone.getY()+newY);
        
    }
    
    @Override
    public void setTarget(GameObject target){
        this.target = target;
    }

    @Override
    public void setTargets(GameObject target1, GameObject target2) {
    }
}
