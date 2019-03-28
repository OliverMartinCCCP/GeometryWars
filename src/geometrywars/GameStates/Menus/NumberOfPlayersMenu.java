/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.GameStates.Menus;

import Main.Game;
import geometrywars.GameObjects.MenuButton;
import geometrywars.GameStates.GameStateManager;
import geometrywars.GameStates.ID;
import geometrywars.GameStates.ArcadeState;
import geometrywars.GameStates.State;
import geometrywars.gfx.Assets;
import geometrywars.gfx.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controllers;

/**
 *
 * @author Oliver
 */
public class NumberOfPlayersMenu extends State {

    private State playMenu;
    private int currentSelection;
    
    public NumberOfPlayersMenu(PlayMenu playMenu, GameStateManager gsm) {
        setFont(new Font("Curlz MT", Font.CENTER_BASELINE, (int) (250 / Game.widthScaler)));
        this.keys = new boolean[256];

        this.playMenu = playMenu;
        this.gsm = gsm;
        
        this.menuButtonText = new String[]{"Single player", "Multi player", "back"};
        this.menuButtons = new MenuButton[menuButtonText.length];
        createMenuButtons();
        createController();
        
        createSnowFlakes();
    }

    @Override
    public void init() {
        currentSelection = 0;
    }

    @Override
    public void tick() {
        for (Background snowFlake : snowFlakes) {
            snowFlake.tick();
        }
        amountOfTicks++;

        for (MenuButton button : menuButtons) {
            button.tick();
        }

        if (amountOfTicks >= 5) {
            amountOfTicks = 0;
            processInput();
        }
    }

    @Override
    public void render(Graphics g) {
        background.render(g);
        for (Background snowFlake : snowFlakes) {
            snowFlake.render(g);
        }
        g.setFont(font);
        g.setColor(Color.red);
        g.drawString("Geometrywars", (int) (200 / Game.widthScaler), (int) (250 / Game.heightScaler));
        System.out.println("numberOfPlayers" +Game.widthScaler);
        for (MenuButton menuButton : menuButtons) {
            menuButton.render(g);
        }
    }

    @Override
    public void createMenuButtons() {
        for (int i = 0; i < menuButtons.length; i++) {
            menuButtons[i] = new MenuButton((int) (550/Game.widthScaler), (int) ((400 + (i * 125))/Game.heightScaler), (int) (400/Game.widthScaler), (int) (100/Game.heightScaler), ID.MenuButton, menuButtonText[i], Assets.sleigh);
        }
        menuButtons[0].toggleSelection();
    }

    private void createController() {
        // Controller
        this.controllerSet = false;

        try {
            Controllers.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        Controllers.poll();

        int amount = 0;

        while (!controllerSet && amount < Controllers.getControllerCount()) {
            if (Controllers.getController(amount).getName().equals("Wireless Controller")) {
                controller = Controllers.getController(amount);
                for (int i = 0; i < controller.getAxisCount(); i++) {
                    System.out.println(i + ": " + controller.getAxisName(i));
                    controller.setDeadZone(i, (float) 0.3);
                }
                controllerSet = true;
            }
            amount++;
        }
    }

    private void execute() {
        if (currentSelection == 0) {
            gsm.setState(new ArcadeState(gsm, 1));
        }
        if (currentSelection == 1) {
            gsm.setState(new ArcadeState(gsm, 2));
        }
        if (currentSelection == 2) {
            gsm.setState(playMenu);
        }
    }

    private void processInput() {
        //CONTROLLER
        if (controllerSet) {
            controller.poll();

            if (controller.isButtonPressed(1)) {
                execute();
            }

            if (controller.getPovY() == -1) {
                menuButtons[currentSelection].toggleSelection();
                if (currentSelection - 1 < 0) {

                    currentSelection = 0;

                } else {
                    currentSelection--;

                }
                menuButtons[currentSelection].toggleSelection();
            }

            if (controller.getPovY() == 1) {
                menuButtons[currentSelection].toggleSelection();
                if (currentSelection + 1 > menuButtons.length - 1) {

                    currentSelection = menuButtons.length - 1;

                } else {
                    currentSelection++;
                }
                menuButtons[currentSelection].toggleSelection();
            }
        }

        //KP_UP
        menuButtons[currentSelection].toggleSelection();

        if (keys[90]) {
            if (currentSelection - 1 < 0) {
                currentSelection = 0;
            } else {
                currentSelection--;
            }
        }
        //KP_DOWN
        if (keys[83]) {
            if (currentSelection < menuButtons.length-1){
                currentSelection = (currentSelection + 1);
            }
        }
        //KP_ENTER
        //check which button is selected and execute
        if (keys[10]) {
            execute();
            System.out.println("execute");
        }

        menuButtons[currentSelection].toggleSelection();

    }

    @Override
    public void keyPressed(int k) {
        keys[k] = true;
    }

    @Override
    public void keyReleased(int k) {
        keys[k] = false;
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

}
