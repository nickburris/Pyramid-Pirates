package com.pirates.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Amelia on 2016-03-19.
 */
public class Block extends Sprite {

    //private variables
    private BlockType type;

    //enum
    public enum BlockType {
        WALL (PyramidPyrates.BLOCK_WIDTH_RAW, PyramidPyrates.BLOCK_WIDTH_RAW),
        SPIKE_UP (PyramidPyrates.BLOCK_WIDTH_RAW, PyramidPyrates.BLOCK_WIDTH_RAW*15/34),
        SPIKE_DOWN (PyramidPyrates.BLOCK_WIDTH_RAW, PyramidPyrates.BLOCK_WIDTH_RAW*15/34);

        private final float width;
        private final float height;
        BlockType(float width, float height) {
            this.width = width;
            this.height = height;
        }
    }

    //constructor
    public Block(float px, float py, Texture texture, BlockType type) {
        super(px, py, texture);
        this.type = type;
        if (type == BlockType.WALL) {
            setWidth(BlockType.WALL.width);
            setHeight(BlockType.WALL.height);
        } else if (type == BlockType.SPIKE_UP || type == BlockType.SPIKE_DOWN) {
            setWidth(BlockType.SPIKE_UP.width);
            setHeight(BlockType.SPIKE_UP.height);
        }
    }

    //getters and setters
    public BlockType getType() {return type;}

    //abstract classes
    public void move() {
        px -= PyramidPyrates.SPEED;
    }
}

