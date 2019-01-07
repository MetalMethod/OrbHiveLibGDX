package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.*;
import com.metalmethodd.orbhive.enemys.Wasp;

public class LevelOne extends BaseLevel {

    private Player player;
    private Wasp wasp;
    private Wasp[] enemies;

    public LevelOne(OrbHiveGame game) {
        super(game);

        player = new Player(new Vector2(100, 100));

        gameInputHandler = new GameInputHandler(player);

        enemies = new Wasp[]{
                EnemyFactory.createWasp(),
                EnemyFactory.createWasp(),
                EnemyFactory.createWasp()
        };
        wasp = EnemyFactory.createWasp();
    }

    public void render(float delta) {
        runTime += delta;
        checkExitGame();
        drawBackgroundColor();
        textureHandler.drawBgLevelOne();

        player.update(delta);
        wasp.update(delta);

        textureHandler.drawPlayer(player, runTime);

        for (Bullet bullet : player.getBullets()) {
            if (bullet.getPosition().x >= Constants.SCREEN_WIDTH) {
                player.getBullets().remove(bullet);
                return;
            }

            textureHandler.drawPlayerBulletRect(bullet);
        }

        textureHandler.drawWasp(runTime, wasp);

        if (wasp.getBoundingRectangle().x < 0) {
            wasp = EnemyFactory.createWasp();
        }

        if(wasp.getBoundingRectangle().overlaps(player.getBoundingRectangle())){
            wasp = EnemyFactory.createWasp();
            player.takeHit(Constants.playerHitAcceleration);
            if (player.getState() == Player.EntityState.DEAD){
                game.setScreen(new GameOverScreen(game));
            }
            // TODO: 1/7/2019 player lose lives logic here
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
