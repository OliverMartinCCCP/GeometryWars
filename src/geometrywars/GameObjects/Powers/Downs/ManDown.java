package geometrywars.GameObjects.Powers.Downs;

import geometrywars.GameObjects.Player.Player;
import geometrywars.utilities.GameObjectHandler;





public class ManDown extends PowerDown{

    public ManDown(double x, double y) {
        super(x, y);
    }


    @Override
    public void execute(GameObjectHandler objectHandler, Player source) {
        System.out.println(source.getName() + ": Man Down! I lost 1 life");
        source.hit();
        // TODO:
        // play "got hit" sound 
    }

}
