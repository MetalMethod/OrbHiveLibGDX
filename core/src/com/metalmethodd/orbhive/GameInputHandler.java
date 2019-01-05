package com.metalmethodd.orbhive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GameInputHandler implements InputProcessor {

    private final Player player;

    public GameInputHandler(Player player) {

        Gdx.input.setInputProcessor(this);
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        //        Gdx.app.log("key pressed: ", String.valueOf(keycode));
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP: {
                System.out.println("UP");
                player.moveUp();
                break;
            }

            case Input.Keys.D:
            case Input.Keys.RIGHT: {
                System.out.println("RIGHT");
                player.moveForward();
                break;
            }

            case Input.Keys.S:
            case Input.Keys.DOWN: {
                System.out.println("DOWN");
                player.moveDown();
                break;
            }

            case Input.Keys.A:
            case Input.Keys.LEFT: {
                System.out.println("LEFT");
                player.moveBack();
                break;
            }

            case Input.Keys.SPACE:
            case Input.Keys.M: {
                player.shoot();
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        player.shoot();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
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
