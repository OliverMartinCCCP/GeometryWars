/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.GameObjects;

import Main.Game;
import geometrywars.Data.Highscore;
import geometrywars.GameStates.ID;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author olivi
 */
public class Table extends GameObject {

    private int width;
    private int height;
    private List<Highscore> highscores;

    public Table(int x, int y, ID id, List<Highscore> highscores) {
        super(x, y, id);
        width = 500;
        this.highscores = highscores;
        height = highscores.size() * 50;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Curlz MT", Font.BOLD, (int) (50/Game.widthScaler)));
        for(int i = 0; i<highscores.size();i++){
            g.drawString(highscores.get(i).getUsername(), (int) (650/Game.widthScaler), (int) ((500+(i*50))/Game.heightScaler));
            g.drawString(String.valueOf(highscores.get(i).getHighscore()), (int) (950/Game.widthScaler), (int) ((500+(i*50))/Game.heightScaler));
            
        }

    }

}
