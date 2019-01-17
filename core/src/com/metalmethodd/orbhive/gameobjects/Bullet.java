package com.metalmethodd.orbhive.gameobjects;

import com.badlogic.gdx.math.Vector2;

import static com.metalmethodd.orbhive.Constants.*;

public class Bullet extends AbstractGameObject{

    public Bullet(Vector2 position) {
        super(position, BULLET_WIDTH, BULLET_HEIGHT);
        init();
        speed = BULLET_SPEED;

        fixPositionOnPlayerGun();
    }

    /**
     * Must always call updateBoundingRectangle()
     * in the end after of all  methods calls
     */
    public void update() {
        position.x += speed;

        //must call updateBoundingRectangle() after all methods
        updateBoundingRectangle();
    }

    private void fixPositionOnPlayerGun() {
        position.x += PLAYER_WIDTH + 1;
        position.y += PLAYER_HEIGHT - 4;
    }
}