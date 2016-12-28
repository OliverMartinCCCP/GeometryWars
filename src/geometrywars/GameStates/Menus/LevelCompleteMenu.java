/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.GameStates.Menus;

import Main.Game;
import geometrywars.GameStates.Menus.MainMenu;
import geometrywars.Data.GeometrywarsDA;
import geometrywars.GameObjects.MenuButton;
import geometrywars.GameObjects.Player.Player;
import geometrywars.GameStates.CampaignState;
import geometrywars.GameStates.GameStateManager;
import geometrywars.GameStates.ID;
import geometrywars.GameStates.State;
import geometrywars.gfx.Assets;
import geometrywars.gfx.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import org.lwjgl.input.Controller;

/**
 *
 * @author oliver
 */
public class LevelCompleteMenu extends State {

    private static int currentSelection;
    private static int amountOfItems;
    private Player player1;
    private Player player2;

    private int selectedLevel;

    private Controller controller;
    private boolean controllerSet;

    public LevelCompleteMenu(GameStateManager gsm, int selectedLevel) {
        setFont(new Font("Curlz MT", Font.CENTER_BASELINE, (int) (200 / Game.widthScaler)));
        this.gsm = gsm;
        this.selectedLevel = selectedLevel;
        this.keys = new boolean[256];
        this.controllerSet = false;
        this.menuButtonText = new String[]{"Next level", "Main menu", "Exit"};
        this.menuButtons = new MenuButton[menuButtonText.length];
        this.amountOfTicks = 0;
        createMenuButtons();
        createSnowFlakes();
    }

    @Override
    public void init() {
        currentSelection = 0;
        amountOfItems = menuButtons.length;
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

    public void setController(Controller controller) {
        this.controller = controller;
        if (controller != null) {
            this.controllerSet = true;
        }
    }

    public void setPlayers(Player player1) {
        this.player1 = player1;
        if (player1.getScore() >= player1.getHighscore()) {
            GeometrywarsDA.getInstance().updateHighscore(this.player1.getPlayerId(), this.player1.getScore());
        }
    }

    @Override
    public void createMenuButtons() {
        for (int i = 0; i < menuButtons.length; i++) {
            menuButtons[i] = new MenuButton((int) (550 / Game.widthScaler), (int) ((500 + (i * 125)) / Game.heightScaler), (int) (400 / Game.widthScaler), (int) (100 / Game.heightScaler), ID.MenuButton, menuButtonText[i], Assets.sleigh);
        }
        menuButtons[0].toggleSelection();
    }

    public void drawScore(Graphics g) {
        g.setFont(font);
        g.setColor(Color.red);
        g.drawString("Level completed", (int) (200 / Game.widthScaler), (int) (360 / Game.heightScaler));
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
            currentSelection = (currentSelection + 1);
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

    public void execute() {
        if (currentSelection == 0) {
            gsm.setState(new CampaignState(gsm, controller, (selectedLevel + 1)));
        }

        if (currentSelection == 1) {
            gsm.setState(new MainMenu(gsm));
        }

        if (currentSelection == 2) {
            System.exit(1);
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
        g.drawString("Level Completed", (int) (Game.width / Game.widthScaler), (int) (250 / Game.heightScaler));

        for (MenuButton button : menuButtons) {
            button.render(g);
        }

        drawScore(g);
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
