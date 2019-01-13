package com.metalmethodd.orbhive.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;

public abstract class AbstractGameObject {

    protected Vector2 position;
    protected Vector2 velocity;
    protected Vector2 acceleration;

    protected float height;
    protected float width;

    protected int speed;

    protected Rectangle boundingRectangle;

    public AbstractGameObject(Vector2 position, int width, int height) {
        this.height = height;
        this.width = width;
        this.position = position;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        speed = Constants.INITIAL_GAMEOBJECT_SPEED;
        boundingRectangle = new Rectangle();
    }

    protected void init() {
        boundingRectangle.set(position.x, position.y, width, height);
    }

    protected void updateBoundingRectangle() {
        boundingRectangle.setPosition(position);
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }
}
