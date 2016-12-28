package geometrywars.GameObjects.Enemies;

import Main.Game;
import geometrywars.GameObjects.GameObject;
import geometrywars.GameStates.ID;
import geometrywars.Physics.Movement.BasicMovement;
import geometrywars.gfx.Assets;
import java.awt.Graphics;
import java.util.Random;



public class TriangleEnemy extends Enemy{
    
    private GameObject target;
    private int explosionId;
    
    Random r = new Random();
    int max = 5;
    int min = 1;
    int randomNumber;

    public TriangleEnemy( int x, int y, ID id) {
        super(x, y);
        this.lives = 1;
        this.velX = (int) Math.ceil(Math.random() * 3);
        this.velY = (int) Math.ceil(Math.random() * 3);
        
        this.movement = new BasicMovement(this);
    }
    @Override
    public void tick() {
        if (!frozen)
            if ((int)this.getX() <= 0-this.getWidth()/2 || (int)this.getY() <= 0-this.getHeight()/2){
                this.setVelX(getRandomNumber());
                this.setVelY(getRandomNumber());
            }
            if((int)this.getX() >= Game.width-(this.getWidth()/2)-10 || (int)this.getY() >= Game.height-(this.getHeight()/2)-25){
                this.setVelX(-1*getRandomNumber());
                this.setVelY(-1*getRandomNumber());
            }
            movement.move();
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy3, (int)x, (int)y, width,height,null);
    }

    public int getRandomNumber(){
        return r.nextInt(max - min + 1) + min;
    }
}
