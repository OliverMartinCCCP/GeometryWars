package geometrywars.utilities;

import geometrywars.GameObjects.Enemies.Enemy;
import java.util.TimerTask;





public class UnfreezeEnemy extends TimerTask{

    private Enemy enemy;
    public UnfreezeEnemy(Enemy enemy){
        this.enemy = enemy;
    }
    
    @Override
    public void run() {
        enemy.unfreeze();
    }

}
