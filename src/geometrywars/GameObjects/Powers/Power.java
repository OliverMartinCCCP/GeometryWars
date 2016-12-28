package geometrywars.GameObjects.Powers;

import geometrywars.GameObjects.Player.Player;
import geometrywars.utilities.GameObjectHandler;





public interface Power {

    public void execute(GameObjectHandler objectHandler, Player source);
    
    
}
