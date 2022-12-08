/**
 * the purpose of this class is program control from start to finish: load, initialize, monitor, and update all
 * data (sounds, images, UI, map) while the game status is active. This is the "Engine" that powers the game itself.
 *
 */

package manager;

import model.hero.Mario;
import view.ImageLoader;
import view.StartScreenSelection;
import view.UIManager;

import javax.swing.*;
import java.awt.*;

public class GameEngine implements Runnable {

    //constant window frame size
    private final static int WIDTH = 1268, HEIGHT = 708;

    private MapManager mapManager; // monitor/update map
    private UIManager uiManager; // update UI
    private SoundManager soundManager; // play sound corresponding to the specific game object
    private GameStatus gameStatus; // active-paused-inactive
    private boolean isRunning; // flag o notify GameEngine of gameStatus
    private Camera camera; // frame adjustment object
    private ImageLoader imageLoader; // load sprite images
    private Thread thread; // ensure thread safety
    private StartScreenSelection startScreenSelection = StartScreenSelection.START_GAME; // start screen
    private int selectedMap = 0; // map selection

    //initialize Singleton GameEngine object with use of private ctor
    private GameEngine() {
        init();
    }

    //initialization
    private void init() {
        imageLoader = new ImageLoader();
        InputManager inputManager = new InputManager(this);
        gameStatus = GameStatus.START_SCREEN;
        camera = new Camera();
        uiManager = new UIManager(this, WIDTH, HEIGHT);
        soundManager = new SoundManager();
        mapManager = new MapManager();

        JFrame frame = new JFrame("Super Mario Bros.");
        frame.add(uiManager);
        frame.addKeyListener(inputManager);
        frame.addMouseListener(inputManager);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        start();
    }

    // start game (thread safe)
    private synchronized void start() {
        if (isRunning)
            return;

        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    // reset after death <= 3 otherwise GameStatus == GAME_OVER
    private void reset(){
        resetCamera();
        setGameStatus(GameStatus.START_SCREEN);
    }

    // adjust frames to starting position
    public void resetCamera(){
        camera = new Camera();
        soundManager.restartBackground();
    }

    // user selection via mouse
    public void selectMapViaMouse() {
        String path = uiManager.selectMapViaMouse(uiManager.getMousePosition());
        if (path != null) {
            createMap(path);
        }
    }

    // user selection via keyboard
    public void selectMapViaKeyboard(){
        String path = uiManager.selectMapViaKeyboard(selectedMap);
        if (path != null) {
            createMap(path);
        }
    }

    // change map to play
    public void changeSelectedMap(boolean up){
        selectedMap = uiManager.changeSelectedMap(selectedMap, up);
    }

    // create the map and update GameStatus
    private void createMap(String path) {
        boolean loaded = mapManager.createMap(imageLoader, path);
        if(loaded){
            setGameStatus(GameStatus.RUNNING);
            soundManager.restartBackground();
        }

        else
            setGameStatus(GameStatus.START_SCREEN);
    }

    // run game & start/update timer
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (isRunning && !thread.isInterrupted()) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                if (gameStatus == GameStatus.RUNNING) {
                    gameLoop();
                }
                delta--;
            }
            render();

