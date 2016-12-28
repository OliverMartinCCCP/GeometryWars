
package geometrywars.GameStates;

import Main.Game;
import geometrywars.Data.GeometrywarsDA;
import geometrywars.Display.HUD;
import geometrywars.GameObjects.Drones.KillerDrone;
import geometrywars.utilities.GameObjectHandler;
import geometrywars.GameObjects.Player.Player;
import geometrywars.GameObjects.Enemies.CircleEnemy;
import geometrywars.GameObjects.Drones.Drone;
import geometrywars.GameObjects.Enemies.Enemy;
import geometrywars.GameObjects.Enemies.HexagonEnemy;
import geometrywars.GameObjects.Enemies.TriangleEnemy;
import geometrywars.GameObjects.Geoms.Geom;
import geometrywars.GameStates.Menus.GameOverMenu;
import geometrywars.gfx.Background;
import geometrywars.gfx.ImageLoader;

import geometrywars.utilities.Clock;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;



public class ArcadeState extends State {

    private int wave;
    
    private GameObjectHandler objectHandler;
    private ArrayList<Player> players;

    private boolean keys[];
    
    private int amountPlayers;
    private Player player1;
    private Player player2;
    private Drone drone;
    
    private Clock clock;

    private float mouseX;
    private float mouseY;
    private boolean controllerSet;

    
    private Background background;
    
    private Controller controller;
    
    private static Timer myTimer;
    private static TimerTask myTimerTask;
    
    private HUD hud;
    
    //Variabelen om te niet teveel kogels tegelijk af te vuren.
    
  ////////////////////////////////////////////////////////////////
 ///WARNING/////////!! H A R D   C O D E  D !!//////////WARNING//
////////////////////////////////////////////////////////////////
    private boolean shootController = false;
    private boolean shootMouse = false;
    private boolean shootStatic = false;
    private boolean canShoot1 = false;
    private boolean canShoot2 = false;
    private int tickValue = 0;
    private int bulletsPerSecond = 8;
    
    private Background snowFlakes[];
    
    
    
