package com.pirates.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Amelia on 2016-03-19.
 */
public class Generation {

    //structures
    //0: nothing, 1: block, 2: rat, 3: mummy, 4: anubis, 5: spike-up, 6: spike-down, 7: coin
    private static int[][][] maps  = {
        {{0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 1, 1, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 3, 0, 0},
        {0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 0, 2, 0, 3, 5, 5, 0, 0, 4}},

        {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 1, 1, 1, 2, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 1, 1, 0, 7, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
        {0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 1, 5, 2, 2}},

        {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 2, 0, 1, 1, 0, 0},
        {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
        {1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 0, 0, 0, 0, 0, 0, 3, 3, 2}},

        {{0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 1, 1, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 0, 0, 0, 3, 0, 0, 0, 0, 2},
        {1, 1, 1, 1, 1, 1, 0, 2, 0, 1},
        {0, 0, 0, 0, 0, 1, 1, 1, 1, 1}},

        {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 1, 1, 1, 4, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 7, 7, 3, 0, 0, 0, 0, 0, 0},
        {1, 1, 1, 1, 4, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
        {0, 4, 0, 0, 0, 0, 1, 1, 0, 0}},

        {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 3, 1, 0, 0},
        {0, 0, 0, 7, 1, 1, 1, 0, 0, 0},
        {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
        {1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 0, 2, 0, 0, 0, 0, 0, 0, 2}},

        {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 7, 0, 0, 0, 0, 0, 7, 0}},

