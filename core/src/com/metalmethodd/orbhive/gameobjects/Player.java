package com.metalmethodd.orbhive.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.metalmethodd.orbhive.Constants;

import static com.metalmethodd.orbhive.Constants.*;

public class Player extends AbstractGameObject {
    private Array<Bullet> bullets;
    private EntityState currentState;
    private int lifes;
    private boolean isPlayerMoving;

    private boolean gameStarted;
    private boolean playerHitAnimationState;

    private int playerShootingTime;
    private boolean isPlayerShooting;

    //auto shooting in intervals
    private float elapsedTimeLastShot;
    private float intervalShooting;


    public Player(Vector2 position) {
        super(position, PLAYER_WIDTH, PLAYER_HEIGHT);
        init();

        acceleration = new Vector2(Constants.PLAYER_WIND, Constants.PLAYER_GRAVITY);

        this.bullets = new Array<Bullet>();

        currentState = EntityState.FULL;
        lifes = Constants.INITIAL_PLAYER_LIVES;

        playerShootingTime = 0;
        isPlayerShooting = false;
        playerHitAnimationState = false;
        gameStarted = false;

        elapsedTimeLastShot = 0;
        intervalShooting = Constants.AUTOFIRE_INTERVAL;
    }

    public enum EntityState {
        FULL,
        MID,
        LAST,
        DEAD,
        NEUTRAL
    }

    public void stop() {
        velocity.x = 0;
        velocity.y = 0;
        isPlayerMoving = false;
    }

    /**
     * Must always call updateBoundingRectangle()
     * in the end after of all  methods calls
     */
    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));

        startGameAnimation();
        if (gameStarted) {
            detectWalls();
        }

        updatePlayerShootingAnimation();
        updateAutoFire(delta);

        //must call updateBoundingRectangle() after all methods
        updateBoundingRectangle();
    }

    private void updateAutoFire(float delta) {
        if(elapsedTimeLastShot > intervalShooting){
            shoot();
            elapsedTimeLastShot = 0;
            return;
        }
        elapsedTimeLastShot += delta;
    }

    private void startGameAnimation() {
        // the condition && gameStarted == false
        // means only run animation if it hasn't run before
        // fixed bug that animation kept running when player reached left wall
        if (position.x < Constants.PLAYER_INITIAL_X && !gameStarted) {
            position.x += Constants.SPEED_PLAYER_ANIM_START;
        } else {
            gameStarted = true;
        }

    }

    public void reset(){
        stop();
        gameStarted = false;
        startGameAnimation();
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

    ///// TOUCH
    public void moveUpDrag(float distance) {
        setVelocity(getVelocity().add(0, -PLAYER_DRAG_VELOCITY * distance));
        isPlayerMoving = true;
    }

    public void moveForwardDrag(float distance) {
        setVelocity(getVelocity().add(PLAYER_DRAG_VELOCITY * distance, 0));
        isPlayerMoving = true;
    }

    public void moveDownDrag(float distance) {
        setVelocity(getVelocity().add(0, PLAYER_DRAG_VELOCITY * distance));
        isPlayerMoving = true;
    }

    public void moveBackDrag(float distance) {
        setVelocity(getVelocity().add(-PLAYER_DRAG_VELOCITY * distance, 0));
        isPlayerMoving = true;
    }


    public void stopPlayer() {
        stopMovePlayerY();
        stopMovePlayerX();
        isPlayerMoving = false;
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
            lifes--;
            playerHitAnimationState = true;
        }
    }

    public boolean isPlayerHit() {
        return playerHitAnimationState;
    }

    public void setPlayerHit(boolean value) {
        playerHitAnimationState = value;
    }

    public boolean isPlayerShooting() {
        return playerShootingTime > 0;
    }

    private void updatePlayerShootingAnimation() {
        playerShootingTime--;
        if (playerShootingTime < 0) playerShootingTime = 0;
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
        //int x = PLAYER_WIDTH;
        //int y = PLAYER_HEIGHT -4;

        int x = PLAYER_WIDTH;
        int y = PLAYER_HEIGHT -7;


        bullets.add(new Bullet(new Vector2(getPosition().x + x, getPosition().y + y)));
        playerShootingTime = Constants.PLAYER_SHOOT_ANIMATION_DURATION;
    }

    public boolean getGameStarted(){
        return gameStarted;
    }

}

