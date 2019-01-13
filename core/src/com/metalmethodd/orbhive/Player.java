package com.metalmethodd.orbhive;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import static com.metalmethodd.orbhive.Constants.*;

public class Player {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Array<Bullet> bullets;
    private int height;
    private int width;
    private Rectangle boundingRectangle;
    private EntityState currentState;
    private int lifes;
    private boolean isPlayerMoving;

    private float playerHitTime;

    public Player(Vector2 position) {
        this.width = Constants.PLAYER_WIDTH;
        this.height = Constants.PLAYER_HEIGHT;

        this.bullets = new Array<Bullet>();

        this.position = position;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(Constants.PLAYER_WIND, Constants.PLAYER_GRAVITY);

        boundingRectangle = new Rectangle(getPosition().x, getPosition().y, width, height);

        currentState = EntityState.FULL;
        lifes = Constants.INITIAL_PLAYER_LIVES;

        playerHitTime = 0;
    }

    public enum EntityState {
        FULL,
        MID,
        LAST,
        DEAD,
        NEUTRAL

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
        detectWalls();
        updateBoundingRectangle();
        updateHitAnimation();
    }

    private void updateBoundingRectangle() {
        boundingRectangle.setPosition(getPosition());
    }

    private void updateLifes() {
        if (lifes < 1) {
            lifes = Constants.INITIAL_PLAYER_LIVES;
        }
    }

    private void setPosition(Vector2 position) {
        this.position = position;
    }

    private void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }


    public void moveUp() {
        setVelocity(getVelocity().add(0, -Constants.PLAYER_VELOCITY));
        isPlayerMoving = true;
    }

    public void moveForward() {
        setVelocity(getVelocity().add(Constants.PLAYER_VELOCITY, 0));
        isPlayerMoving = true;
    }

    public void moveDown() {
        setVelocity(getVelocity().add(0, Constants.PLAYER_VELOCITY));
        isPlayerMoving = true;
    }

    public void moveBack() {
        setVelocity(getVelocity().add(-Constants.PLAYER_VELOCITY, 0));
        isPlayerMoving = true;
    }

    public void stopPlayer() {
        stopMovePlayerY();
        stopMovePlayerX();
    }

    public void stopMovePlayerY() {
        setVelocity(getVelocity().set(getVelocity().x, 0));
        isPlayerMoving = false;
    }

    public void stopMovePlayerX() {
        setVelocity(getVelocity().set(0, getVelocity().y));
        isPlayerMoving = false;
    }

    public boolean getIsPlayermoving() {
        return isPlayerMoving;
    }

    public void takeHit(int hitAmount) {
        /** if hit animation is playing
         *the player don't get hit
         */
        if (!isPlayerHit()) {
            velocity.set(getVelocity().add(-hitAmount, 0));
            playerHitTime = PLAYER_HIT_ANIMATION_DURATION;
            lifes--;
        }
    }

    public boolean isPlayerHit() {
        return playerHitTime > 0;
    }

    private void updateHitAnimation() {
        playerHitTime--;
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
        if (getPosition().x < SCREEN_PADDING_LEFT) {
            setPosition(getPosition().set(SCREEN_PADDING_LEFT, getPosition().y));
        }
        //right
        if (getPosition().x > GAME_WIDTH - SCREEN_PADDING_RIGHT) {
            setPosition(getPosition().set(GAME_WIDTH - SCREEN_PADDING_RIGHT, getPosition().y));
        }
        //top
        if (getPosition().y < SCREEN_PADDING_TOP) {
            setPosition(getPosition().set(getPosition().x, SCREEN_PADDING_TOP));
        }
        //down
        if (getPosition().y > GAME_HEIGHT - SCREEN_PADDING_BOTTOM) {
            setPosition(getPosition().set(getPosition().x, GAME_HEIGHT - SCREEN_PADDING_BOTTOM));
        }
    }

    public void shoot() {
        bullets.add(new Bullet(new Vector2(getPosition().x, getPosition().y)));
    }


}

