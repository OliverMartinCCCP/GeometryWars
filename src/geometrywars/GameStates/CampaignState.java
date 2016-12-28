package geometrywars.GameStates;

import geometrywars.GameStates.Menus.GameOverMenu;
import Main.Game;
import geometrywars.Data.GeometrywarsDA;
import geometrywars.Display.HUD;
import geometrywars.GameObjects.Drones.KillerDrone;
import geometrywars.utilities.GameObjectHandler;
import geometrywars.GameObjects.Player.Player;
import geometrywars.GameObjects.Enemies.CircleEnemy;
import geometrywars.GameObjects.Drones.Drone;
import geometrywars.GameObjects.Enemies.Boss;
import geometrywars.GameObjects.Enemies.Enemy;
import geometrywars.GameObjects.Enemies.HexagonEnemy;
import geometrywars.GameObjects.Enemies.TriangleEnemy;
import geometrywars.GameStates.Menus.LevelCompleteMenu;
import geometrywars.gfx.Background;
import geometrywars.gfx.ImageLoader;
import geometrywars.levels.CampaignLevels;

import geometrywars.utilities.Clock;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.lwjgl.input.Controller;

public class CampaignState extends State {

    private int wave;

    private GameObjectHandler objectHandler;

    private boolean keys[];

    private int amountPlayers;
    private Player player1;
    private List<Player> players;
    private Drone drone;
    private Boss boss;

    private Clock clock;

    private float mouseX;
    private float mouseY;
    
    private Background background;
    private HUD hud;
    
    private boolean controllerSet;
    private Controller controller;
    
    private int level;
    private boolean bossLevel;
    private int amountCircle;
    private int amountTriangle;
    private int amountHexagon;
    private int selectedLevel;
    

    //Variabelen om te niet teveel kogels tegelijk af te vuren.
    ////////////////////////////////////////////////////////////////
    ///WARNING/////////!! H A R D   C O D E  D !!//////////WARNING//
////////////////////////////////////////////////////////////////
    private boolean shootController = false;
    private boolean shootMouse = false;
    private boolean shootStatic = false;
    private boolean canShoot = false;
    private int tickValue = 0;
    private int bulletsPerSecond = 8;

    public CampaignState(GameStateManager gsm, Controller controller, int selectedLevel) {
        this.gsm = gsm;
        this.keys = new boolean[256];
        objectHandler = new GameObjectHandler();
        background = new Background(ImageLoader.loadImage("/textures/snowBackground2.jpg"), Game.width, Game.height, 0, 0);
        players = new ArrayList<>();
        this.selectedLevel = selectedLevel;
        
        // scenario setup for testing
        makePlayer();
        makeDrone();
        this.clock = new Clock();
        clock.start();
        this.wave = 1;
        this.bossLevel = false;
        
        
        this.controllerSet = false;
        this.controller = controller;
        if (controller != null){
            controllerSet = true;
        }

        
    }

    @Override
    public void init() {
        System.out.println("Campaignstate initialized");
        
        /*
        File file = new File(".");
        for(String fileNames : file.list()) System.out.println(fileNames);
        */
        
        String fileName = "res/levels/levels.txt";
        CampaignLevels level = new CampaignLevels(fileName, selectedLevel);
        
        bossLevel = level.getBossEnemy();
        
        if (bossLevel){
            makeBoss();
        }else{
            this.amountCircle = level.getAmountCircle();
            this.amountTriangle = level.getAmountTriangle();
            this.amountHexagon = level.getAmountHexagon();
        
            makeEnemies(amountCircle, amountTriangle, amountHexagon);
        }
        
    }

    @Override
    public void tick() {
        if (System.currentTimeMillis() >= player1.getTime()+2000){
            player1.setVelocity(4);
        }
        
        if (bossLevel && clock.timePassed()>500 && boss.isAlive()){
            clock = new Clock();
            boss.fire();
            clock.start();
        }
        
        if (objectHandler.processEnemies()) {
            if (selectedLevel == player1.getLevel()){
                GeometrywarsDA.getInstance().updateLevel(player1.getPlayerId(), (level + 1));
            }
            
            //Level complete scherm
            LevelCompleteMenu levelComplete = new LevelCompleteMenu(gsm, selectedLevel);
            levelComplete.setController(controller);
            levelComplete.setPlayers(player1);
            gsm.setState(levelComplete);
        }

        
        if (!player1.isAlive()) {
            GameOverMenu gameOver = new GameOverMenu(gsm);
            gameOver.setPlayers(player1);
            gameOver.setController(controller);
            gsm.setState(gameOver);
        }

        // process 
        processInput();
        background.tick();
        objectHandler.tick();
        objectHandler.processCollision();
    }

