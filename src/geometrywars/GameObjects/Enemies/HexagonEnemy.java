package geometrywars.GameObjects.Enemies;

import geometrywars.GameObjects.GameObject;
import geometrywars.GameStates.ID;
import geometrywars.Physics.Movement.BasicMovement;
import geometrywars.gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;



public class HexagonEnemy extends Enemy{
    
    private GameObject target;

    public HexagonEnemy( int x, int y, ID id) {
        super(x, y);
        this.lives = 1;
        this.velX = (int) Math.ceil(Math.random() * 3);
        this.velY = (int) Math.ceil(Math.random() * 3);
        
        this.movement = new BasicMovement(this);
    }
    @Override
    public void tick() {
        if (!frozen)
            movement.move();
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.drawImage(Assets.enemy1, (int)x, (int)y, width,height,null);
    }

    
}
