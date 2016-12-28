/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.gfx;

import geometrywars.Display.Window;
import geometrywars.GameStates.GameStateManager;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Olivier
 */
public class Background {

    private BufferedImage background;
    private GameStateManager gsm;
    private Window window;
    private int width, height, xPos, yPos;
    private boolean playStateMode;

    public Background(BufferedImage background, int width, int height, int xPos, int yPos) {
        this.background = background;
        this.width = width;
        this.height = height;
        this.xPos = xPos;
        this.yPos = yPos;
        this.playStateMode = false;
    }

    public void tick() {
        if (playStateMode) {
            if (yPos > 1080) {
                yPos = -60;
            }
            yPos++;
        } 
    }

    public void render(Graphics g) {
        g.drawImage(background, xPos, yPos, width, height, null);
        
    }

    public void setMode(boolean mode) {
        playStateMode = true;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }

}
