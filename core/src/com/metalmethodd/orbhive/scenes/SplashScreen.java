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

public class SplashScreen implements Screen {

    private final Renderer renderer;
    private SpriteBatch batch;
    private OrbHiveGame game;
    private OrthographicCamera camera;

    private Array<AbstractEnemy> enemies;
    private float runTime = 0;
    private final EnemyFactory enemyFactory;
    private int timer = 0;
    private boolean keyPressed = false;

    private Music introMp3;

    public SplashScreen(OrbHiveGame game) {
        renderer = new Renderer();

        this.game = game;
        this.batch = game.batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);

        enemies = new Array<AbstractEnemy>();

        enemyFactory = new EnemyFactory();

        introMp3 = AssetLoader.getIntroMp3();
        introMp3.play();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);

        renderer.drawSplashScreen();

        drawWasps(delta);
    }

    public void drawWasps(float delta) {
        for (AbstractEnemy enemy : enemies) {
            enemy.update();
            renderer.drawWasp(runTime, delta, enemies, enemy);
            if (enemy.getPosition().x < 0) {
                enemies.removeValue(enemy, true);
            }
        }
    }

    /**
     * It is required for all levels, to call updateLevelBasicLogic()
     * so all basic mechanics of each level is handled by the BaseLevel class
     *
     * @param delta
     */
    private void update(float delta) {
        runTime += delta;
        enemyFactory.spawnWaspGroup(50, enemies);
        checkEnterGame();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) ||
                Gdx.input.isTouched()
        ) {
            keyPressed = true;

            enemyFactory.resetSpawnGroup();
            enemyFactory.spawnWaspGroup(100, enemies);

            introMp3.stop();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    public void checkEnterGame() {
        // ############################
        game.setScreen(new BossScreen(game, 23));
        // ################################

        if (keyPressed && enemies.size == 0) {
            game.setScreen(new LevelOne(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        introMp3.pause();
    }

    @Override
    public void resume() {
        introMp3.play();
    }

    @Override
    public void hide() {
        introMp3.pause();

    }

    @Override
    public void dispose() {
        renderer.dispose();
        batch.dispose();
        introMp3.dispose();
    }
}
