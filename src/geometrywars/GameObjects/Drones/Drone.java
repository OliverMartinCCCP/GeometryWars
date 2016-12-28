package geometrywars.GameObjects.Drones;

import Main.Game;
import geometrywars.GameObjects.GameObject;
import geometrywars.GameObjects.Geoms.Geom;
import geometrywars.GameObjects.Player.Player;
import geometrywars.GameStates.ID;
import geometrywars.Physics.Movement.DroneMovement;
import geometrywars.Physics.Movement.Movement;
import java.awt.Graphics;
import java.util.ArrayList;





public class Drone extends GameObject{
    
    protected Movement movement;
    protected ArrayList<Geom> geoms;
    protected Player owner;

    public Drone(int x, int y, ID id, Player owner) {
        super(x, y, id);
        this.movement = new DroneMovement(this);
        this.geoms = new ArrayList<>();
        this.owner = owner;
        this.width = (int) (16/Game.widthScaler);
        this.height = (int) (16/Game.heightScaler);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        
    }
    
    public void setTarget(GameObject target){
        movement.setTarget(target);
    }
    public void updateGeomList(ArrayList<Geom> geoms){
        this.geoms = geoms;
    }
    public ArrayList<Geom> getGeomList(){
        return geoms;
    }
    
    public Player getOwner(){
        return owner;
    }
    
}
