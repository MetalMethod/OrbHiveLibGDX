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
import com.metalmethodd.orbhive.gameobjects.enemies.AbstractEnemy;
import com.metalmethodd.orbhive.gameobjects.Bullet;
import com.metalmethodd.orbhive.gameobjects.EnemyFactory;
import com.metalmethodd.orbhive.gameobjects.Player;
import static com.metalmethodd.orbhive.Constants.*;


public class BaseLevel implements Screen {

    protected SpriteBatch batch;
    protected OrbHiveGame game;
    protected OrthographicCamera camera;

    protected GameInputHandler gameInputHandler;
    protected Renderer renderer;

    protected float runTime;

    protected Player player;
    protected Array<AbstractEnemy> enemies;
    protected Array<Bullet> bullets;

    private UserInterface ui;
    private int score = 0;

    //progress MUST BE A FLOAT FROM 0 TO 100
    private float progress = 0;

    public BaseLevel(OrbHiveGame game) {
        this.game = game;
        this.batch = game.batch;

        player = new Player(new Vector2(INITIAL_PLAYER_X, INITIAL_PLAYER_Y));
        enemies = new Array<AbstractEnemy>();
        bullets = new Array<Bullet>();

        gameInputHandler = new GameInputHandler(player);
        renderer = new Renderer();
        camera = renderer.getCamera();

        ui = new UserInterface();
    }

    @Override
    public void show() {

    }

    @Override
    /**
     * in every level , on the render method call
     * clearDrawBackgroundColor();
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
        renderer.dispose();
    }

    protected void updateLevelBasicLogic(float delta) {
        runTime += delta;
        checkExitGame();
        player.update(delta);

        //disable input if player is not ready (on animation)
        if(player.getGameStarted()){
            gameInputHandler.init();
        }

        gameOverCondition();

        updateEnemies();
        updateBullets();
        checkCollisionBulletsEnemies(bullets, enemies);

        updateUi();
    }

    private void updateUi() {
        if(progress > 100) progress = 100;

        progress += Constants.GAME_PROGRESS_SPEED/10;

        ui.setScore(score);
        //System.out.println(progress);
        ui.setProgress(progress);
    }

    public float getProgress() {
        return progress;
    }

    public void checkExitGame() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    protected void gameOverCondition() {
        if (player.getState() == Player.EntityState.DEAD) {
            game.setScreen(new GameOverScreen(game, score));
        }
    }

    protected void clearDrawBackgroundColor() {
//        Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.03f, 0.03f, 0.03f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected void drawPlayer(float delta) {
        renderer.drawPlayer(player, runTime, delta);
        //renderer.drawPlayerBoundingRect(player);
    }

    /**
     * All levels must overide drawBackground()
     */
    protected void drawBackground() {
        clearDrawBackgroundColor();
    }

    protected void drawEnemies(Array<AbstractEnemy> enemies, float delta) {
        for (AbstractEnemy enemy : enemies) {
            //renderer.drawEnemyBoundingRect(enemy);

            switch (enemy.getEnemyType()) {
                case WASP:
                    renderer.drawWasp(runTime, delta, enemies, enemy);
                    break;

                case SIMPLE_ENEMY:
                    renderer.drawSimpleEnemy(runTime, delta, enemies, enemy);
                    break;

                case BRAIN_SMALL:
                    renderer.drawBrainSmall(runTime, delta, enemies, enemy);
                    break;
            }
        }
    }

    protected void drawBullets(float delta) {
        for (Bullet bullet : bullets) {
            renderer.drawBulletAnimation(bullet, delta, runTime);
            //renderer.drawPlayerBulletRect(bullet);
        }
    }

    protected void updateEnemies() {
        for (AbstractEnemy wasp : enemies) {
            wasp.update();

            if (wasp.getBoundingRectangle().x < 0) {
                enemies.removeValue(wasp, false);
                continue;
            }

            if (wasp.getBoundingRectangle().x > GAME_WIDTH + 10) {
                enemies.removeValue(wasp, false);
                continue;
            }

            //ENEMY HITS PLAYER
            if (wasp.getBoundingRectangle().overlaps(player.getBoundingRectangle())) {
                if (!wasp.isHit()) {
                    enemies.removeValue(wasp, false);
                    player.takeHit(Constants.PLAYER_HIT_ACCELERATION);
                    enemies.add(EnemyFactory.createWasp());
                }
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

    protected void checkCollisionBulletsEnemies(Array<Bullet> bullets, Array<AbstractEnemy> enemies) {
        for (AbstractEnemy enemy : enemies) {
            for (Bullet bullet : bullets) {
                if (enemy.getBoundingRectangle().overlaps(bullet.getBoundingRectangle())) {
                    killEnemy(enemy);
                    bullets.removeValue(bullet, false);
                }
            }

        }
    }

    protected void killEnemy(AbstractEnemy enemy) {
        enemy.setHit(true);
        score++;
    }

    protected void drawUi(){
        renderer.drawUi(ui);
    }
}
