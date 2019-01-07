package com.metalmethodd.orbhive;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Wasp {

    private Vector2 position;

    private int speed = Constants.WASP_ENEMY_SPEED;

    private float height;
    private float width;

    private Rectangle boundingRectangle;

    public Wasp(Vector2 position) {
        this.height = Constants.WASP_ENEMY_HEIGHT;
        this.width = Constants.WASP_WIDTH;
        this.position = position;
        this.boundingRectangle = new Rectangle();
    }

    public void update(float delta) {
        position.x -= speed;
        boundingRectangle.x = position.x;
        boundingRectangle.y = position.y;
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
