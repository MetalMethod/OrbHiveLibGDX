package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.metalmethodd.orbhive.OrbHiveGame;

import static com.metalmethodd.orbhive.Constants.SCREEN_HEIGHT;
import static com.metalmethodd.orbhive.Constants.SCREEN_WIDTH;


public class BaseLevel implements Screen {

    protected SpriteBatch batch;
    protected OrbHiveGame game;
    protected OrthographicCamera camera;

    public BaseLevel(OrbHiveGame game){
        this.game = game;
        this.batch = game.batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
    }


    @Override
    public void show() {

    }

    @Override
    /**
     * in every level , on the render method call
     * drawBackgroundColor();
     */
    public void render(float delta) {


        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            game.setScreen(new GameOverScreen(game));
        }



        /*
        if player overlaps wasp
            do shit - player and wasp methods


        Intersector.overlaps(player_rectangle , wasp_rectangle)
                */

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    /**
     * Dispose all texture, sprites, animations, background, etc
     * call batch.dispose(); on every level;
     */
    public void dispose() {

    }

    public void checkExitGame(){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }

    protected void drawBackgroundColor() {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected  void drawBackground(){

    }

    protected void drawPlayer() {



    }
}
