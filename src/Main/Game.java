/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
    * and open the template in the editor.
 */
package Main;

import geometrywars.Display.Window;
import geometrywars.GameStates.GameStateManager;
import geometrywars.gfx.Assets;
import geometrywars.scaling.Scale;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

public class Game implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    // serialVersionUID is nodig omdat canvas een serialized class is.
    // Als je dit niet aanmaakt dan maakt de klasse automatisch 1 en
    // kun je InvalidClassExceptions krijgen.
    private static final long serialVersionUID = 1550691097823471818L;

    //parameters om frame aan te maken
    public static int width, height;
    private String title;
    // Thread is nodig om verschillende processen tegelijk te kunnen afhandelen
    private Thread thread;
    // Running wordt true in start()
    private boolean running = false;

    public static Window window;
    
    private GameStateManager gsm;
    
    public static double widthScaler = 1, heightScaler = 1;
    public static Scale scale;
    
    public Game(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        //Sound.playSound("Music_jingle.wav", -15);
    }

    private void init() {
        Assets.init();
        gsm = new GameStateManager();
        
        window = new Window(width, height, title);
        window.getFrame().addKeyListener(this);
        window.getFrame().addMouseListener(this);
        window.getCanvas().addMouseListener(this);
        window.getFrame().addMouseMotionListener(this);
        window.getCanvas().addMouseMotionListener(this);
        
        gsm.setWindow(window);
        scale = new Scale(window);
    }

    // Synchronized nodig om alle processen simultaan af te handelen
    // samen met thread
    public synchronized void start() {
        // Thread wordt aangemaakt
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }


    
    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
        //Gameloop
        //Bepaalt oa de frames
        //wiskundig
        //copy paste
        long lastTime = System.nanoTime();

        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                    //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    // refreshen van LinkedList<GameObjects> die graphics updated
    private void tick() {

        gsm.tick();
    }

    private void render() {
        //Buffer maken en inladen
        //buffer zorgt ervoor dat het scherm vloeiend wordt weergegeven
        //dmv multibuffer(3) staat er continue een queue van graphics klaar
        BufferStrategy bs = window.getCanvas().getBufferStrategy();
        if (bs == null) {
            window.getCanvas().createBufferStrategy(3);
            return;
        }
        // paint it black

        Graphics g = bs.getDrawGraphics();
        //g.setColor(Color.black);
        //g.fillRect(0, 0, window.getWidth(), window.getHeight());



        gsm.render(g);

        //oude graphics verwijderen (maw player kan niet op 2 plaatsen tegelijkertijd zijn)
        //??? Wanneer ik dit weglaat werkt alles nog steeds
        g.dispose();

        //Next Queue van buffer tonen
        bs.show();
    } 

    @Override
    public void keyPressed(KeyEvent key){
        gsm.keyPressed(key.getKeyCode());
        //System.out.println(key.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent key){
        gsm.keyReleased(key.getKeyCode());
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gsm.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gsm.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        gsm.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gsm.mouseMoved(e);
    }
    
}
