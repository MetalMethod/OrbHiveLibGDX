package com.metalmethodd.orbhive;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;

import static com.metalmethodd.orbhive.Constants.*;

public class Player {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private int height;
    private int width;

    private Rectangle boundingRectangle;

    private EntityState currentState;
    private int lifes;
    private boolean isPlayerMoving;

    public enum EntityState {
        FULL,
        MID,
        LAST,
        DEAD,
        NEUTRAL
    }

    public Player(Vector2 position) {
            this.width = Constants.PLAYER_WIDTH;
            this.height = Constants.PLAYER_HEIGHT;

            this.position = position;
            velocity = new Vector2(0, 0);

            acceleration = new Vector2(Constants.wind, Constants.gravity);

            boundingRectangle = new Rectangle(position.x, position.y, width, height);

            currentState = EntityState.FULL;
            lifes = Constants.initialEntityLives;
    }


    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void stop() {
        velocity.x = 0;
        velocity.y = 0;
    }

    /**
     * must be called every frame
     */


    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));

        boundingRectangle.set(getPosition().x, getPosition().y, width, height);

        updateLifes();

        detectWalls();

    }

    private void updateLifes() {
        if (lifes < 1) {
            lifes = Constants.initialEntityLives;
        }
    }

    private void setPosition(Vector2 position) {
        this.position = position;
    }

    private void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }


    public void moveUp() {
        setVelocity((Vector2) getVelocity().add(0, -Constants.movementVelocity));
        isPlayerMoving = true;
    }

    public void moveForward() {
        setVelocity((Vector2) getVelocity().add(Constants.movementVelocity, 0));
        isPlayerMoving = true;
    }

    public void moveDown() {
        setVelocity((Vector2) getVelocity().add(0, Constants.movementVelocity));
        isPlayerMoving = true;
    }

    public void moveBack() {
        setVelocity((Vector2) getVelocity().add(-Constants.movementVelocity, 0));
        isPlayerMoving = true;
    }

    public void stopPlayer() {
        stopMovePlayerY();
        stopMovePlayerX();
    }

    public void stopMovePlayerY() {
        setVelocity((Vector2) getVelocity().set(getVelocity().x, 0));
        isPlayerMoving = false;
    }

    public void stopMovePlayerX() {
        setVelocity((Vector2) getVelocity().set(0, getVelocity().y));
        isPlayerMoving = false;
    }


    public boolean getIsPlayermoving(){
        return  isPlayerMoving;
    }


    public void takeHit(int hitAmount) {
        velocity.set(getVelocity().add(-hitAmount, 0));

        lifes--;

        System.out.println("hit. remaining lifes: " + String.valueOf(lifes));

    }

    public EntityState getState() {
        switch (lifes) {
            case 3:
                currentState = EntityState.FULL;
                break;
            case 2:
                currentState = EntityState.MID;
                break;
            case 1:
                currentState = EntityState.LAST;
                break;
            case 0:
                currentState = EntityState.DEAD;
                break;
        }
        return currentState;
    }

    public void detectWalls() {
        //left
        if (getPosition().x < screenPaddingLeft) {
            setPosition(getPosition().set(screenPaddingLeft, getPosition().y));
        }
        //right
        if (getPosition().x > screenWidth - screenPaddingRight) {
            setPosition(getPosition().set(screenWidth - screenPaddingRight, getPosition().y));
        }
        //top
        if (getPosition().y < screenPaddingTop) {
            setPosition(getPosition().set(getPosition().x, screenPaddingTop));
        }
        //down
        if (getPosition().y > screenHeight - screenPaddingBottom) {
            setPosition(getPosition().set(getPosition().x, screenHeight - screenPaddingBottom));
        }
    }





    public void shoot() {
        System.out.println("shoot");
    }


}

