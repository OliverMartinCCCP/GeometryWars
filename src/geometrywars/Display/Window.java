/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;


public class Window extends Canvas {

    private static final long serialVersionUID = 240840600533728354L;
    private JFrame frame;
    private Canvas canvas;

    private int width, height;
    private String title;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        createWindow();
    }

    private void createWindow() {
        // JFrame Titel
        frame = new JFrame(title);
        // JFrame Afmetingen
        frame.setPreferredSize(new Dimension(getWidth(), getHeight()));
        //frame.setMaximumSize(new Dimension(width, height));
        //frame.setMinimumSize(new Dimension(width, height));
        // JFrame Gedrag
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(getWidth(), getHeight()));
        canvas.setMaximumSize(new Dimension(getWidth(), getHeight()));
        canvas.setMinimumSize(new Dimension(getWidth(), getHeight()));
        canvas.setFocusable(false);
        frame.add(canvas);
        frame.pack();
    
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }
    
    public void setHeight(int height){
        this.height = height;
    }
    public void setWidth(int width){
        this.width = width;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