    @Override
    public void render(Graphics g) {
        background.render(g);
        hud.render(g);
        objectHandler.render(g);
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

    public void makePlayer() {
        this.player1 = GeometrywarsDA.getInstance().getPlayer(500, 200, "Clydez");
        player1.setVelX(4);
        player1.setVelY(4);
        players.add(player1);
        objectHandler.addObject(player1);
        
        hud = new HUD(players);

        level = player1.getLevel();
    }

    public void makeEnemies(int amountCircle, int amountTriangle, int amountHexagon) {
        Enemy enemy;
        
        for (int i = 0; i < amountCircle; i++) {
            enemy = new CircleEnemy(getRandomX(), getRandomY(), ID.Enemy);
            enemy.setTarget(player1);
            objectHandler.addObject(enemy);
        }
        for (int i = 0; i < amountTriangle; i++) {
            enemy = new TriangleEnemy(getRandomX(), getRandomY(), ID.Enemy);
            enemy.setTarget(player1);
            objectHandler.addObject(enemy);
        }
        for (int i = 0; i < amountHexagon; i++) {
            enemy = new HexagonEnemy(getRandomX(), getRandomY(), ID.Enemy);
            enemy.setTarget(player1);
            objectHandler.addObject(enemy);
        }
    }
    
    public void makeBoss(){
        Enemy enemy = new Boss(1000, 250, this,ID.Enemy);
        objectHandler.addObject(enemy);
        this.boss = (Boss) enemy;
    }
    
    public int getRandomX(){
        Random r = new Random();
        int randomX;
        if (player1.getX() > Game.width/2){
            randomX = r.nextInt((int) (player1.getX()) - 200);
        } else{
            randomX = r.nextInt((int) (player1.getX()) + Game.width/2 - 40);
        }

        return randomX;
    }
    
    public int getRandomY(){
        Random r = new Random();
        int randomY;
        if (player1.getY() > Game.height/2){
            randomY = r.nextInt((int) (player1.getY()-50));
        } else{
            randomY = r.nextInt((int) (player1.getY())) + Game.height/2 - 70;
        }
        
        
        return randomY;
    }

    public void makeDrone() {
        Drone d1 = new KillerDrone(550, 630, ID.Drone, player1);
        objectHandler.addObject(d1);
    }
    
    
    ///////GETTERS///////
    public Player getPlayer(){
        return player1;
    }
    
    public GameObjectHandler getHandler(){
        return objectHandler;
    }
    
    
    //////INPUT/////////

    public void processInput() {
        if (controllerSet) {
            controller.poll();

            if (controller.isButtonPressed(7)) {
                shootController = true;
            } else {
                shootController = false;
            }

            player1.move(controller.getAxisValue(3), controller.getAxisValue(2));

            if (controller.isButtonPressed(9)) {
                PausedState pausedState = new PausedState(gsm);
                pausedState.setState(this);
                pausedState.setController(controller);
                gsm.setState(pausedState);
            }

            if (controller.isButtonPressed(0)) {
                player1.reload();
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

        if (keys[82]) {
            player1.reload();
        }

        if (shootMouse || shootStatic || shootController) {
            if (tickValue == 60 / player1.getWeapon().getFireRate()) {
                if (shootMouse || shootController) {
                    canShoot = true;
                }
                tickValue = 0;
            }

            tickValue += 1;
            if (tickValue == 60){
                tickValue = 0;
            }
            
            if (canShoot) {
                if (player1.isAbleToFire()) {

                    player1.fireMouse(mouseX, mouseY);
                    canShoot = false;
                }

            }
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

    @Override
    public void createMenuButtons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
