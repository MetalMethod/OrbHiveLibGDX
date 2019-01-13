package com.metalmethodd.orbhive.scenes;

import com.metalmethodd.orbhive.*;
import com.metalmethodd.orbhive.gameobjects.EnemyFactory;

public class LevelOne extends BaseLevel {

    float previousSpawn;

    public LevelOne(OrbHiveGame game) {
        super(game);
        previousSpawn = runTime;
    }

    /**
     * All levels must call update(delta) on render.
     *
     * @param delta
     */
    public void render(float delta) {
        update(delta);
        drawBackground();
        drawPlayer();
        drawEnemies(enemies);
        drawBullets();
    }

    /**
     * It is required for all levels, to call updateLevelBasicLogic()
     * so all basic mechanics of each level is handled by the BaseLevel class
     *
     * @param delta
     */
    private void update(float delta) {
        updateLevelBasicLogic(delta);
        spawnEnemies();
    }

    /**
     * All levels must overide drawBackground()
     */
    @Override
    protected void drawBackground() {
        drawBackgroundColor();
        textureHandler.drawBgLevelOne();
    }

    private void spawnEnemies() {
        // time to start first spawns
        float initial = 1.0f;

        // interval of time since last spawning
        float interval = 0.3f;

        // amount of time that spawning happens
        float variation = 0.05f;

        // HANDLE WASP
        //first spawn
        if (runTime > initial && runTime < initial + variation) {
            enemies.add(EnemyFactory.createWasp());
            previousSpawn = runTime;
        }

        // spawn by interval
        if (runTime > previousSpawn + interval && runTime < previousSpawn + interval + variation * 4) {
            enemies.add(EnemyFactory.createWasp());
            enemies.add(EnemyFactory.createSimpleEnemy());
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
