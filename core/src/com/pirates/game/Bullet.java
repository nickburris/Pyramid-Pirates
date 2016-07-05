package com.pirates.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ListIterator;

/**
 * Created by Amelia on 2016-03-19.
 */
public class Bullet extends Sprite {

    //private constants
    public static final float SPEED = 55*PyramidPyrates.XSCALE;
    public static final float BULLET_SIZE = 12*PyramidPyrates.XSCALE;

    //constructor
    public Bullet(float px, float py, Texture texture) {
        super(px, py, texture);
        setWidth(BULLET_SIZE);
        setHeight(BULLET_SIZE);
    }

    public void move() {
        px += SPEED;

        //check if off screen
        if (px > PyramidPyrates.SCREEN_WIDTH) {
            this.toBeRemoved = true;
        }

        //check collisions
        boolean collided = false;
        ListIterator<Sprite> litr = PyramidPyrates.allSprites.listIterator();
        while (litr.hasNext()) {
            Sprite curr = litr.next();
            if (!(curr instanceof Bullet)) {
                boolean[] touching = collides(curr);
                if (touching[0] || touching[1] || touching[2] || touching[3]) {
                    if (curr instanceof Block) {
                        this.toBeRemoved = true;
                        break;
                    } else if (curr instanceof Mob) {
                        PyramidPyrates.audio.mobDeath();
                        PyramidPyrates.increaseScore(10);
                        curr.toBeRemoved = true;
                        this.toBeRemoved = true;
                        break;
                    }
                }
            }
        }
    }
}
