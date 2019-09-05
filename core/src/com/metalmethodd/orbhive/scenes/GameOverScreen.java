package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.metalmethodd.orbhive.AssetLoader;
import com.metalmethodd.orbhive.OrbHiveGame;
import com.metalmethodd.orbhive.Renderer;

import static com.metalmethodd.orbhive.Constants.GAME_HEIGHT;
import static com.metalmethodd.orbhive.Constants.GAME_WIDTH;

public class GameOverScreen implements Screen {

    private SpriteBatch batch;
    private OrbHiveGame game;
    private Texture backgroundImage;
    private OrthographicCamera camera;
    private Renderer renderer;

    private int finalScore;

    private float timer = 0;
    private float runTime = 0;
    private boolean keyPressed = false;

    private final Music startMp3;
    private boolean soundFinished = false;

    public GameOverScreen(OrbHiveGame game, int score) {
        this.game = game;
        this.batch = game.batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);

        renderer = new Renderer();

        finalScore = score;

        startMp3 = AssetLoader.getStartMp3();
        startMp3.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                soundFinished = true;
            }
        });
    }


    @Override
    public void show() {
        startMp3.setLooping(false);
        startMp3.play();
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);

        renderer.drawGameOverScreen();

        renderer.drawGameOverFinalScore(finalScore);


    }

    private void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {
            keyPressed = true;
            startMp3.stop();
            startMp3.play();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if (soundFinished) {
            startMp3.stop();
            game.setScreen(new SplashScreen(game));
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
        startMp3.dispose();
    }
}
