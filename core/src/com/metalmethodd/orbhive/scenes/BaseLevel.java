package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.metalmethodd.orbhive.*;
import com.metalmethodd.orbhive.enemys.Wasp;

import static com.metalmethodd.orbhive.Constants.*;


public class BaseLevel implements Screen {

    protected SpriteBatch batch;
    protected OrbHiveGame game;
    protected OrthographicCamera camera;

    protected GameInputHandler gameInputHandler;
    protected TextureHandler textureHandler;

    protected float runTime;

    protected Player player;
    protected Array<Wasp> enemies;
    protected Array<Bullet> bullets;


    public BaseLevel(OrbHiveGame game) {
        this.game = game;
        this.batch = game.batch;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);

        player = new Player(new Vector2(INITIAL_PLAYER_X, INITIAL_PLAYER_Y));
        enemies = new Array<Wasp>();
        bullets = new Array<Bullet>();

        gameInputHandler = new GameInputHandler(player);
        textureHandler = new TextureHandler();
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
        textureHandler.dispose();
    }

    protected void updateLevelBasicLogic(float delta) {
        runTime += delta;
        checkExitGame();
        player.update(delta);

        gameOverCondition();

        updateEnemies();
        updateBullets();

        checkCollisionBulletsEnemies(bullets, enemies);
    }

    public void checkExitGame() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    protected void gameOverCondition() {
        if (player.getState() == Player.EntityState.DEAD) {
            game.setScreen(new GameOverScreen(game));
        }
    }

    protected void drawBackgroundColor() {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected void drawPlayer() {
        textureHandler.drawPlayer(player, runTime);
        //textureHandler.drawPlayerBoundingRect(player);
    }

    /**
     * All levels must overide drawBackground()
     */
    protected void drawBackground() {
        drawBackgroundColor();
    }

    protected void drawEnemies() {
        for (Wasp wasp : enemies) {
            textureHandler.drawWasp(runTime, wasp);
            //textureHandler.drawEnemyBoundingRect(wasp);
        }
    }

    protected void drawBullets() {
        for (Bullet bullet : bullets) {
            textureHandler.drawPlayerBulletRect(bullet);
        }
    }

    protected void updateEnemies() {
        for (Wasp wasp : enemies) {
            wasp.update();

            if (wasp.getBoundingRectangle().x < 0) {
                enemies.removeValue(wasp, false);
                continue;
            }

            if (wasp.getBoundingRectangle().overlaps(player.getBoundingRectangle())) {
                enemies.removeValue(wasp, false);
                player.takeHit(Constants.PLAYER_HIT_ACCELERATION);
                enemies.add(EnemyFactory.createWasp());
            }
        }
    }

    protected void updateBullets() {
        bullets = player.getBullets();

        for (Bullet bullet : bullets) {
            bullet.update();

            if (bullet.getPosition().x >= Constants.GAME_WIDTH) {
                bullets.removeValue(bullet, false);
            }
        }
    }

    protected void checkCollisionBulletsEnemies(Array<Bullet> bullets, Array<Wasp> enemies) {
        for (Wasp wasp : enemies) {
            for (Bullet bullet : bullets) {
                if (wasp.getBoundingRectangle().overlaps(bullet.getBoundingRectangle())) {
                    killWasp(wasp);
                    bullets.removeValue(bullet, false);
                }
            }
        }
    }

    protected void killWasp(Wasp wasp) {
        System.out.println("Enemy HIT");
        enemies.removeValue(wasp, false);
    }

}
