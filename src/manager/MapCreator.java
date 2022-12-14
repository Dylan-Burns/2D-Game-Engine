/**
 * the purpose of this class is to load the required data (background and game graphics, sprite images, etc)
 * and control object creation/destruction and positioning
 */

package manager;

import model.EndFlag;
import model.brick.*;
import model.prize.*;
import view.ImageLoader;
import model.Map;
import model.enemy.Enemy;
import model.enemy.Goomba;
import model.enemy.KoopaTroopa;
import model.hero.Mario;

import java.awt.*;
import java.awt.image.BufferedImage;

class MapCreator {

    private ImageLoader imageLoader;

    private BufferedImage backgroundImage; // map graphics
    private BufferedImage superMushroom, oneUpMushroom, fireFlower, coin; // power-ups
    private BufferedImage ordinaryBrick, surpriseBrick, groundBrick, pipe; // static game objects
    private BufferedImage goombaLeft, goombaRight, koopaLeft, koopaRight, endFlag; // dynamic game objects


    MapCreator(ImageLoader imageLoader) {

        this.imageLoader = imageLoader;
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");

        // crop images from sprite sheet
        this.backgroundImage = imageLoader.loadImage("/background.png");
        this.superMushroom = imageLoader.getSubImage(sprite, 2, 5, 48, 48);
        this.oneUpMushroom= imageLoader.getSubImage(sprite, 3, 5, 48, 48);
        this.fireFlower= imageLoader.getSubImage(sprite, 4, 5, 48, 48);
        this.coin = imageLoader.getSubImage(sprite, 1, 5, 48, 48);
        this.ordinaryBrick = imageLoader.getSubImage(sprite, 1, 1, 48, 48);
        this.surpriseBrick = imageLoader.getSubImage(sprite, 2, 1, 48, 48);
        this.groundBrick = imageLoader.getSubImage(sprite, 2, 2, 48, 48);
        this.pipe = imageLoader.getSubImage(sprite, 3, 1, 96, 96);
        this.goombaLeft = imageLoader.getSubImage(sprite, 2, 4, 48, 48);
        this.goombaRight = imageLoader.getSubImage(sprite, 5, 4, 48, 48);
        this.koopaLeft = imageLoader.getSubImage(sprite, 1, 3, 48, 64);
        this.koopaRight = imageLoader.getSubImage(sprite, 4, 3, 48, 64);
        this.endFlag = imageLoader.getSubImage(sprite, 5, 1, 48, 48);

    }

    Map createMap(String mapPath, double timeLimit) {
        BufferedImage mapImage = imageLoader.loadImage(mapPath);

        //verify map is valid before passing off controls
        validateMap(mapImage);

        Map createdMap = new Map(timeLimit, backgroundImage);
        String[] paths = mapPath.split("/");
        createdMap.setPath(paths[paths.length-1]);

        fillMap(mapImage, createdMap);

        System.out.println("Map is created..");
        return createdMap;
    }

    public void validateMap(BufferedImage mapImage) {
        if (mapImage == null) {
            System.out.println("Path is invalid...");
        }
    }

    // create game objects from sprite sheet then initialize and load them into the newly created map
    public void fillMap(BufferedImage mapImage, Map createdMap) {

        int pixelMultiplier = 48;

        int mario = new Color(160, 160, 160).getRGB();
        int ordinaryBrick = new Color(0, 0, 255).getRGB();
        int surpriseBrick = new Color(255, 255, 0).getRGB();
        int groundBrick = new Color(255, 0, 0).getRGB();
        int pipe = new Color(0, 255, 0).getRGB();
        int goomba = new Color(0, 255, 255).getRGB();
        int koopa = new Color(255, 0, 255).getRGB();
        int end = new Color(160, 0, 160).getRGB();

        for (int x = 0; x < mapImage.getWidth(); x++) {
            for (int y = 0; y < mapImage.getHeight(); y++) {

                int currentPixel = mapImage.getRGB(x, y);
                int xLocation = x*pixelMultiplier;
                int yLocation = y*pixelMultiplier;

                if (currentPixel == ordinaryBrick) {
                    Coin coin = generateCoin(xLocation, yLocation);
                    Brick brick = new OrdinaryBrick(xLocation, yLocation, this.ordinaryBrick, coin);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == surpriseBrick) {
                    Prize prize = generateRandomPrize(xLocation, yLocation);
                    Brick brick = new SurpriseBrick(xLocation, yLocation, this.surpriseBrick, prize);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == pipe) {
                    Brick brick = new Pipe(xLocation, yLocation, this.pipe);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == groundBrick) {
                    Brick brick = new GroundBrick(xLocation, yLocation, this.groundBrick);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == goomba) {
                    Enemy enemy = new Goomba(xLocation, yLocation, this.goombaLeft);
                    ((Goomba)enemy).setRightImage(goombaRight);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == koopa) {
                    Enemy enemy = new KoopaTroopa(xLocation, yLocation, this.koopaLeft);
                    ((KoopaTroopa)enemy).setRightImage(koopaRight);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == mario) {
                    Mario marioObject = new Mario(xLocation, yLocation);
                    createdMap.setMario(marioObject);
                }
                else if(currentPixel == end){
                    EndFlag endPoint= new EndFlag(xLocation+24, yLocation, endFlag);
                    createdMap.setEndPoint(endPoint);
                }
            }
        }
    }

    // randomize which prize is generated
    private Prize generateRandomPrize(double x, double y){
        Prize generated;
        int random = (int)(Math.random() * 12);

        switch(random) {
            case 0 -> generated = new MagicMushroom(x, y, this.superMushroom); //super mushroom
            case 1 -> generated = new FireFlower(x, y, this.fireFlower); //fire flower
            case 2 -> generated = new Coin(x, y, this.coin, 50); //one up mushroom
            default ->  generated = new Coin(x, y, this.coin, 50); //coin
        }
        return generated;
    }

    // create a new coin object
    private Coin generateCoin(double x, double y) {
        return new Coin(x, y, this.coin, 50);
    }
}
