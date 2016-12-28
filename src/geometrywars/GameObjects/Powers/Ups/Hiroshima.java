package geometrywars.GameObjects.Powers.Ups;

import geometrywars.GameObjects.Enemies.Enemy;
import geometrywars.GameObjects.Player.Player;
import geometrywars.utilities.GameObjectHandler;
import java.util.ArrayList;
import java.util.Random;





public class Hiroshima extends PowerUp {

    public Hiroshima(double x, double y) {
        super(x, y);
    }

    @Override
    public void execute(GameObjectHandler objectHandler, Player source) {
        System.out.println(source.getName() + " nuked 2/3 of the enemies");
        ArrayList<Enemy> enemies = objectHandler.getEnemies();
        
        for (int i = 0; i < enemies.size(); i++){
            Random r = new Random();
            // free 2/3 of the enemies (random)
            if (r.nextInt(3) < 2){
                enemies.get(i).destroy();
                //TODO: play bomb sound
            }
        }
    }

}
