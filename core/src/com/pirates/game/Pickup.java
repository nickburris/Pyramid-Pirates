package com.pirates.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Ryan on 2016-03-20.
 */
public class Pickup extends Sprite {

    //private variables
    private PickupType type;

    //animated texture
    Texture[] animation;
    private int animationStep;

    //enum
    public enum PickupType {
        COIN(175,175);

        private final float width;
        private final float height;
        PickupType(float width, float height) {
            this.width = width;
            this.height = height;
        }
    }

    //constructor
    public Pickup(float px, float py, Texture texture, PickupType type) {
        super(px, py, texture);
        this.type = type;

        if(type == PickupType.COIN){
            animation = new Texture[5];
            for(int i = 0; i < 5; i++){
                animation[i] = new Texture("images/Coin/Coin" + (i+1) + ".png");
            }
            animationStep = 0;
        }

        setWidth(type.width);
        setHeight(type.height);
    }

    //getters and setters
    public PickupType getType() {return type;}

    public Texture getTexture(){
        if(type == PickupType.COIN){
            animationStep++;
            if(animationStep == animation.length*10) animationStep = 0;
            return animation[animationStep/10];
        }else{
            return super.getTexture();
        }
    }

    //move method
    public void move() {
        px -= PyramidPyrates.SPEED;
    }
}
