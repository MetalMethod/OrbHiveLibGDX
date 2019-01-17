package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.metalmethodd.orbhive.AssetLoader;
import com.metalmethodd.orbhive.OrbHiveGame;
import com.metalmethodd.orbhive.TextureHandler;

import static com.metalmethodd.orbhive.Constants.GAME_HEIGHT;
import static com.metalmethodd.orbhive.Constants.GAME_WIDTH;

public class SplashScreen implements Screen {

    private final TextureHandler textureHandler;
    private SpriteBatch batch;
    private OrbHiveGame game;
    private OrthographicCamera camera;

    public SplashScreen(OrbHiveGame game) {
        textureHandler = new TextureHandler();

        this.game = game;
        this.batch = game.batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);

        textureHandler.drawSplashScreen();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) ||
                Gdx.input.isTouched()
        ) {
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
        textureHandler.dispose();
        batch.dispose();
    }
}
