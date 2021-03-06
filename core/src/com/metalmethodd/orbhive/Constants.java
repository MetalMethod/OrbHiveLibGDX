package com.metalmethodd.orbhive;

public class Constants {


    //COOL EFFECT TO THINK LATER
    // batch.setColor(0,0,0,1f);
    // sets everything to black and white


    // GRAPHICS
    public static final int GAME_WIDTH = 256;
    public static final int GAME_HEIGHT = 256;

    //bg scroll speed right to left
    public static final float BG_LVL_ONE_X_SPEED = 1.5f;

    // #############################
    // TESTING SPEED of animation for debug and dev
    //public static final float BG_LVL_ONE_Y_SPEED = 3f;

    // NORMAL SPEED
//    public static final float BG_LVL_ONE_Y_SPEED = 0.03f;
    public static final float BG_LVL_ONE_Y_SPEED = 0.06f;
    // #############################


    public static final int MOON_BIG_SIZE = 80;

    public static final float CLOUD_ONE_SPEED = BG_LVL_ONE_Y_SPEED * 4f;
    public static final float CLOUD_TWO_SPEED = BG_LVL_ONE_Y_SPEED * 2f;
    public static final float CLOUD_THREE_SPEED = BG_LVL_ONE_Y_SPEED * 2.5f;

    //    public static final int PLAYER_SIZE = 40;
    // public static final int PLAYER_WIDTH = 40;
    // public static final int PLAYER_HEIGHT = 25;
    public static final int PLAYER_SIZE = 20;
    public static final int PLAYER_WIDTH = 20;
    public static final int PLAYER_HEIGHT = 13;

    //    public static final int DRAW_PLAYER_HEIGHT_FIX = 8;
    public static final int DRAW_PLAYER_HEIGHT_FIX = 3;

    public static final int PLAYER_HIT_ACCELERATION = 70;
    public static final int PLAYER_SHOOT_ANIMATION_DURATION = 10;

    public static final int SCREEN_PADDING_LEFT = 5;
    public static final int SCREEN_PADDING_RIGHT = 45;
    public static final int SCREEN_PADDING_TOP = 0;
    public static final int SCREEN_PADDING_BOTTOM = 36;

    public static final int BULLET_HEIGHT = 10;
    public static final int BULLET_WIDTH = 10;
    public static final int BULLET_SPEED = 10;
    public static final int BULLET_CIRCLE_RADIUS = 5;
    public static final int BULLET_CIRCLE_SEGMENTS = 10;


    public static final int WASP_WIDTH = 16;
    public static final int WASP_HEIGHT = 16;
    public static final int WASP_DEFAULT_SPEED = 3;
    public static final int WASP_MINIMUM_SPEED = 3;
    public static final int WASP_MAXIMUM_SPEED = 5;

    public static final int SIMPLE_ENEMY_WIDTH = 16;
    public static final int SIMPLE_ENEMY_HEIGHT = 16;
    public static final int SIMPLE_ENEMY_SPEED = 2;

    public static final int BRAIN_SMALL_WIDTH = 32;
    public static final int BRAIN_SMALL_HEIGHT = 32;
    public static final int BRAIN_SMALL_SPEED = 1;

    // PHYSICS
    public static final int PLAYER_VELOCITY = 180;
    public static final int PLAYER_GRAVITY = 5;
    public static final int PLAYER_WIND = -3;

    // GAME LOGIC
    public static final int INITIAL_GAME_OBJECT_SPEED = 0;
    public static final int INITIAL_PLAYER_LIVES = 3;
    public static final float AUTOFIRE_INTERVAL = 0.5f;


    //inital value
    //    public static final float INITIAL_PLAYER_X = 50f;
    public static final float INITIAL_PLAYER_X = -PLAYER_WIDTH - 20;
    public static final float INITIAL_PLAYER_Y = 100f;

    // TOUCH
    public static final float PLAYER_DRAG_VELOCITY = 0.3f;

    public static final float SPEED_PLAYER_ANIM_START = 2;
    public static final float PLAYER_INITIAL_X = 18;
}
