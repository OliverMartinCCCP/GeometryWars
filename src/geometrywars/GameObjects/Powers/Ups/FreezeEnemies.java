package geometrywars.GameObjects.Powers.Ups;

import geometrywars.GameObjects.Enemies.Enemy;
import geometrywars.GameObjects.Player.Player;
import geometrywars.utilities.GameObjectHandler;
import java.util.ArrayList;





public class FreezeEnemies extends PowerUp{

    public FreezeEnemies(double x, double y) {
        super(x, y);
    }

    @Override
    public void execute(GameObjectHandler objectHandler, Player source) {
        System.out.println(source.getName() + " froze 2/3 of the enemies");
        ArrayList<Enemy> enemies = objectHandler.getEnemies();
        
        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).freeze();
        }
    }

}
