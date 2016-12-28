package geometrywars.GameObjects.Geoms;

import Main.Game;
import geometrywars.GameObjects.GameObject;
import geometrywars.GameStates.ID;
import geometrywars.gfx.Assets;
import java.awt.Graphics;





public class Geom extends GameObject {

    private int width,height;
    
    public Geom(int x, int y) {
        super(x, y, ID.Geom);
        width = (int) (35/Game.widthScaler);
        height = (int) (72/Game.heightScaler);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        
        g.drawImage(Assets.geom, (int)x, (int)y, width,height,null);
    }

}
