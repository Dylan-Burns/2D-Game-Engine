/**
 * the purpose of this class is to provide the behaviors and attributes of a magic mushroom game object
 */

package model.prize;

import manager.GameEngine;
import model.hero.Mario;
import model.hero.MarioForm;
import view.Animation;
import view.ImageLoader;

import java.awt.image.BufferedImage;

public class MagicMushroom extends BoostItem{

    public MagicMushroom(double x, double y, BufferedImage style) {
        super(x, y, style);
        setPoint(125);
    }

    @Override
    public void onTouch(Mario mario, GameEngine engine) {
        mario.acquirePoints(getPoint());

        ImageLoader imageLoader = new ImageLoader();

        if(!mario.getMarioForm().isSuper()){
            BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SUPER);
            BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SUPER);

            Animation animation = new Animation(leftFrames, rightFrames);
            MarioForm newForm = new MarioForm(animation, true, false);
            mario.setMarioForm(newForm);
            mario.setDimension(48, 96);

            engine.playSuperMushroom();
        }
    }
//    @Override
//    public void onReveal(GameEngine engine) {
//        engine.playPowerUpAppears();
//    }
}
