/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.GameObjects;

import Main.Game;
import geometrywars.GameStates.ID;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author clydez
 */
public class MenuButton extends GameObject {
    private int width;
    private int height;
    private boolean selected;
    private String label;

    private Color color;

    private BufferedImage image;
    private Font font;

    private boolean selectable;

    public MenuButton(int x, int y, int width, int height, ID id, String label,BufferedImage image) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        this.selected = false;
        this.label = label;
        this.selectable = true;
        this.image = image;
        font = new Font("Curlz MT", Font.BOLD, (int) (50/Game.widthScaler));

    }

    public void toggleSelection() {
        selected = !selected;
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 250, 50);
    }

    @Override
    public void tick() {
        if (selectable) {
            if (selected) {
                color = Color.red;
            } else {
                color = new Color(164,0,0);
            }
        }else{
            color = new Color(164,0,0);
        }

    }

    @Override
    public void render(Graphics g) {
        g.setFont(font);
        g.setColor(color);
        if(selected){
            g.drawImage(image, (int) x, (int) y, width, height, null);
        }      
        g.drawString(label, (int) x + width, (int) y + 80);
    }

    @Override
    public String toString() {
        return label;
    }

    public void setUnSelectable() {
        this.selectable = false;
    }
    
    public boolean getSelectable(){
        return selectable;
    }


}