    public ArcadeState(GameStateManager gsm, int amountPlayers) {
        this.gsm = gsm;
        this.keys = new boolean[256];       
        this.amountPlayers = amountPlayers;
        
        players = new ArrayList<>();
        objectHandler = new GameObjectHandler();
        background = new Background(ImageLoader.loadImage("/textures/SnowBackground2.jpg"), Game.width, Game.height, 0, 0);
        
        snowFlakes = new Background[50];
        Random r = new Random();
        for (int i = 0; i < 50; i++){
            int size = r.nextInt(60);
            snowFlakes[i] = new Background(ImageLoader.loadImage("/textures/snowFlakes3.png"), size, size, r.nextInt(1920), r.nextInt(1080));
            snowFlakes[i].setMode(true);
        }
        
        // aanmaak hud
        
        
        // scenario setup for testing
        makePlayer();
        makeEnemies(10);
        makeDrone();
        makeGeom();
        this.clock = new Clock();
        clock.start();
        this.wave = 1;
        
        // Controller
        this.controllerSet = false;
        
        try {
            Controllers.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        Controllers.poll();
        
        int amount = 0;

        while (!controllerSet && amount < Controllers.getControllerCount()){
            if (Controllers.getController(amount).getName().equals("Wireless Controller")){
                controller = Controllers.getController(amount);
                for(int i = 0; i < controller.getAxisCount(); i++){
                    System.out.println(i + ": " + controller.getAxisName(i));
                    controller.setDeadZone(i, (float) 0.3);
                }
                controllerSet = true;
            }
            amount ++;
        }   
               
        myTimer = new Timer();
    }
    
    

    @Override
    public void init() {
        System.out.println("playstate initialized");
        
    }

    @Override
    public void tick() {
        if (System.currentTimeMillis() >= player1.getTime()+2500){
            player1.setVelocity(4);
        }
        
        if (amountPlayers == 2){
            player2.setScore(player1.getScore());
            
            if (System.currentTimeMillis() >= player2.getTime()+2500){
                player2.setVelocity(4);
            }
        }
        
        if (objectHandler.processWave()){
            System.out.println("Wave cleared");
            wave ++;
            makeEnemies(3 + (wave*2));
            makeGeom();
        }
        
        if (!player1.isAlive() || !player2.isAlive()){
            GameOverMenu gameOver = new GameOverMenu(gsm);
            gameOver.setPlayers(player1, player2);
            gsm.setState(gameOver);
        }
        
        // process
        processInput();
        background.tick();
        for (int i = 0; i < 50; i++){
            snowFlakes[i].tick();
        }
        objectHandler.tick();
        objectHandler.processCollision();
    }
    
    

    @Override
    public void render(Graphics g) {
        background.render(g);
        for (int i = 0; i < 50; i++){
            snowFlakes[i].render(g);
        }
        hud.render(g);
        objectHandler.render(g);
        
    }

    @Override
    public void keyPressed(int k) {
        keys[k] = true;
        
        // spatie om te reloaden()
        if ( k == 82) {
            player1.reload();
        }
        // shift om power uit te voeren
        if ( k == 32 ) {
            player1.usePower();
        }
        if ( k == 65 ) {
            player1.previousWeapon();
        }
        if ( k == 69 ) {
            player1.nextWeapon();
        }
    }

    @Override
    public void keyReleased(int k) {
        keys[k] = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        
        shootStatic = false;
        shootMouse = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == e.BUTTON1) {
            mouseX = e.getX();
            mouseY = e.getY();

            shootMouse = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shootMouse = false;
        shootStatic = false;
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
    //afbakening van veld en objecten
    public static boolean clamp(int var, int min, int max) {
        if (var >= max) {
            return true;
        } else if (var <= min) {
            return true;
        } else {
            return false;
        }
    }
    public void makePlayer(){
        this.player1 = GeometrywarsDA.getInstance().getPlayer(500, 200, "Clydez");
        player1.setHandler(objectHandler);
        players.add(player1);
        objectHandler.addObject(player1);
        
        if (amountPlayers == 2){
            player2 = GeometrywarsDA.getInstance().getPlayer(500, 200, "Derango");
            player2.setHandler(objectHandler);
            players.add(player2);
            objectHandler.addObject(player2);
        }else{
            this.player2 = player1;
        }
        
        hud = new HUD(players);
    }
    
    public void makeEnemies(int amount) {
        for (int i = 0; i < amount; i++) {
            Enemy enemy = new CircleEnemy(getRandomX(), getRandomY(), ID.Enemy);
            if (amountPlayers == 2){
                enemy.setTargets(player1, player2);
            }else{
                enemy.setTarget(player1);
            }
            objectHandler.addObject(enemy);
            enemy = new TriangleEnemy(getRandomX(), getRandomY(), ID.Enemy);
            enemy.setTarget(player1);
            objectHandler.addObject(enemy);
            enemy = new HexagonEnemy(getRandomX(), getRandomY(), ID.Enemy);
            enemy.setTarget(player1);
            objectHandler.addObject(enemy);
        }
    }
    
    public int getRandomX(){
        Random r = new Random();
        int randomX;
        if (player1.getX() > Game.width/2){
            randomX = r.nextInt((int) (player1.getX()) - Game.width/2);
        } else{
            randomX = r.nextInt((int) (player1.getX()) + Game.width/2);
        }

        return randomX;
    }
    
    public int getRandomY(){
        Random r = new Random();
        int randomY;
        if (player1.getY() > Game.height/2){
            randomY = r.nextInt((int) (player1.getY()-50));
        } else{
            randomY = r.nextInt((int) (player1.getY())) + Game.height/2;
        }
        
        
        return randomY;
    }
 
    public void makeDrone(){
        Drone d1 = new KillerDrone( 550, 630, ID.Drone, player1 );
        Drone d2 = new KillerDrone( 550, 630, ID.Drone, player2 );
        
        objectHandler.addObject(d1);
        objectHandler.addObject(d2);
        
    }    
    
    public void makeGeom(){
        Random r = new Random();
        for (int i = 0; i < 1; i++){
            objectHandler.addObject(new Geom( r.nextInt(Game.width), r.nextInt(Game.height) ));
        }
    }
    public void processInput(){             
        
        if (controllerSet){
            controller.poll();
        
            if (controller.isButtonPressed(7)){
                shootController = true;
            }else{
                shootController = false;
            }

            player2.move(controller.getAxisValue(3), controller.getAxisValue(2));
            
            if (controller.isButtonPressed(9)){
                PausedState pausedState = new PausedState(gsm);
                pausedState.setState(this);
                pausedState.setController(controller);
                gsm.setState(pausedState);
            }
            
            if (controller.isButtonPressed(0)){
                // Todo: bij keypressed zetten
                player2.reload();
            }
        }
        
        
        if (keys[81]) {
            player1.moveLeft();
        }
        if (keys[90]) {
            player1.moveUp();
        }
        if (keys[68]) {
            player1.moveRight();
        }
        if (keys[83]) {
            player1.moveDown();
        }
        
        if (keys[27]) {
            keys[27] = false;
            PausedState pausedState = new PausedState(gsm);
            pausedState.setState(this);
            gsm.setState(pausedState);
        }
        
        if (shootMouse || shootStatic || shootController) {
             if (tickValue == 60 / player1.getWeapon().getFireRate()) {
                if (shootMouse){
                    canShoot1 = true;
                }
                if (shootController){
                    canShoot2 = true;
                }
                tickValue = 0;
            }

            tickValue += 1;
            
            if (tickValue == 60){
                tickValue = 0;
            }
            
            if (canShoot1){
                    player1.fireMouse(mouseX, mouseY);
                    canShoot1 = false;
                }
            }
            
            if (canShoot2){
                if (player2.isAbleToFire()){
                    if (controllerSet){
                        player2.fireController(controller.getAxisValue(1), controller.getAxisValue(0));
                    }
                    canShoot2 = false;
                }
            }
    }

    @Override
    public void createMenuButtons() {
    }

}
