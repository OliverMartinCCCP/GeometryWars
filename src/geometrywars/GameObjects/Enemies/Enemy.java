package geometrywars.GameObjects.Enemies;

import geometrywars.GameObjects.GameObject;
import geometrywars.GameObjects.Moveable;
import geometrywars.GameStates.ID;
import geometrywars.Physics.Movement.Movement;
import geometrywars.utilities.UnfreezeEnemy;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;





public abstract class Enemy extends GameObject implements Moveable{

    protected Movement movement;
    protected boolean frozen;
    
    public Enemy(int x, int y) {
        super(x, y, ID.Enemy);
        this.frozen = false;
    }
    
    @Override
    public abstract void tick();
    @Override
    public abstract void render(Graphics g);
    @Override
    public void setTarget(GameObject target){
    }
    public void setTargets(GameObject target, GameObject target2){
    }
    @Override
    public double getVelX(){
        return velX;
    }
    @Override
    public double getVelY(){
        return velY;
    }
    
    public void freeze(){
        frozen = true;
        Timer t = new Timer();
        TimerTask unfreeze = new UnfreezeEnemy(this);
        t.schedule(unfreeze, 3000,3000);
    }
    public void unfreeze(){
        frozen = false;
    }
}
