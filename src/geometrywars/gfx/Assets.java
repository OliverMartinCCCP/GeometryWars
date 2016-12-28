/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrywars.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Olivier
 */
public class Assets {

    private static final int width = 80;
    private static final int height = 80;
    
    public static BufferedImage hud, life, hero, santa, dollar, coins, immuun, 
            fairy, enemy1, enemy2, enemy3, geom, snowball, bg, bg2, boss,
            house, house2, sleigh, image, present;
    
    public static void init() {
        // aanmaak spritesheets
        
        SpriteSheet hudSheet = new SpriteSheet(ImageLoader.loadImage("/textures/HUD.png"));
        SpriteSheet lifeSprite = new SpriteSheet(ImageLoader.loadImage("/textures/lifeSprite.png"));
        SpriteSheet immuunSprite = new SpriteSheet(ImageLoader.loadImage("/textures/immuun.png"));
        SpriteSheet dollarSprite = new SpriteSheet(ImageLoader.loadImage("/textures/dollar.png"));
        SpriteSheet coinSprite = new SpriteSheet(ImageLoader.loadImage("/textures/coins.png"));
        SpriteSheet xmasSheet = new SpriteSheet(ImageLoader.loadImage("/textures/xmasSheet.png"));
        SpriteSheet presentSprite = new SpriteSheet(ImageLoader.loadImage("/textures/present.png"));
        SpriteSheet bossSheet= new SpriteSheet(ImageLoader.loadImage("/textures/boss.png"));
        SpriteSheet houseSheet = new SpriteSheet(ImageLoader.loadImage("/textures/christmassHouse3.png"));
        SpriteSheet houseSheet2 = new SpriteSheet(ImageLoader.loadImage("/textures/christmassHouse3.1.png"));
        SpriteSheet santaSleighSheet = new SpriteSheet(ImageLoader.loadImage("/textures/santaSleigh.jpg"));
        
        
        
        hud = hudSheet.getSprite(0, 0, width, height);
        santa = xmasSheet.getSprite(0, 0, 70, 140);
        fairy = xmasSheet.getSprite(210, 70, 70, 70);
        enemy2 = xmasSheet.getSprite(70, 70, 70, 70);
        enemy1 = xmasSheet.getSprite(140, 0, 70, 70);
        enemy3 = xmasSheet.getSprite(210, 0, 70, 70);
        geom = xmasSheet.getSprite(280, 0, 70, 140);
        snowball = xmasSheet.getSprite(140, 70, 70, 70);
        life = santa;
        dollar = dollarSprite.getSprite(0, 0, 80, 154);
        immuun = immuunSprite.getSprite(0, 0, 70, 140);
        boss = bossSheet.getSprite(0, 0, 319, 919);
        house = houseSheet.getSprite(0, 0, 386, 459);
        house2 = houseSheet2.getSprite(0, 0, 386, 459);
        sleigh = santaSleighSheet.getSprite(0, 0, 650, 314);
        
        present = presentSprite.getSprite(0, 0, 297, 287);
    }

}
