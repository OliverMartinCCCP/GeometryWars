package geometrywars.GameObjects.Powers.Ups;

import geometrywars.GameObjects.GameObject;
import geometrywars.GameObjects.Powers.Power;
import geometrywars.GameStates.ID;
import geometrywars.gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;





public abstract class PowerUp extends GameObject implements Power{
    
    public PowerUp(double x, double y) {
        super(x, y, ID.Power);
    }
    @Override
    public void tick(){}
    @Override
    public void render(Graphics g){
        if (alive){
            g.drawImage(Assets.present, (int)x, (int)y, width,height,null);
            g.setColor(Color.red);
            g.drawRect(500, 500, 32, 32);
        }
        
    }

}
