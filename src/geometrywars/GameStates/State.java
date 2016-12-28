package geometrywars.GameStates;

import Main.Game;
import geometrywars.GameObjects.MenuButton;
import geometrywars.gfx.Background;
import geometrywars.gfx.ImageLoader;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;
import org.lwjgl.input.Controller;

public abstract class State {

    protected GameStateManager gsm;
    protected SettingState settingState;
    protected MenuButton[] menuButtons;
    
    protected int amountOfTicks;

    protected String[] menuButtonText;

    protected boolean keys[];

    protected boolean controllerSet;

    protected Controller controller;

    protected Background background;
    protected Background snowFlakes[];
    protected Font font = new Font("Curlz MT", Font.CENTER_BASELINE, 250);

    public State() {
        this.background = new Background(ImageLoader.loadImage("/textures/SnowBackground2.jpg"), (int) (Game.width / Game.widthScaler), (int) (Game.height / Game.heightScaler), 0, 0);
        this.snowFlakes = new Background[50];
        this.amountOfTicks = 0;
    }

    public void createSnowFlakes() {
        Random r = new Random();
        for (int i = 0; i < 50; i++) {
            int size = r.nextInt(60);
            snowFlakes[i] = new Background(ImageLoader.loadImage("/textures/snowFlakes3.png"), size, size, r.nextInt((int) (1920 / Game.widthScaler)), r.nextInt((int) (1080 / Game.heightScaler)));
            snowFlakes[i].setMode(true);
        }
    }
    
    public void setFont(Font font){
        this.font = font;
    }

    public abstract void init();

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void createMenuButtons();

    public abstract void keyPressed(int k);

    public abstract void keyReleased(int k);

    public abstract void mouseDragged(MouseEvent e);

    public abstract void mouseMoved(MouseEvent e);

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseEntered(MouseEvent e);

    public abstract void mouseExited(MouseEvent e);

    public abstract void mouseClicked(MouseEvent e);

}