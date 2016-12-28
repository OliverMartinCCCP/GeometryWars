package geometrywars.GameObjects.Enemies;

import geometrywars.GameObjects.GameObject;
import geometrywars.GameStates.ID;
import geometrywars.Physics.Movement.FollowerMovement;
import geometrywars.gfx.Assets;
import java.awt.Graphics;



public class CircleEnemy extends Enemy{
    
    private GameObject target;


    public CircleEnemy( int x, int y, ID id) {
        super(x, y);
        this.lives = 4;
           
        this.movement = new FollowerMovement(this);
        this.velX = 2;
        this.velY = 2;
    }
    @Override
    public void tick() {
        if (!frozen)
            movement.move();
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy2, (int)x, (int)y, lives*10+20, lives*10+20, null);
    }
    @Override
    public void setTarget(GameObject target){
        movement.setTarget(target);
    }
    
    public void setTargets(GameObject target, GameObject target2){
        movement.setTargets(target, target2);
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
