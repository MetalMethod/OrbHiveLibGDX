package com.metalmethodd.orbhive;

public class Constants {

    // GRAPHICS
    public static final int GAME_WIDTH = 455;
    public static final int GAME_HEIGHT = 256;

    public static final int PLAYER_WIDTH = 40;
    public static final int PLAYER_HEIGHT = 25;
    public static final int PLAYER_SIZE = 40;
    public static final int DRAW_PLAYER_HEIGHT_FIX = 8;
    public static final float PLAYER_HIT_ANIMATION_DURATION = 50;

    public static final int SCREEN_PADDING_LEFT = 5;
    public static final int SCREEN_PADDING_RIGHT = 45;
    public static final int SCREEN_PADDING_TOP = 0;
    public static final int SCREEN_PADDING_BOTTOM = 36;

    public static final int BULLET_HEIGHT = 4;
    public static final int BULLET_WIDTH = 7;
    public static final int BULLET_SPEED = 9 ;

    public static final int SIMPLE_ENEMY_WIDTH = 16;
    public static final int SIMPLE_ENEMY_HEIGHT = 16;
    public static final int SIMPLE_ENEMY_SPEED = 2;

    public static final int WASP_WIDTH = 16;
    public static final int WASP_HEIGHT = 16;
    public static final int WASP_DEFAULT_SPEED = 3;
    public static final int WASP_MINIMUM_SPEED = 3;
    public static final int WASP_MAXIMUM_SPEED = 5;

    // PHYSICS
    public static final int PLAYER_VELOCITY = 180;
    public static final int PLAYER_GRAVITY = 5;
    public static final int PLAYER_WIND = -3;

    public static final int movementAcceleration = 50;

    public static int PLAYER_HIT_ACCELERATION = 70;
    public static int enemyHitAcceleration = -100;


    // GAME LOGIC
    public static final int INITIAL_GAMEOBJECT_SPEED = 0;
    public static int INITIAL_PLAYER_LIVES = 3;
    public static final float INITIAL_PLAYER_X = 100f;
    public static final float INITIAL_PLAYER_Y = 100f;
}
