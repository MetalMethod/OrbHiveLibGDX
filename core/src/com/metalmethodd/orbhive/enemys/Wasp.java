package com.metalmethodd.orbhive.enemys;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;

import static com.metalmethodd.orbhive.Constants.WASP_ENEMY_SPEED;
import static com.metalmethodd.orbhive.Constants.WASP_HEIGHT;
import static com.metalmethodd.orbhive.Constants.WASP_WIDTH;

public class Wasp {

    private Vector2 position;

    private int speed = WASP_ENEMY_SPEED;

    private float height;
    private float width;

    private Rectangle boundingRectangle;

    public Wasp(Vector2 position) {
        height = Constants.WASP_HEIGHT;
        width = WASP_WIDTH;
        this.position = position;
        boundingRectangle = new Rectangle(position.x, position.y, WASP_WIDTH, WASP_HEIGHT);
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
