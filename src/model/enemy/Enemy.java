/**
 * the purpose of this class is to create a template enemy object that provides basic functionality
 * for other enemy objects to inherit
 */

package model.enemy;

import model.GameObject;

import java.awt.image.BufferedImage;


public abstract class Enemy extends GameObject{

    public Enemy(double x, double y, BufferedImage style) {
        super(x, y, style);
        setFalling(false);
        setJumping(false);
    }
}
