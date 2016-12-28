package geometrywars.GameObjects;

import geometrywars.GameObjects.Player.Player;
import Main.Game;
import geometrywars.GameStates.ID;
import geometrywars.gfx.Assets;
import geometrywars.sounds.Sound;
import java.awt.Graphics;



public class Bullet extends GameObject {

    private Player owner;
    private double angle;
    private int velocity;
    
    public Bullet( Player owner, double angle, int velocity ) {
        super( (owner.getX()+owner.getWidth()/2), (owner.getY()+owner.getHeight()/2), ID.Bullet );
        this.owner = owner;
        this.angle = angle;
        this.velocity = velocity;
        this.width = (int) (32/Game.widthScaler);
        this.height = (int) (32/Game.heightScaler);
        Sound.playSound("Music_snowBallEffect.wav",-5);
    }

    @Override
    public void tick() {
        // Angle & velocity is already set when intiantiated
        // TODO: move towards angle
        float newX = (float) (velocity * Math.cos(angle));
        float newY = (float) (velocity * Math.sin(angle));
        
        this.x -= newX;
        this.y += newY;
        // TODO: if bullet out of bounds => call destroy();
        if (x < 0 || x > Game.width || y < 0 || y > Game.height)
            destroy();  
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.snowball, (int)x, (int)y, width, height, null);
    }
    public Player getOwner(){
        return owner;
    }

}
