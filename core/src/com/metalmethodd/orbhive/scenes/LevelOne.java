package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.metalmethodd.orbhive.*;
import com.metalmethodd.orbhive.gameobjects.background.Background;
import com.metalmethodd.orbhive.gameobjects.EnemyFactory;
import com.metalmethodd.orbhive.gameobjects.background.Cloud;
import com.metalmethodd.orbhive.gameobjects.background.Moon;
import com.metalmethodd.orbhive.gameobjects.background.Star;

import static com.metalmethodd.orbhive.Constants.*;

public class LevelOne extends BaseLevel {

    private float previousSpawn;
    private float previousBrainSpawn;
    private Background background;
    private Moon moon;
    private Cloud cloudOne, cloudTwo, cloudThree;
    private Cloud cloudOneCopy, cloudTwoCopy, cloudThreeCopy;
    private Array<Cloud> clouds;
    private Array<Star> starsFarLayer;

    // spawn counters
    private int waspCount = 0;
    private int enemySimpleCount = 0;
    private int brainCount = 0;

    private Music gameMp3;
    private Music.OnCompletionListener songFinishedListener;
    private int timesSongFinished = 0;
    private float totalMusicTime = 0;

    public LevelOne(OrbHiveGame game) {
        super(game);

        gameMp3 = AssetLoader.getGaneMp3();
        gameMp3.setLooping(false);
        songFinishedListener = new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                timesSongFinished++;
            }
        };
        gameMp3.setOnCompletionListener(songFinishedListener);
        gameMp3.play();

        previousSpawn = runTime;
        previousBrainSpawn = runTime;

        background = new Background(new Vector2(GAME_WIDTH, 0), 256, 256);
        moon = new Moon(new Vector2(0, 0), MOON_BIG_SIZE, MOON_BIG_SIZE);
        cloudOne = new Cloud(new Vector2(GAME_WIDTH, background.getPosition().y + 50), 95, 14);
        cloudTwo = new Cloud(new Vector2(20, 120), 95, 19);
        cloudThree = new Cloud(new Vector2(GAME_WIDTH / 2, 140), 97, 27);
        cloudOne.draw = true;
        cloudTwo.draw = true;
        cloudThree.draw = true;

        cloudOneCopy = new Cloud(new Vector2(GAME_WIDTH + 100, background.getPosition().y + 40), 95, 14);
        cloudTwoCopy = new Cloud(new Vector2(GAME_WIDTH / 3 + 60, background.getPosition().y + 20), 95, 19);
        cloudThreeCopy = new Cloud(new Vector2(GAME_WIDTH, background.getPosition().y + 30), 97, 27);
        cloudOneCopy.draw = true;
        cloudTwoCopy.draw = true;
        cloudThreeCopy.draw = true;
        clouds = new Array<Cloud>();
        clouds.add(cloudOne, cloudTwo, cloudThree);
        clouds.add(cloudOneCopy, cloudTwoCopy, cloudThreeCopy);

        starsFarLayer = createStarLayer(40, 0.4f);
    }

    private Array<Star> createStarLayer(int starCount, float alpha) {
        Array<Star> stars = new Array<Star>();
        for (int i = 0; i < starCount; i++) {
            stars.add(new Star(
                    EnemyFactory.getRandomInt(0, GAME_WIDTH),
                    EnemyFactory.getRandomInt(0, GAME_HEIGHT),
                    alpha)
            );
        }
        return stars;
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

        updateProgress();
        updateMusic();
    }

    private void updateMusic() {
        if (gameOverCondition()) {
            gameMp3.stop();
        }
    }

    private void updateProgress() {
        //progress = background.getPosition().y / 10;
        //progress = runTime / 2;

        progress = (gameMp3.getPosition() * 0.75f) * 2;

        if (progress > 100f) {
            goToBoss();
        }
    }

    private void updateBackground() {
        // scroll background to left
        background.setPosition(
                background.getPosition().x - BG_LVL_ONE_X_SPEED,
                background.getPosition().y + Constants.BG_LVL_ONE_Y_SPEED
        );

        //resets background to the right
        if (background.getPosition().x < 0) {
            background.setPosition(GAME_WIDTH, background.getPosition().y);
        }

        // update moon
        if (background.getPosition().y < 300) {
            moon.setPosition(100, (int) (background.getPosition().y - 200));
        }

        if (background.getPosition().y > 300) {
            moon.increaseSize(0.5f);
            background.draw = false;
        }

        updateClouds();

        // if (background.getPosition().y > 600 && background.getPosition().y < 730) {
        //    moon.setPosition((int) (moon.getPosition().x + 1), (int) moon.getPosition().y + 1);
        //    moon.increaseSize(0.8);
        // }

        //GO TO BOSS
//        if (background.getPosition().y > BOSS_BG_Y - 1) {
    }

    private void updateClouds() {
        cloudOne.setPosition(cloudOne.getPosition().x - Constants.CLOUD_ONE_SPEED, background.getPosition().y + 50);
        cloudTwo.setPosition(cloudTwo.getPosition().x - Constants.CLOUD_TWO_SPEED, background.getPosition().y + 60);
        cloudThree.setPosition(cloudThree.getPosition().x - Constants.CLOUD_THREE_SPEED, background.getPosition().y + 40);

        cloudOneCopy.setPosition(cloudOneCopy.getPosition().x - Constants.CLOUD_THREE_SPEED, background.getPosition().y + 60);
        cloudTwoCopy.setPosition(cloudTwoCopy.getPosition().x - Constants.CLOUD_ONE_SPEED, background.getPosition().y + 70);
        cloudThreeCopy.setPosition(cloudThreeCopy.getPosition().x - Constants.CLOUD_TWO_SPEED, background.getPosition().y + 80);

        for (Cloud cloud : clouds) {
            if (cloud.getPosition().x < -GAME_WIDTH / 2) {
                cloud.setPosition(GAME_WIDTH, cloud.getPosition().y);
            }
            if (cloud.getPosition().y > 400) {
                cloud.draw = false;
            }
        }
    }

    private void backgroundReset() {
        background.draw = true;
        moon.draw = false;
        background.setPosition(GAME_WIDTH, -GAME_HEIGHT);
    }

    /**
     * All levels must overide drawBackground()
     */
    @Override
    protected void drawBackground() {
        drawStars(starsFarLayer);
        renderer.drawBgLevelOne(background);
        renderer.drawMoonBig(moon);
        drawClouds();
    }

    private void drawStars(Array<Star> stars) {
        for (Star star : stars) {
            renderer.drawSingleStar(star.getPosition().x, star.getPosition().y, star.getAlpha());
        }
    }

    private void drawClouds() {
        renderer.drawCloudOne(cloudOne);
        renderer.drawCloudTwo(cloudTwo);
        renderer.drawCloudThree(cloudThree);
        renderer.drawCloudOne(cloudOneCopy);
        renderer.drawCloudTwo(cloudTwoCopy);
        renderer.drawCloudThree(cloudThreeCopy);
    }

    private void spawnEnemies() {
        enemyFactory.spawnEnemies(getProgress(), enemies);

    }

    private void goToBoss() {
        game.setScreen(new BossScreen(game, ui.getScore()));
    }

    @Override
    /**
     * Dispose all texture, sprites, animations, background, etc
     */
    public void dispose() {
        batch.dispose();
        renderer.dispose();
        gameMp3.dispose();
    }
}
