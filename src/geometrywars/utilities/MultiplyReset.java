package geometrywars.utilities;

import geometrywars.GameObjects.Player.Player;
import java.util.TimerTask;





public class MultiplyReset extends TimerTask {

    private Player player;
    
    public MultiplyReset(Player player) {
        this.player = player;
    }
        
    @Override
    public void run() {
        player.setMultiplier(1);
    }
}
