package com.metalmethodd.orbhive;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.metalmethodd.orbhive.Constants.BULLET_SPEED;

public class SimpleEnemy {

    private Vector2 position;

    private int speed = Constants.SIMPLE_ENEMY_SPEED;

    private float height;
    private float width;

    private Rectangle boundingRectangle;

    public SimpleEnemy(Vector2 position) {

        this.height = Constants.SIMPLE_ENEMY_HEIGTH;
        this.width = Constants.SIMPLE_ENEMY_WIDTH;
        this.position = position;
        this.boundingRectangle = new Rectangle();

    }


    public void update(float delta) {
        position.x -= 5;
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
