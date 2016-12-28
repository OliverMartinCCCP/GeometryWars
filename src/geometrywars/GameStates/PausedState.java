package geometrywars.GameStates;

import Main.Game;
import geometrywars.GameObjects.MenuButton;
import geometrywars.GameStates.Menus.MainMenu;
import geometrywars.gfx.Assets;
import geometrywars.gfx.Background;
import geometrywars.gfx.ImageLoader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;
import org.lwjgl.input.Controller;

public class PausedState extends State {

    private static int currentSelection;
    private State state;

    private Controller controller;
    private boolean controllerSet;

    //hardcoded!!
    private int amountOfTicks;

    private Background background;
    private Background snowFlakes[];

    private static final Font font = new Font("Curlz MT", Font.CENTER_BASELINE, (int) (250 / Game.widthScaler));

    public PausedState(GameStateManager gsm) {
        this.menuButtonText = new String[]{"continue", "Main menu", "Exit"};
        this.menuButtons = new MenuButton[menuButtonText.length];
        this.gsm = gsm;
        this.keys = new boolean[256];
        this.amountOfTicks = 0;

        this.controllerSet = false;

        createMenuButtons();

        this.background = new Background(ImageLoader.loadImage("/textures/SnowBackground2.jpg"), (int) (Game.width / Game.widthScaler), (int) (Game.height / Game.heightScaler), 0, 0);
        this.snowFlakes = new Background[50];
        createSnowFlakes();

    }

    public void createSnowFlakes() {
        Random r = new Random();
        for (int i = 0; i < 50; i++) {
            int size = r.nextInt(60);
            snowFlakes[i] = new Background(ImageLoader.loadImage("/textures/snowFlakes3.png"), size, size, r.nextInt((int) (1920 / Game.widthScaler)), r.nextInt((int) (1080 / Game.heightScaler)));
            snowFlakes[i].setMode(true);
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        if (controller != null) {
            this.controllerSet = true;
        }
    }

    @Override
    public void init() {
        currentSelection = 0;
        //System.out.println("pausedState initialized");
    }

    @Override
    public void tick() {
        amountOfTicks++;

        for (Background snowFlake : snowFlakes) {
            snowFlake.tick();
        }
        for (MenuButton button : menuButtons) {
            button.tick();
        }

        if (amountOfTicks >= 5) {
            amountOfTicks = 0;
            processInput();
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
        if (keys[90]) {
            menuButtons[currentSelection].toggleSelection();
            if (currentSelection - 1 < 0) {

                currentSelection = menuButtons.length - 1;

            } else {
                currentSelection--;

            }
            menuButtons[currentSelection].toggleSelection();
        }

        //KP_DOWN
        if (keys[83]) {
            menuButtons[currentSelection].toggleSelection();
            currentSelection = (currentSelection + 1) % menuButtons.length;
            menuButtons[currentSelection].toggleSelection();

        }

        //KP_ENTER
        if (keys[10]) //check which button is selected and execute
        {
            execute();
            System.out.println("execute");
        }
    }

    public void execute() {
        if (currentSelection == 0) {
            gsm.setState(state);
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
        g.drawString("Geometrywars", (int) (200 / Game.widthScaler), (int) (250 / Game.heightScaler));
        for (MenuButton menuButton : menuButtons) {
            menuButton.render(g);
        }
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

    @Override
    public void createMenuButtons() {
        for (int i = 0; i < menuButtons.length; i++) {
            menuButtons[i] = new MenuButton(550, 400 + (i * 125), 400, 100, ID.MenuButton, menuButtonText[i], Assets.sleigh);
        }
        menuButtons[0].toggleSelection();
    }

}
