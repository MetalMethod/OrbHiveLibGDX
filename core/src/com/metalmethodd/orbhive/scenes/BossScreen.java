package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.OrbHiveGame;
import com.metalmethodd.orbhive.gameobjects.EnemyFactory;
import com.metalmethodd.orbhive.gameobjects.background.Background;
import com.metalmethodd.orbhive.gameobjects.background.Cloud;
import com.metalmethodd.orbhive.gameobjects.background.Moon;
import com.metalmethodd.orbhive.gameobjects.background.Star;

import static com.metalmethodd.orbhive.Constants.*;

public class BossScreen extends BaseLevel {

    public BossScreen(OrbHiveGame game, int score) {
        super(game);
        ui.setScore(score);
    }

    /**
     * All levels must call update(delta)
     * before any other method on render.
     *
     * @param delta
     */
    public void render(float delta) {
        update(delta);

        renderer.updateCameraShake(delta);
        clearDrawBackgroundColor();
        drawBackground();
        drawPlayer(delta);
        drawEnemies(enemies, delta);
        drawBullets(delta);

        drawUi(ui);

        renderer.drawBossEye(runTime);
        renderer.drawBossEye2(runTime);
        renderer.drawBossEye3(runTime);

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
        spawnEnemies();

    }

    private void updateBackground() {
    }


    /**
     * All levels must overide drawBackground()
     */
    @Override
    protected void drawBackground() {
    }


    private void spawnEnemies() {
        enemyFactory.spawnEnemies(getProgress(), enemies);
    }

    @Override
    /**
     * Dispose all texture, sprites, animations, background, etc
     */
    public void dispose() {
        batch.dispose();
        renderer.dispose();
    }
}
