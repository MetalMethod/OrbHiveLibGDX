package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.metalmethodd.orbhive.OrbHiveGame;

import static com.metalmethodd.orbhive.Constants.SCREEN_HEIGHT;
import static com.metalmethodd.orbhive.Constants.SCREEN_WIDTH;

public class SplashScreen implements Screen {

    private SpriteBatch batch;
    private OrbHiveGame game;
    private Texture backgroundImage;
    private OrthographicCamera camera;

    public SplashScreen(OrbHiveGame game) {
        this.game = game;
        this.batch = game.batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    @Override
    public void show() {
        backgroundImage = new Texture("orbhive_title.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(backgroundImage, 0, 0, SCREEN_WIDTH
                , SCREEN_HEIGHT);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(new LevelOne(game));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
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
    public void dispose() {
        backgroundImage.dispose();
        batch.dispose();
    }
}
