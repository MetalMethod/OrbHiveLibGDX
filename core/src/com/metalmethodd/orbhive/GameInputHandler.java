package com.metalmethodd.orbhive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.gameobjects.Player;

public class GameInputHandler implements InputProcessor {

    private final Player player;
    private Vector2 touchPos = new Vector2();
    private Vector2 dragPos = new Vector2();
    private float touchRadius = 200f;


    public GameInputHandler(Player player) {
        this.player = player;
    }

    public void init(){
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        //        Gdx.app.log("key pressed: ", String.valueOf(keycode));
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP: {
                player.moveUp();
                break;
            }

            case Input.Keys.D:
            case Input.Keys.RIGHT: {
                player.moveForward();
                break;
            }

            case Input.Keys.S:
            case Input.Keys.DOWN: {
                player.moveDown();
                break;
            }

            case Input.Keys.A:
            case Input.Keys.LEFT: {
                player.moveBack();
                break;
            }

            case Input.Keys.SPACE:
            case Input.Keys.M: {
        //        player.shoot();
                break;
            }
        }
        return true;
    }


    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP: {
                player.stopMovePlayerY();
                break;
            }

            case Input.Keys.D:
            case Input.Keys.RIGHT: {
                player.stopMovePlayerX();
                break;
            }
            case Input.Keys.S:
            case Input.Keys.DOWN: {
                player.stopMovePlayerY();
                break;
            }
            case Input.Keys.A:
            case Input.Keys.LEFT: {
                player.stopMovePlayerX();
                break;
            }
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    

    @Override
    public boolean touchDown(
            int screenX,
            int screenY,
            int pointer,
            int button) {
        touchPos.set(screenX, Gdx.graphics.getHeight() - screenY);

        // player.shoot();

        return true;
    }

    /**
     * Try to detect direction
     if (distance <= touchRadius){
     //  * gives you a 'natural' angle
     float angle =
     MathUtils.atan2(
     touchPos.x - dragPos.x, dragPos.y - touchPos.y)
     * MathUtils.radiansToDegrees + 90;
     if (angle < 0)
     angle += 360;
     // move according to distance and angle
     } else
     {
     // keep moving at constant speed
     }
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        dragPos.set(screenX, Gdx.graphics.getHeight() - screenY);
        float distance = Math.abs(touchPos.dst(dragPos));

        // limit distance so it wont jump around when dragged
        if(distance > 70){
            distance = 70;
        }

        if (dragPos.x > touchPos.x) {
            player.moveForwardDrag(distance);
        }

        if (dragPos.x < touchPos.x) {
            player.moveBackDrag(distance);
        }

        if (dragPos.y > touchPos.y) {
            player.moveUpDrag(distance);
        }

        if (dragPos.y < touchPos.y) {
            player.moveDownDrag(distance);
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        player.stop();
        return true;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
