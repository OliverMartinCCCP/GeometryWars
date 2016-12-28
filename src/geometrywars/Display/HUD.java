/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Display;

import Main.Game;
import geometrywars.GameObjects.Player.Player;
import geometrywars.gfx.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

public class HUD {

    private List<Player> players;
    private int numberOfPlayer;

    private int width, height;

    private static final Font font = new Font("Courier New", Font.CENTER_BASELINE, (int) (30 / Game.widthScaler));

    public HUD(List<Player> players) {
        this.players = players;
        numberOfPlayer = players.size();
        this.width = (int) (40 / Game.widthScaler);
        this.height = (int) (40 / Game.heightScaler);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        drawLives(g);
        drawScore(g);
        drawHighscore(g);
        drawName(g);
        drawHUD(g);
    }

    public void drawLives(Graphics g) {
        g.setColor(Color.blue);
        if (players.size() == 1) {
            for (int i = 0; i < players.get(0).getLives(); i++) {
                g.drawImage(Assets.life, (int) (30 + (i * 48) / Game.widthScaler), (int) (25 / Game.heightScaler), width, height, null);
            }
        } else {
            for (int i = 0; i < players.get(0).getLives(); i++) {
                g.drawImage(Assets.life, (int) (30 + (i * 48) / Game.widthScaler), (int) (25 / Game.heightScaler), width, height, null);
            }
            for (int i = 0; i < players.get(1).getLives(); i++) {
                g.drawImage(Assets.life, (int) (1850 - (i * 48) / Game.widthScaler), (int) (25 / Game.heightScaler), width, height, null);
            }
        }

    }

    public void drawScore(Graphics g) {
        g.setFont(font);
        g.setColor(Color.white);
        if (players.size() == 1) {
            g.drawString("Score: " + players.get(0).getScore(), (int) (850 / Game.widthScaler), (int) (40 / Game.heightScaler));
        } else {
            g.drawString("Score: " + (players.get(0).getScore() + players.get(1).getScore()), (int) (850 / Game.widthScaler), (int) (40 / Game.heightScaler));
        }

    }

    public void drawHighscore(Graphics g) {
        g.setFont(font);
        g.setColor(Color.white);
        if (players.size() == 1) {
            g.drawString("Highcore: " + players.get(0).getHighscore(), (int) (850 / Game.widthScaler), (int) (80 / Game.heightScaler));
        }

    }

    public void drawName(Graphics g) {
        g.setFont(font);
        g.setColor(Color.white);
        if (players.size() == 1) {
            g.drawString("Player: " + players.get(0).getName(), (int) (1500 / Game.widthScaler), (int) (40 / Game.heightScaler));
        } else {
            g.drawString("Player: " + players.get(0).getName(), (int) (30 / Game.widthScaler), (int) (100 / Game.heightScaler));
            g.drawString("Player: " + players.get(1).getName(), (int) (1635 / Game.widthScaler), (int) (100 / Game.heightScaler));
        }

    }

    public void drawHUD(Graphics g) {
        if (players.size() == 1) {
            g.setColor(Color.blue);
            g.drawImage(Assets.hud, (int) (35 / Game.widthScaler), (int) (980 / Game.heightScaler), (int) (32 / Game.widthScaler), (int) (32 / Game.heightScaler), null);
            g.setColor(Color.white);
            g.drawString(players.get(0).getWeapon().getAmmo() + "/" + players.get(0).getWeapon().getClipSize(), (int) (75 / Game.widthScaler), (int) (1004 / Game.heightScaler));

            g.setColor(Color.white);
            g.drawString("" + players.get(0).getCoins(), (int) (60 / Game.widthScaler), (int) (110 / Game.heightScaler));
            g.setColor(Color.red);
            g.drawImage(Assets.geom, (int) (30 / Game.widthScaler), (int) (80 / Game.heightScaler), (int) (20 / Game.widthScaler), (int) (40 / Game.heightScaler), null);

            //reload tonen
            if (players.get(0).getWeapon().isReloading()) {
                g.setColor(Color.white);
                g.drawString("Reloading...", 35, 975);
            } else if (players.get(0).getWeapon().getAmmo() == 0) {
                g.setColor(Color.white);
                g.drawString("Reload!", 35, 975);
            }
        } else {
            g.setColor(Color.blue);
            g.drawImage(Assets.hud, (int) (35 / Game.widthScaler), (int) (980 / Game.heightScaler), (int) (32 / Game.widthScaler), (int) (32 / Game.heightScaler), null);
            g.drawImage(Assets.hud, (int) (1845 / Game.widthScaler), (int) (980 / Game.heightScaler), (int) (32 / Game.widthScaler), (int) (32 / Game.heightScaler), null);
            //show amount of ammo left
            g.setColor(Color.white);
            g.drawString(players.get(0).getWeapon().getAmmo() + "/" + players.get(0).getWeapon().getClipSize(), (int) (75 / Game.widthScaler), (int) (1004 / Game.heightScaler));
            g.drawString(players.get(1).getWeapon().getAmmo() + "/" + players.get(1).getWeapon().getClipSize(), (int) (1750 / Game.widthScaler), (int) (1004 / Game.heightScaler));

            //Show amount of coins
            g.setColor(Color.white);
            g.drawString("" + players.get(0).getCoins(), (int) (60 / Game.widthScaler), (int) (130 / Game.heightScaler));
            g.drawString("" + players.get(1).getCoins(), (int) (1860 / Game.widthScaler), (int) (130 / Game.heightScaler));

            g.setColor(Color.red);
            g.drawImage(Assets.geom, (int) (30 / Game.widthScaler), (int) (100 / Game.heightScaler), (int) (20 / Game.widthScaler), (int) (40 / Game.heightScaler), null);
            g.drawImage(Assets.geom, (int) (1830 / Game.widthScaler), (int) (100 / Game.heightScaler), (int) (20 / Game.widthScaler), (int) (40 / Game.heightScaler), null);

            //reload tonen
            if (players.get(0).getWeapon().isReloading()) {
                g.setColor(Color.white);
                g.drawString("Reloading...", 35, 975);
            } else if (players.get(0).getWeapon().getAmmo() == 0) {
                g.setColor(Color.white);
                g.drawString("Reload!", 35, 975);
            }
            if (players.get(1).getWeapon().isReloading()) {
                g.setColor(Color.white);
                g.drawString("Reloading...", 1760, 975);
            } else if (players.get(1).getWeapon().getAmmo() == 0) {
                g.setColor(Color.white);
                g.drawString("Reload!", 1760, 975);
            }
        }

    }

}
