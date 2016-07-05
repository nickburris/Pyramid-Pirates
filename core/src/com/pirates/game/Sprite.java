package com.pirates.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Amelia on 2016-03-19.
 */
public abstract class Sprite {

    //private variables
    protected float px, py;
    protected float width, height;
    private Texture texture;
    public boolean toBeRemoved = false;

    //constructor
    public Sprite(float px, float py, Texture texture) {
        this.px = px;
        this.py = py;
        this.texture = texture;
        //width = texture.getWidth() * (PyramidPyrates.SCREEN_WIDTH/1920);
        //height = texture.getHeight() * (PyramidPyrates.SCREEN_HEIGHT/1080);
    }

    //getters and setters
    public Texture getTexture(){ return texture; }
    public int getX(){ return (int)px; }
    public int getY(){ return (int)py; }
    public void setX(float px){ this.px = px; }
    public void setY(float py){ this.py = py; }
    //get and set methods for scaled widths and heights of images
    public int getWidth(){ return (int)width; }
    public int getHeight(){ return (int)height; }
    public void setWidth(float width){ this.width = width * PyramidPyrates.XSCALE;}
    public void setHeight(float height){ this.height = height * PyramidPyrates.YSCALE; }

    //abstract methods
    public abstract void move();

    //inherited methods
    //returns [touchingLeft, touchingRight, touchingTop, touchingBottom]
    public boolean[] collides(Sprite s) {
        boolean[] result = new boolean[4];

        int offset = 15;

        result[0] = contains(s.px, s.py, s.width, s.height, getX(), getY()+getHeight()-offset) || contains(s.px, s.py, s.width, s.height, getX(), getY()+offset);
        result[1] = //contains(s.px, s.py, s.width, s.height, getX()+getWidth(), getY()+getHeight()-offset) || contains(s.px, s.py, s.width, s.height, getX()+getWidth(), getY()+offset)
                    contains(s.px, s.py, s.width, s.height, getX() + getWidth(), getY()+(getHeight()/2));
        result[2] = contains(s.px, s.py, s.width, s.height, getX()+offset, getY()+getHeight()) || contains(s.px, s.py, s.width, s.height, getX()+getWidth()-offset, getY()+getHeight());
        result[3] = contains(s.px, s.py, s.width, s.height, getX()+(getWidth()/2), getY()); //contains(s.px, s.py, s.width, s.height, getX()+offset, getY()) || contains(s.px, s.py, s.width, s.height, getX()+getWidth()-offset, getY());

        return result;
    }

    //private helper methods
    //returns true if (x2, y2) within rectangle defined by first four parameters
    private boolean contains (float x1, float y1, float width, float height, float x2, float y2) {
        if ((x2 > x1 && x2 < x1+width) && (y2 > y1 && y2 < y1+height)) {
            return true;
        } else {
            return false;
        }
    }
}
