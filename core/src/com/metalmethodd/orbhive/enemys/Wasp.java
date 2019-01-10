package com.metalmethodd.orbhive.enemys;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;

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
