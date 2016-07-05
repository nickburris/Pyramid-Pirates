package com.pirates.game;

import com.badlogic.gdx.graphics.Texture;

import sun.rmi.runtime.Log;

/**
 * Created by Amelia on 2016-03-19.
 */
public class Mob extends Sprite {

    //private variables
    private MobType type;
    private Texture staticTex;
    private Texture[] animation;
    private int animationStep;

    //enum
    public enum MobType {
        RAT (180, 70),
        MUMMY(90, 98),
        ANUBIS(90, 180);

        private final float width;
        private final float height;
        MobType(float width, float height) {
            this.width = width;
            this.height = height;
        }
    }

    //constructor
    public Mob(float px, float py, Texture texture, MobType type) {
        super(px, py, texture);
        staticTex = texture;
        this.type = type;
        setWidth(type.width);
        setHeight(type.height);

        if (type == MobType.MUMMY) {
            animation = new Texture[3];
            for(int i = 0; i < 3; i++){
                animation[i] = new Texture("images/Mummy/mum" + (i+1) + ".png");
            }
            animationStep = 0;
        }else if(type == MobType.RAT){
            animation = new Texture[3];
            for(int i = 0; i < 3; i++){
                animation[i] = new Texture("images/Rat/rat" + (i+1) + ".png");
            }
            animationStep = 0;
        }
    }

    //getters and setters
    public MobType getType() {return type;}

    //move method
    public void move() {
        px -= PyramidPyrates.SPEED;
    }

    @Override
    public Texture getTexture() {
        //return current frame for the animated mobs
        if (type == MobType.MUMMY || type == MobType.RAT) {
            animationStep++;
            if(animationStep == animation.length*10) animationStep = 0;
            return animation[animationStep/10];
        } else {
            return staticTex;
        }
    }
}
