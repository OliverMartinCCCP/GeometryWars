/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.GameStates;

import geometrywars.Display.Window;
import geometrywars.GameStates.Menus.MainMenu;
import java.awt.event.MouseEvent;

public class GameStateManager {

    private State currentState;
    private Window window;

    public GameStateManager() {
        currentState = new MainMenu(this);
        
    }

    public void setState(State state) {
        currentState = state;
        currentState.init();
    }

    //deze methods worden uitgevoerd in de state zoals het hoort
    public void tick() {
        currentState.tick();
    }

    public void render(java.awt.Graphics g) {
        currentState.render(g);
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public void keyPressed(int k) {
        currentState.keyPressed(k);
    }

    public void keyReleased(int k) {
        currentState.keyReleased(k);
    }

    public void mouseDragged(MouseEvent e) {
        currentState.mouseDragged(e);
    }

    public void mouseMoved(MouseEvent e) {
        currentState.mouseMoved(e);
    }

    public void mousePressed(MouseEvent e) {
        currentState.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        currentState.mouseReleased(e);
    }

}
