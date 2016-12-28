package geometrywars.GameStates.Menus;

import Main.Game;
import geometrywars.GameObjects.MenuButton;
import geometrywars.GameStates.GameStateManager;
import geometrywars.GameStates.ID;
import geometrywars.GameStates.SettingState;
import geometrywars.GameStates.State;
import geometrywars.gfx.Assets;
import geometrywars.gfx.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controllers;

public class MainMenu extends State {

    private static int currentSelection;

    private String[] menuButtonText;

    

    public MainMenu(GameStateManager gsm) {
        setFont(new Font("Curlz MT", Font.CENTER_BASELINE, (int) (250 / Game.widthScaler)));

        this.gsm = gsm;
        this.settingState = new SettingState(gsm);
        this.keys = new boolean[256];

        this.menuButtonText = new String[]{"New Game", "Settings", "Highscores", "Exit"};
        this.menuButtons = new MenuButton[menuButtonText.length];
        createMenuButtons();
        createController();

        this.createSnowFlakes();
        
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
        //System.out.println("Mainmenu" +Game.widthScaler);
        for (MenuButton menuButton : menuButtons) {
            menuButton.render(g);
        }
    }

    @Override
    public void createMenuButtons() {
        for (int i = 0; i < menuButtons.length; i++) {
            menuButtons[i] = new MenuButton((int) (550 / Game.widthScaler), (int) ((400 + (i * 125)) / Game.heightScaler), (int) (400 / Game.widthScaler), (int) (100 / Game.heightScaler), ID.MenuButton, menuButtonText[i], Assets.sleigh);
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
            gsm.setState(new PlayMenu(this, gsm));
        }
        if (currentSelection == 1) {
            settingState.setMenuVisibility(true);
        }
        if (currentSelection == 2) {
            gsm.setState(new HighScoresMenu(gsm));
        }
        if (currentSelection == 3) {
            System.exit(1);
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
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

}
