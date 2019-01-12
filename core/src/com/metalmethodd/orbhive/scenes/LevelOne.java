package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.metalmethodd.orbhive.*;
import com.metalmethodd.orbhive.enemys.Wasp;

public class LevelOne extends BaseLevel {

    private Player player;
    private Wasp wasp;
    private Array<Wasp> enemies;

    private Array<Bullet> bullets;

    public LevelOne(OrbHiveGame game) {
        super(game);

        player = new Player(new Vector2(100, 100));
        gameInputHandler = new GameInputHandler(player);
        enemies = new Array<Wasp>();
        wasp = EnemyFactory.createWasp();
        bullets = new Array<Bullet>();
    }


    public void render(float delta) {
        runTime += delta;
        checkExitGame();
        drawBackgroundColor();
        textureHandler.drawBgLevelOne();

        player.update(delta);

        textureHandler.drawPlayer(player, runTime);
        //textureHandler.drawPlayerBoundingRect(player);

        gameOverCondition();

        ///////ENEMIES
        if (runTime > 1.0 && runTime < 1.1) {
            enemies.add(EnemyFactory.createWasp());
        }

        if (runTime > 3.0 && runTime < 3.1) {
            enemies.add(EnemyFactory.createWasp());
        }

        updateEnemies();
        updateBullets();
        checkCollisionBulletsEnemies(bullets, enemies);
    }

    private void gameOverCondition() {
        if (player.getState() == Player.EntityState.DEAD) {
            game.setScreen(new GameOverScreen(game));
        }
    }

    private void updateEnemies() {
        for (Wasp wasp : enemies) {
            wasp.update();
            textureHandler.drawWasp(runTime, wasp);
            //textureHandler.drawEnemyBoundingRect(wasp);

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
            textureHandler.drawPlayerBulletRect(bullet);

            if (bullet.getPosition().x >= Constants.SCREEN_WIDTH) {
                bullets.removeValue(bullet, false);
            }
        }
    }

    private void checkCollisionBulletsEnemies(Array<Bullet> bullets, Array<Wasp> enemies) {
        for (Wasp wasp : enemies) {
            for (Bullet bullet : bullets) {
                if (wasp.getBoundingRectangle().overlaps(bullet.getBoundingRectangle())) {
                    System.out.println("Enemy HIT");
                    enemies.removeValue(wasp, false);
                }

            }
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
