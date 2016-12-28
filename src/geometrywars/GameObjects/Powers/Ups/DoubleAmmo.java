package geometrywars.GameObjects.Powers.Ups;

import geometrywars.GameObjects.Player.Player;
import geometrywars.utilities.DoubleAmmoTimer;
import geometrywars.utilities.GameObjectHandler;
import java.util.Timer;
import java.util.TimerTask;





public class DoubleAmmo extends PowerUp {

    public DoubleAmmo(double x, double y) {
        super(x, y);
    }

    @Override
    public void execute(GameObjectHandler objectHandler, Player source) {
        Timer t = new Timer();
        TimerTask doubleAmmo = new DoubleAmmoTimer(source.getWeapon());
        t.schedule(doubleAmmo, 0, 10000);
    }

}
