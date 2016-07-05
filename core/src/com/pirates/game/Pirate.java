package com.pirates.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ListIterator;

/**
 * Created by Nick on 3/19/2016.
 */
public class Pirate extends Sprite {
    //private constants
    private static final int INIT_X = (int)(PyramidPyrates.SCREEN_WIDTH/3);
    private static final int INIT_Y = (int)(PyramidPyrates.SCREEN_WIDTH/4);

    //private variables
    private float yVel;
    private final float yVelMax = 35*PyramidPyrates.YSCALE;
    private boolean jumpingUp = false;
    private int jumpEnergy = 5;

    private boolean dying = false;

    //getters and setters
    public float getYVel(){return yVel;}
    public void setYVel(float yVel){this.yVel = yVel;}

    Texture[] animation = new Texture[3];
    public int animationStep = 0;

    //constructor
    public Pirate() {
        super(INIT_X, INIT_Y, new Texture("images/pirate/pirate_run1.png"));
        yVel = 0;
        animation[0] = new Texture("images/pirate/pirate_run1.png"); //stationary
        animation[1] = new Texture("images/pirate/pirate_run2.png");
        animation[2] = new Texture("images/pirate/pirate_run3.png");
        setWidth((int)(animation[0].getWidth() * 3.5));
        setHeight((int) (animation[0].getHeight() * 3.5));
        dying = false;
    }


    public void jump(boolean touching) {
        if(touching) {
            if (!jumpingUp) {
                ListIterator<Sprite> litr = PyramidPyrates.allSprites.listIterator();
                while (litr.hasNext()) {
                    Sprite curr = litr.next();
                    if (curr instanceof Block && Math.abs(curr.getY() + curr.getHeight() - this.getY()) < 2) { // this.getY() <= curr.getY() + curr.getHeight() && curr.getY() <= this.getY() + this.getHeight()){
                        jumpEnergy = 6;
                        yVel = 15.0f * PyramidPyrates.YSCALE;
                        jumpingUp = true;
                        break;
                    }
                }
            } else {
                if (yVel < yVelMax) {
                    if(jumpEnergy > 0) {
                        jumpEnergy--;
                        if(jumpEnergy%2 == 0) yVel += 6.0f * PyramidPyrates.YSCALE;
                    }
                } else {
                    jumpingUp = false;
                }
            }
        }else{
            jumpingUp = false;
        }
    }

    //move pirate, checking for collisions
    public void move() {
        yVel += PyramidPyrates.GRAVITY;
        setY(getY() + yVel);

        if (px < INIT_X) px+=3*PyramidPyrates.XSCALE;

        //check for collisions
        ListIterator<Sprite> litr = PyramidPyrates.allSprites.listIterator();
        while (litr.hasNext()) {
            Sprite curr = litr.next();
            //block collision
            if (curr instanceof Block) {

                boolean[] touching = collides(curr);
                boolean touchingLeft = touching[0];
                boolean touchingRight = touching[1];
                boolean touchingTop = touching[2];
                boolean touchingBottom = touching[3];

                if(touchingLeft || touchingRight || touchingTop || touchingBottom){
                    if(((Block)curr).getType() == Block.BlockType.SPIKE_DOWN || ((Block)curr).getType() == Block.BlockType.SPIKE_UP){
                        PyramidPyrates.audio.playerDeath();
                        PyramidPyrates.gameOver();
                        return;
                    }
                }


                //ignore touchingLeft
                if (touchingRight) {setX(curr.px-getWidth());}
                else if (touchingTop) {setY(curr.py-getHeight()); yVel = 0;}
                else if (touchingBottom) {setY(curr.py+curr.height); yVel = 0;}

            }

            //mob collision
            else if (curr instanceof Mob) {
                boolean[] collisions = collides(curr);
                if (collisions[0] || collisions[1] || collisions[2] || collisions[3]) {
                    PyramidPyrates.audio.playerDeath();
                    PyramidPyrates.gameOver();
                }
            }

            //pickup collision
            else if(curr instanceof Pickup){
                //check if colliding with a pickup
                boolean[] collisions = collides(curr);
                if(collisions[0] || collisions[1] || collisions[2] || collisions[3]){
                    if(((Pickup) curr).getType() == Pickup.PickupType.COIN){
                        curr.toBeRemoved = true;
                        PyramidPyrates.increaseScore(100);
                    }
                }
            }
        }
    }

    //fires a bullet straight forward from the current location.
    public void fire() {
        PyramidPyrates.audio.gunshot();
        PyramidPyrates.allSprites.add(new Bullet(px+width/2, py+height/2, new Texture("images/bullet.png")));
    }

    public void die(){
        setX(300*PyramidPyrates.XSCALE);
        setY(455*PyramidPyrates.YSCALE);
        setWidth(getWidth() * 4);
        setHeight(getHeight() * 4);
        dying = true;
        animation = new Texture[11];
        animationStep = 0;
        for(int i = 0; i < animation.length; i++){
            animation[i] = new Texture("images/death/pirate_die" + (i+1) + ".png");
        }
    }

    public Texture getTexture(){
        if(!dying) {
            animationStep++;
            if (animationStep == 21) animationStep = 0;

            return animation[animationStep / 7];
        }else{
            if(animationStep < 100) animationStep++;

            if(animationStep < 35) {
                return animation[0];
            }else {
                return animation[(animationStep - 35) / 6];
            }
        }
    }
}
