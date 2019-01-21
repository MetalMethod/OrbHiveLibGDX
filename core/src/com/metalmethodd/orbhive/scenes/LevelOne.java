package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.*;
import com.metalmethodd.orbhive.gameobjects.background.Background;
import com.metalmethodd.orbhive.gameobjects.EnemyFactory;
import com.metalmethodd.orbhive.gameobjects.background.Moon;

import static com.metalmethodd.orbhive.Constants.*;

public class LevelOne extends BaseLevel {

    private float previousSpawn;
    private float previousBrainSpawn;
    private Background background;
    private Moon moon;

    public LevelOne(OrbHiveGame game) {
        super(game);
        previousSpawn = runTime;
        previousBrainSpawn = runTime;

        background = new Background(new Vector2(GAME_WIDTH, 0), 256, 256);
        moon = new Moon(new Vector2(0,0), MOON_BIG_SIZE, MOON_BIG_SIZE);
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
        updateBackground();
        updateLevelBasicLogic(delta);
        // spawnEnemies();
    }

    private void updateBackground() {
        // scroll background to left
        background.setPosition(
                background.getPosition().x - BG_LVL_ONE_X_SPEED,
                background.getPosition().y + Constants.BG_LVL_ONE_Y_SPEED
        );

        //resets background to the right
        if(background.getPosition().x < 0){
            background.setPosition(GAME_WIDTH, background.getPosition().y);
        }

        // update moon
        if(background.getPosition().y < 300){
            moon.setPosition(100, (int) (background.getPosition().y -200));
        }

        if(background.getPosition().y > 350 && background.getPosition().y <600 ){
            moon.setPosition(100, (int) moon.getPosition().y);
            moon.increaseSize(0.2);
            background.draw = false;
        }

        if(background.getPosition().y > 600 && background.getPosition().y <730 ){
            moon.setPosition((int) (moon.getPosition().x + 1), (int) moon.getPosition().y + 1);
            moon.increaseSize(0.8);
        }

        if(background.getPosition().y > 730){
//            backgroundReset();
        }
    }

    private void backgroundReset() {
        background.draw = true;
        moon.draw = false;
        background.setPosition(GAME_WIDTH,-GAME_HEIGHT);
    }

    /**
     * All levels must overide drawBackground()
     */
    @Override
    protected void drawBackground() {
        drawBackgroundColor();
        textureHandler.drawBgLevelOne(background, moon);
    }

    private void spawnEnemies() {
        // time to start first spawns
        final float initial = 2.0f;

        // interval of time since last spawning
        final float interval = 0.3f;

        // amount of time that spawning happens
        final float variation = 0.05f;

        //first spawn. waits for initial time pass
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

        // Brain spawn by interval
        if (runTime > previousBrainSpawn + 2 && runTime < previousBrainSpawn + 2 + variation * 4) {
            enemies.add(EnemyFactory.createBrainSmall());
            previousBrainSpawn = runTime;
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
