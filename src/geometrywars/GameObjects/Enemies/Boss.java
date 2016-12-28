/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.GameObjects.Enemies;

import geometrywars.GameObjects.GameObject;
import geometrywars.GameObjects.Player.Player;
import geometrywars.GameStates.CampaignState;
import geometrywars.GameStates.ID;
import geometrywars.gfx.Assets;
import geometrywars.utilities.Clock;
import java.awt.Graphics;

/**
 *
 * @author oliver
 */
public class Boss extends Enemy{

    private GameObject target;
    private Player player;
    private CampaignState state;
    private Clock clock;


    public Boss( int x, int y, CampaignState state, ID id) {
        super(x, y);
        this.lives = 1000;
        this.width = 400;
        this.height = 800;
        this.state = state;
        this.player = state.getPlayer();
    }
    
    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.boss, (int)x, (int)y, width, height,null);
    }

    public void fire(){
        Enemy enemy = new CircleEnemy(((int)x + 150), ((int)y + 140), ID.Enemy);
        enemy.setTarget(player);
        state.getHandler().addObject(enemy);
        enemy = new CircleEnemy(((int)x + 150), ((int)y + 470), ID.Enemy);
        enemy.setTarget(player);
        state.getHandler().addObject(enemy);
    }
}
