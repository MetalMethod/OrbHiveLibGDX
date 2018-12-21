package com.metalmethodd.orbhive;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {




    @Override
    public boolean keyDown(int keycode) {
        //        Gdx.app.log("key pressed: ", String.valueOf(keycode));
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP: {

                break;
            }

            case Input.Keys.D:
            case Input.Keys.RIGHT: {

                break;
            }

            case Input.Keys.S:
            case Input.Keys.DOWN: {

                break;
            }

            case Input.Keys.A:
            case Input.Keys.LEFT: {

                break;
            }

            case Input.Keys.SPACE:
            case Input.Keys.M: {

            }
        }
        return true;
    }


    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP: {

                break;
            }

            case Input.Keys.D:
            case Input.Keys.RIGHT: {

                break;
            }
            case Input.Keys.S:
            case Input.Keys.DOWN: {

                break;
            }
            case Input.Keys.A:
            case Input.Keys.LEFT: {

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
        return false;
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
