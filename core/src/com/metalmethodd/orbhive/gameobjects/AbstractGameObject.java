package com.metalmethodd.orbhive.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;

public abstract class AbstractGameObject {

    protected Vector2 position;

    protected float height;
    protected float width;

    protected int speed;

    private Rectangle boundingRectangle;

    public AbstractGameObject(Vector2 position, int width, int height){
        this.height = height;
        this.width = width;
        this.position = position;
        speed = Constants.INITIAL_GAMEOBJECT_SPEED;
        boundingRectangle = new Rectangle();
    }

    public void init(){
        boundingRectangle.set(position.x, position.y, width, height);
    }

    public void update() {
        position.x -= speed;
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

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }
}
