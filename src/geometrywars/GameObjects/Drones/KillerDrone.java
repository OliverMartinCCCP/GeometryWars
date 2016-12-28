package geometrywars.GameObjects.Drones;

import geometrywars.GameObjects.Player.Player;
import geometrywars.GameStates.ID;
import geometrywars.gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;





public class KillerDrone extends Drone {

    
    private Player owner;
    
    public KillerDrone(int x, int y, ID id, Player owner) {
        super(x, y, id, owner);
    }

    @Override
    public void tick() {
        movement.move();
        
        
        
    }

    @Override
    public void render(Graphics g) {
        /*
        g.setColor(Color.white);
        g.drawOval((int)x, (int)y, width, height);
        g.fillOval((int)x, (int)y, width, height);
        */
        g.setColor(Color.blue);
        g.drawImage(Assets.fairy, (int)x, (int)y, width,height,null);
    }
    
}
