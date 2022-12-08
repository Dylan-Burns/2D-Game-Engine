/**
 * the purpose of this class is to create the fireball objects to render to the map
 * when mario has collided with the fire flower power-up and the power-up time limit
 * has not ended
 */

package model.hero;


import model.GameObject;

import java.awt.image.BufferedImage;

public class Fireball extends GameObject {

    public Fireball(double x, double y, BufferedImage style, boolean toRight) {
        super(x, y, style);
        setDimension(24, 24);
        setFalling(false);
        setJumping(false);
        setVelX(10);

        if(!toRight)
            setVelX(-5);
    }
}
