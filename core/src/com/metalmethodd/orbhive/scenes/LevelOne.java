package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.*;

public class LevelOne extends BaseLevel {

    private Player player;
    private SimpleEnemy enemy;
    private SimpleEnemy[] enemys;


    public LevelOne(OrbHiveGame game) {
        super(game);


        player = new Player(new Vector2(100, 100));

        gameInputHandler = new GameInputHandler(player);

        enemys = new SimpleEnemy[]{
                EnemyFactory.createEnemy(),
                EnemyFactory.createEnemy(),
                EnemyFactory.createEnemy()
        };
        enemy = EnemyFactory.createEnemy();
    }

    public void render(float delta) {
        runTime += delta;
        player.update(delta);

        enemy.update(delta);


        System.out.println("enemy x =  " + enemy.getPosition().x);
        System.out.println("enemy Y =  " + enemy.getBoundingRectangle().y);

        checkExitGame();
        drawBackgroundColor();

        textureHandler.drawBgLevelOne();

        textureHandler.drawPlayer(player, runTime);
        //textureHandler.drawPlayerBoundingRect(player);

        for (Bullet bullet : player.getBullets()) {
            if (bullet.getPosition().x >= Constants.SCREEN_WIDTH) {
                player.getBullets().remove(bullet);
                return;
            }


            textureHandler.drawPlayerBulletRect(bullet);
        }

        textureHandler.drawEnemyBoundingRect(enemy);
        textureHandler.drawEnemy(enemy);

        if (enemy.getBoundingRectangle().x < 0) {
            enemy = EnemyFactory.createEnemy();
        }
    }


//textureHandler.drawPlayerBulletRect(player.getBullet());

        /*
        if player overlaps wasp
            do shit - player and wasp methods

        Intersector.overlaps(player_rectangle , wasp_rectangle)
                */


    @Override
/**
 * Dispose all texture, sprites, animations, background, etc
 */
    public void dispose() {
        // dispose images here
        batch.dispose();
    }
}
