package geometrywars.GameObjects.Powers.Downs;

import geometrywars.GameObjects.Player.Player;
import geometrywars.gfx.Assets;
import geometrywars.utilities.GameObjectHandler;
import java.awt.Color;
import java.awt.Graphics;





public class ClearPowers extends PowerDown{
    
    public ClearPowers(double x, double y){
        super(x, y);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.present, (int)x, (int)y, width,height,null);
        g.setColor(Color.red);
        g.drawRect(500, 500, 32, 32);
    }

    @Override
    public void execute(GameObjectHandler handler, Player source) {
        System.out.println("All powers removed from player " + source.getName());
        source.clearPowers();
        // TODO:
        // player a sound
    }   
}
