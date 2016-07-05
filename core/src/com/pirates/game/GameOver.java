package com.pirates.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Amelia on 2016-03-20.
 */
public class GameOver {
    Texture[] textures;

    private int animationStep;

    public GameOver(){
        textures = new Texture[6];
        for(int i = 0; i < 6; i++){
            textures[i] = new Texture("images/gameover/G0" + (i+1) + ".png");
        }
        animationStep = 0;
    }

    public Texture getTexture(){
        animationStep++;
        if(animationStep == 90) animationStep = 0;
        return textures[animationStep/15];
    }
}