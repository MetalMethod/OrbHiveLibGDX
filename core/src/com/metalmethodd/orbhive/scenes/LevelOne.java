package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.*;

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
        //textureHandler.drawPlayerBoundingRect(player);

        for (Bullet bullet : player.getBullets()) {
            if (bullet.getPosition().x >= Constants.SCREEN_WIDTH) {
                player.getBullets().remove(bullet);
                return;
            }

            textureHandler.drawPlayerBulletRect(bullet);
        }

        //textureHandler.drawEnemyBoundingRect(wasp);
        textureHandler.drawWasp(runTime, wasp);

        if (wasp.getBoundingRectangle().x < 0) {
            wasp = EnemyFactory.createWasp();
        }

        if(wasp.getBoundingRectangle().overlaps(player.getBoundingRectangle())){
            wasp = EnemyFactory.createWasp();
        }
    }

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