        {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 2, 2, 0, 0, 4, 0}},

        {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 3, 3, 7, 0, 0},
        {0, 0, 0, 1, 1, 1, 1, 1, 0, 0}},

        {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 4, 3, 0, 0}},

        {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 7, 0, 7, 0, 7, 0, 0, 0},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0}},

        {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 7, 7, 7},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 7, 7, 7, 7, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 4, 4, 4, 4, 4, 4, 4, 4}},

        {{0, 0, 0, 0, 7, 7, 0, 0, 0, 0},
        {0, 0, 0, 0, 7, 7, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}},
    };

    //private constants
    private static final int NUM_WALL_POS = (int)(PyramidPyrates.SCREEN_HEIGHT - 2*PyramidPyrates.BLOCK_WIDTH) / (PyramidPyrates.BLOCK_WIDTH);
    private static final Texture wallTex = new Texture("images/wall.png");
    private static final Texture ratTex = new Texture("images/Rat.png");
    private static final Texture mummyTex = new Texture("images/Mummy.png");
    private static final Texture anubisTex = new Texture("images/Anubis Sprite.png");
    private static final Texture spikeUpTex = new Texture("images/Spike Trap (Up).png");
    private static final Texture spikeDownTex = new Texture("images/Spike Trap (Down).png");
    private static final Texture coinTex = new Texture("images/Coin.png");
    private static final float structWidth = PyramidPyrates.BLOCK_WIDTH * maps[0][0].length;

    //private variables
    private static Block lastTop;
    private static Block lastBottom;
    private static int counter;

    //initial generation: top/bottom wall rows
    public static void initial() {
        //top row
        for (int i = 0; i <= PyramidPyrates.SCREEN_WIDTH + PyramidPyrates.BLOCK_WIDTH; i += PyramidPyrates.BLOCK_WIDTH) {
            Block b = new Block(i, PyramidPyrates.SCREEN_HEIGHT-PyramidPyrates.BLOCK_WIDTH, wallTex, Block.BlockType.WALL);
            PyramidPyrates.allSprites.add(b);
            lastTop = b;
        }

        //bottom row
        for (int i = 0; i <= PyramidPyrates.SCREEN_WIDTH + PyramidPyrates.BLOCK_WIDTH; i += PyramidPyrates.BLOCK_WIDTH) {
            Block b = new Block(i, 0, wallTex, Block.BlockType.WALL);
            PyramidPyrates.allSprites.add(b);
            lastBottom = b;
        }

        counter = 0;
    }

    //runtime generation: to be called during step()
    public static void step() {
        //add top/bottom wall rows
        if (lastTop.getX()+PyramidPyrates.BLOCK_WIDTH <= PyramidPyrates.SCREEN_WIDTH) {
            Block b = new Block(lastTop.getX()+PyramidPyrates.BLOCK_WIDTH, PyramidPyrates.SCREEN_HEIGHT-PyramidPyrates.BLOCK_WIDTH, wallTex, Block.BlockType.WALL);
            PyramidPyrates.allSprites.add(b);
            lastTop = b;
        }
        if (lastBottom.getX()+PyramidPyrates.BLOCK_WIDTH <= PyramidPyrates.SCREEN_WIDTH) {
            Block b = new Block(lastBottom.getX()+PyramidPyrates.BLOCK_WIDTH, 0, wallTex, Block.BlockType.WALL);
            PyramidPyrates.allSprites.add(b);
            lastBottom = b;
        }

        //spawn blocks
//        if (Math.random() < 0.03) {
//            int y = PyramidPyrates.BLOCK_WIDTH + randomWithRange(0,NUM_WALL_POS)*PyramidPyrates.BLOCK_WIDTH;
//            PyramidPyrates.allSprites.add(new Block(PyramidPyrates.SCREEN_WIDTH, y, new Texture(TEX_PATH), Block.BlockType.WALL));
//        }

        if (PyramidPyrates.state == PyramidPyrates.GameState.GAME && counter > 1.5*structWidth && Math.random() < 0.3) {
            //generate random structure
            int i = randomWithRange(0, maps.length);
            int[][] struct = maps[i];

            for (int y = 0; y < struct.length; y++) {
                for (int x = 0; x < struct[y].length; x++) {
                    switch (struct[y][x]) {
                        case 1:
                            PyramidPyrates.allSprites.add(new Block(PyramidPyrates.SCREEN_WIDTH+(x*PyramidPyrates.BLOCK_WIDTH),
                                    PyramidPyrates.SCREEN_HEIGHT-2*PyramidPyrates.BLOCK_WIDTH-(y*PyramidPyrates.BLOCK_WIDTH),
                                    wallTex,
                                    Block.BlockType.WALL));
                            break;
                        case 2:
                            PyramidPyrates.allSprites.add(new Mob(PyramidPyrates.SCREEN_WIDTH+(x*PyramidPyrates.BLOCK_WIDTH),
                                    PyramidPyrates.SCREEN_HEIGHT-2*PyramidPyrates.BLOCK_WIDTH-(y*PyramidPyrates.BLOCK_WIDTH),
                                    ratTex,
                                    Mob.MobType.RAT));
                            break;
                        case 3:
                            PyramidPyrates.allSprites.add(new Mob(PyramidPyrates.SCREEN_WIDTH+(x*PyramidPyrates.BLOCK_WIDTH),
                                    PyramidPyrates.SCREEN_HEIGHT-2*PyramidPyrates.BLOCK_WIDTH-(y*PyramidPyrates.BLOCK_WIDTH),
                                    mummyTex,
                                    Mob.MobType.MUMMY));
                            break;
                        case 4:
                            PyramidPyrates.allSprites.add(new Mob(PyramidPyrates.SCREEN_WIDTH+(x*PyramidPyrates.BLOCK_WIDTH),
                                    PyramidPyrates.SCREEN_HEIGHT-2*PyramidPyrates.BLOCK_WIDTH-(y*PyramidPyrates.BLOCK_WIDTH),
                                    anubisTex,
                                    Mob.MobType.ANUBIS));
                            break;
                        case 5:
                            PyramidPyrates.allSprites.add(new Block(PyramidPyrates.SCREEN_WIDTH+(x*PyramidPyrates.BLOCK_WIDTH),
                                    PyramidPyrates.SCREEN_HEIGHT-2*PyramidPyrates.BLOCK_WIDTH-(y*PyramidPyrates.BLOCK_WIDTH),
                                    spikeUpTex,
                                    Block.BlockType.SPIKE_UP));
                            break;
                        case 6:
                            PyramidPyrates.allSprites.add(new Block(PyramidPyrates.SCREEN_WIDTH+(x*PyramidPyrates.BLOCK_WIDTH),
                                    PyramidPyrates.SCREEN_HEIGHT-2*PyramidPyrates.BLOCK_WIDTH-(y*PyramidPyrates.BLOCK_WIDTH),
                                    spikeDownTex,
                                    Block.BlockType.SPIKE_DOWN));
                            break;
                        case 7:
                            PyramidPyrates.allSprites.add(new Pickup(PyramidPyrates.SCREEN_WIDTH+(x*PyramidPyrates.BLOCK_WIDTH),
                                    PyramidPyrates.SCREEN_HEIGHT-2*PyramidPyrates.BLOCK_WIDTH-(y*PyramidPyrates.BLOCK_WIDTH),
                                    coinTex,
                                    Pickup.PickupType.COIN));
                            break;
                    }
                }
            }
            counter = 0;
        }
        counter += PyramidPyrates.SPEED;
    }

    //private helper methods
    //range [min, max)
    private static int randomWithRange(int min, int max) {
        return (int) (Math.random() * (max-min)) + min;
    }
}
