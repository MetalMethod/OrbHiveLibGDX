package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.metalmethodd.orbhive.*;
import com.metalmethodd.orbhive.enemys.Wasp;

import static com.metalmethodd.orbhive.Constants.INITIAL_PLAYER_X;
import static com.metalmethodd.orbhive.Constants.INITIAL_PLAYER_Y;

public class LevelOne extends BaseLevel {

    private Player player;
    private Wasp wasp;
    private Array<Wasp> enemies;
    private Array<Bullet> bullets;

    float previousSpawn;

    public LevelOne(OrbHiveGame game) {
        super(game);

        player = new Player(new Vector2(INITIAL_PLAYER_X, INITIAL_PLAYER_Y));
        gameInputHandler = new GameInputHandler(player);
        enemies = new Array<Wasp>();
        wasp = EnemyFactory.createWasp();
        bullets = new Array<Bullet>();

        previousSpawn = runTime;
    }


    public void render(float delta) {
        updateLevel(delta);

        drawBackground();
        drawPlayer();
        drawEnemies();
        drawBullets();
    }

    private void updateLevel(float delta) {
        runTime += delta;
        checkExitGame();
        player.update(delta);

        gameOverCondition();

        spawnEnemies();
        updateEnemies();
        updateBullets();

        checkCollisionBulletsEnemies(bullets, enemies);
    }

    private void drawPlayer() {
        textureHandler.drawPlayer(player, runTime);
        //textureHandler.drawPlayerBoundingRect(player);
    }

    private void drawBackground() {
        drawBackgroundColor();
        textureHandler.drawBgLevelOne();
    }

    private void drawEnemies() {
        for(Wasp wasp : enemies) {
            textureHandler.drawWasp(runTime, wasp);
            //textureHandler.drawEnemyBoundingRect(wasp);
        }
    }

    private void drawBullets() {
        for(Bullet bullet : bullets){
            textureHandler.drawPlayerBulletRect(bullet);
        }
    }

    private void gameOverCondition() {
        if (player.getState() == Player.EntityState.DEAD) {
            game.setScreen(new GameOverScreen(game));
        }
    }

    private void updateEnemies() {
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

    private void updateBullets() {
        bullets = player.getBullets();

        for (Bullet bullet : bullets) {
            bullet.update();

            if (bullet.getPosition().x >= Constants.SCREEN_WIDTH) {
                bullets.removeValue(bullet, false);
            }
        }
    }

    private void checkCollisionBulletsEnemies(Array<Bullet> bullets, Array<Wasp> enemies) {
        for (Wasp wasp : enemies) {
            for (Bullet bullet : bullets) {
                if (wasp.getBoundingRectangle().overlaps(bullet.getBoundingRectangle())) {
                    killWasp(wasp);
                    bullets.removeValue(bullet, false);
                }
            }
        }
    }

    private void killWasp(Wasp wasp) {
        System.out.println("Enemy HIT");
        enemies.removeValue(wasp, false);
    }

    private void spawnEnemies() {
        float initial = 1.0f;
        float interval = 0.3f;
        float variation = 0.1f;

        if (runTime > initial && runTime < initial + variation) {
            enemies.add(EnemyFactory.createWasp());
            previousSpawn = runTime;
        }

        if (runTime > previousSpawn + interval && runTime < previousSpawn + interval + variation*4) {
            enemies.add(EnemyFactory.createWasp());
            previousSpawn = runTime;
        }
    }

    @Override
    /**
     * Dispose all texture, sprites, animations, background, etc
     */
    public void dispose() {
        batch.dispose();
        textureHandler.dispose();
    }
}
