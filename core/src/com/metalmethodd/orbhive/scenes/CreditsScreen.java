package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.metalmethodd.orbhive.AssetLoader;
import com.metalmethodd.orbhive.OrbHiveGame;
import com.metalmethodd.orbhive.Renderer;
import com.metalmethodd.orbhive.gameobjects.EnemyFactory;
import com.metalmethodd.orbhive.gameobjects.enemies.AbstractEnemy;

import static com.metalmethodd.orbhive.Constants.GAME_HEIGHT;
import static com.metalmethodd.orbhive.Constants.GAME_WIDTH;

public class CreditsScreen implements Screen {

    private final Renderer renderer;
    private SpriteBatch batch;
    private OrbHiveGame game;
    private OrthographicCamera camera;

    public CreditsScreen(OrbHiveGame game) {
        renderer = new Renderer();

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
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);

        renderer.drawCredits();
    }

    /**
     * It is required for all levels, to call updateLevelBasicLogic()
     * so all basic mechanics of each level is handled by the BaseLevel class
     *
     * @param delta
     */
    private void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) ||
                Gdx.input.isTouched()
        ) {
            game.setScreen(new SplashScreen(game));
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
        renderer.dispose();
        batch.dispose();
    }
}
