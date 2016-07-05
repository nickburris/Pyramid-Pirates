package com.pirates.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Nick on 3/19/2016.
 */
public class Menu {
    Texture[] textures;

    private int animationStep;

    public Menu(){
        textures = new Texture[4];
        for(int i = 0; i < 4; i++){
            textures[i] = new Texture("images/splash/splashscreen_v1_test_" + (i+1) + ".png");
        }
        animationStep = 0;
    }

    public Texture getTexture(){
        animationStep++;
        if(animationStep == 60) animationStep = 0;
        return textures[animationStep/15];
    }
}
