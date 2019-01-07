package com.metalmethodd.orbhive;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.metalmethodd.orbhive.Constants.BULLET_SPEED;
import static com.metalmethodd.orbhive.Constants.PLAYER_HEIGHT;
import static com.metalmethodd.orbhive.Constants.PLAYER_WIDTH;

public class Bullet {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float height;
    private float width;


    private Rectangle boundingRectangle;

    public Bullet(Vector2 position) {
        width = Constants.BULLET_WIDTH;
        height = Constants.BULLET_HEIGHT;

        this.position = position;
        fixPositionOnPlayerGun();

        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);

        boundingRectangle = new Rectangle();
    }

    private void fixPositionOnPlayerGun() {
        position.x += PLAYER_WIDTH;
        position.y += PLAYER_HEIGHT - 7;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public void update(float delta) {
        position.x += BULLET_SPEED;
        updateBoundingRectangle();
    }

    private void updateBoundingRectangle() {
        boundingRectangle.setPosition(getPosition());
    }

}