            if(gameStatus != GameStatus.RUNNING) {
                timer = System.currentTimeMillis();
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                mapManager.updateTime();
            }
        }
    }

    // render the UI components and actively update
    private void render() {
        uiManager.repaint();
    }

    // fluid checks for active frames, game object coordinates, and collisions
    private void gameLoop() {
        updateLocations();
        checkCollisions();
        updateCamera();

        if (isGameOver()) {
            setGameStatus(GameStatus.GAME_OVER);
        }

        int missionPassed = passMission();
        if(missionPassed > -1){
            mapManager.acquirePoints(missionPassed);
            //setGameStatus(GameStatus.MISSION_PASSED);
        } else if(mapManager.endLevel())
            setGameStatus(GameStatus.MISSION_PASSED);
    }

    // shift active frames centered on Mario
    private void updateCamera() {
        Mario mario = mapManager.getMario();
        double marioVelocityX = mario.getVelX();
        double shiftAmount = 0;

        if (marioVelocityX > 0 && mario.getX() - 600 > camera.getX()) {
            shiftAmount = marioVelocityX;
        }

        camera.moveCam(shiftAmount, 0);
    }

    private void updateLocations() {
        mapManager.updateLocations();
    }

    private void checkCollisions() {
        mapManager.checkCollisions(this);
    }

    public void receiveInput(ButtonAction input) {

        if (gameStatus == GameStatus.START_SCREEN) {
            setStartScreenSelection(input);
        }
        else if(gameStatus == GameStatus.MAP_SELECTION){
            if(input == ButtonAction.SELECT){
                selectMapViaKeyboard();
            }
            else if(input == ButtonAction.GO_UP){
                changeSelectedMap(true);
            }
            else if(input == ButtonAction.GO_DOWN){
                changeSelectedMap(false);
            }
        } else if (gameStatus == GameStatus.RUNNING) {
                setRunning(input);
        } else if (gameStatus == GameStatus.PAUSED) {
            if (input == ButtonAction.PAUSE_RESUME) {
                pauseGame();
            }
        } else if(gameStatus == GameStatus.GAME_OVER && input == ButtonAction.GO_TO_START_SCREEN){
            reset();
        } else if(gameStatus == GameStatus.MISSION_PASSED && input == ButtonAction.GO_TO_START_SCREEN){
            reset();
        }

        if(input == ButtonAction.GO_TO_START_SCREEN){
            setGameStatus(GameStatus.START_SCREEN);
        }
    }

    // auxiliary function for receiveInput() to respond to user selection on start screen
    public void setStartScreenSelection(ButtonAction input) {
        if (input == ButtonAction.SELECT && startScreenSelection == StartScreenSelection.START_GAME) {
            startGame();
        } else if (input == ButtonAction.SELECT && startScreenSelection == StartScreenSelection.VIEW_ABOUT) {
            setGameStatus(GameStatus.ABOUT_SCREEN);
        } else if (input == ButtonAction.SELECT && startScreenSelection == StartScreenSelection.VIEW_HELP) {
            setGameStatus(GameStatus.HELP_SCREEN);
        } else if (input == ButtonAction.GO_UP) {
            selectOption(true);
        } else if (input == ButtonAction.GO_DOWN) {
            selectOption(false);
        }
    }

    //auxiliary function for receiveInput() to respond to user keyboard input
    public void setRunning(ButtonAction input) {
        Mario mario = mapManager.getMario();
        if (input == ButtonAction.JUMP) {
            mario.jump(this);
        } else if (input == ButtonAction.M_RIGHT) {
            mario.move(true, camera);
        } else if (input == ButtonAction.M_LEFT) {
            mario.move(false, camera);
        } else if (input == ButtonAction.ACTION_COMPLETED) {
            mario.setVelX(0);
        } else if (input == ButtonAction.FIRE) {
            mapManager.fire(this);
        } else if (input == ButtonAction.PAUSE_RESUME) {
            pauseGame();
        }
    }

    // start screen selection
    private void selectOption(boolean selectUp) {
        startScreenSelection = startScreenSelection.select(selectUp);
    }

    private void startGame() {
        if (gameStatus != GameStatus.GAME_OVER) {
            setGameStatus(GameStatus.MAP_SELECTION);
        }
    }

    private void pauseGame() {
        if (gameStatus == GameStatus.RUNNING) {
            setGameStatus(GameStatus.PAUSED);
            soundManager.pauseBackground();
        } else if (gameStatus == GameStatus.PAUSED) {
            setGameStatus(GameStatus.RUNNING);
            soundManager.resumeBackground();
        }
    }

    public void shakeCamera(){
        camera.shakeCamera();
    }

    private boolean isGameOver() {
        if(gameStatus == GameStatus.RUNNING)
            return mapManager.isGameOver();
        return false;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public StartScreenSelection getStartScreenSelection() {
        return startScreenSelection;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getScore() {
        return mapManager.getScore();
    }

    public int getRemainingLives() {
        return mapManager.getRemainingLives();
    }

    public int getCoins() {
        return mapManager.getCoins();
    }

    public int getSelectedMap() {
        return selectedMap;
    }

    public void drawMap(Graphics2D g2) {
        mapManager.drawMap(g2);
    }

    public Point getCameraLocation() {
        return new Point((int)camera.getX(), (int)camera.getY());
    }

    private int passMission(){
        return mapManager.passMission();
    }

    public void playCoin() {
        soundManager.playCoin();
    }

    public void playOneUp() {
        soundManager.playOneUp();
    }

    public void playSuperMushroom() {
        soundManager.playSuperMushroom();
    }

    public void playMarioDies() {
        soundManager.playMarioDies();
    }

    public void playJump() {
        soundManager.playJump();
    }

    public void playFireFlower() {
        soundManager.playFireFlower();
    }

    public void playFireball() {
        soundManager.playFireball();
    }

    public void playStomp() {
        soundManager.playStomp();
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public static void main(String... args) {
        new GameEngine();
    }

    public int getRemainingTime() {
        return mapManager.getRemainingTime();
    }

    public void playRunningOutOfTime() { soundManager.playTimeWarning(); }

    public void playPowerUpAppears() { soundManager.playPowerUpAppears(); }
}
