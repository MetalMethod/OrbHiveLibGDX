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

public class GameOverScreen implements Screen {

    private SpriteBatch batch;
    private OrbHiveGame game;
    private Texture backgroundImage;
    private OrthographicCamera camera;
    private TextureHandler textureHandler;

    public GameOverScreen(OrbHiveGame game){
        this.game = game;
        this.batch = game.batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);

        textureHandler = new TextureHandler();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);

        textureHandler.drawGameOverScreen();

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            game.setScreen(new SplashScreen(game));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
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
