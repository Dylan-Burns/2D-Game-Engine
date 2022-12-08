/**
 * the purpose of this class is to implement the basic functionality of the template brick for other
 * brick-like objects to inherit
 */

package model.brick;

import manager.GameEngine;
import manager.MapManager;
import model.Map;
import model.prize.Coin;
import model.prize.Prize;
import view.Animation;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OrdinaryBrick extends Brick {

    private Coin coin;
    private Animation animation;
    private boolean breaking;
    private int frames;

    public OrdinaryBrick(double x, double y, BufferedImage style, Coin coin){
        super(x, y, style);
        setBreakable(true);
        setEmpty(false);
        this.coin = coin;

        setAnimation();
        breaking = false;
        frames = animation.getLeftFrames().length;
    }

    private void setAnimation(){
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage[] leftFrames = imageLoader.getBrickFrames();

        animation = new Animation(leftFrames, leftFrames);
    }

//    @Override
//    public Prize reveal(GameEngine engine){
//        MapManager manager = engine.getMapManager();
//        if(!manager.getMario().isSuper())
//            return null;
//
//        breaking = true;
//        manager.addRevealedBrick(this);
//
//        double newX = getX() - 27, newY = getY() - 27;
//        setLocation(newX, newY);
//
//        return null;
//
//
//    }

    @Override
    public Coin reveal(GameEngine engine){
        BufferedImage newStyle = engine.getImageLoader().loadImage("/sprite.png");
        newStyle = engine.getImageLoader().getSubImage(newStyle, 1, 2, 48, 48);
        if(this.coin != null)
            coin.reveal();
        setEmpty(true);
        setStyle(newStyle);

        Coin toReveal = this.coin;
        this.coin = null;
        return toReveal;
    }

    public int getFrames(){
        return frames;
    }

    public void animate(){
        if(breaking){
            setStyle(animation.animate(3, true));
            frames--;
        }
    }

    @Override
    public Coin getPrize(){
        return coin;
    }
}
