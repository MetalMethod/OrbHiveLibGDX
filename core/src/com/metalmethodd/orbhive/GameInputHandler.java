package com.metalmethodd.orbhive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GameInputHandler implements InputProcessor {

    public GameInputHandler(){
            Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        //        Gdx.app.log("key pressed: ", String.valueOf(keycode));
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP: {
                System.out.println("UP");
                break;
            }

            case Input.Keys.D:
            case Input.Keys.RIGHT: {
                System.out.println("RIGHT");
                break;
            }

            case Input.Keys.S:
            case Input.Keys.DOWN: {
                System.out.println("DOWN");
                break;
            }

            case Input.Keys.A:
            case Input.Keys.LEFT: {
                System.out.println("LEFT");
                break;
            }

            case Input.Keys.SPACE:
            case Input.Keys.M: {
                System.out.println("SHOOT");
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
