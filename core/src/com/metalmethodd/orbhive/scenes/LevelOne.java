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
        enemies.add(EnemyFactory.createWasp());

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

        textureHandler.drawWasp(runTime, wasp);

        ///////ENEMIES
        for(Wasp wasp : enemies){
            wasp.update(delta);

                }

        if (wasp.getBoundingRectangle().x < 0) {
            wasp = EnemyFactory.createWasp();
        }

        if(wasp.getBoundingRectangle().overlaps(player.getBoundingRectangle())){
            wasp = EnemyFactory.createWasp();
            player.takeHit(Constants.playerHitAcceleration);
        }

        if (player.getState() == Player.EntityState.DEAD){
            game.setScreen(new GameOverScreen(game));
        }

        /////// BULLETS
        bullets = player.getBullets();

        for (Bullet bullet : bullets) {
            bullet.update(delta);
            textureHandler.drawPlayerBulletRect(bullet);

            if (bullet.getPosition().x >= Constants.SCREEN_WIDTH) {
                bullets.removeValue(bullet, false);
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
