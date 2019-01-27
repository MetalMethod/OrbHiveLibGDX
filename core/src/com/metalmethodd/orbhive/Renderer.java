package com.metalmethodd.orbhive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.metalmethodd.orbhive.gameobjects.background.Background;
import com.metalmethodd.orbhive.gameobjects.background.Cloud;
import com.metalmethodd.orbhive.gameobjects.background.Moon;
import com.metalmethodd.orbhive.gameobjects.enemies.AbstractEnemy;
import com.metalmethodd.orbhive.gameobjects.enemies.EnemyType;
import com.metalmethodd.orbhive.gameobjects.enemies.Wasp;
import com.metalmethodd.orbhive.gameobjects.Bullet;
import com.metalmethodd.orbhive.gameobjects.Player;

import static com.metalmethodd.orbhive.Constants.*;

public class Renderer {

    private Texture sprites;
    private OrthographicCamera camera;

    /**
     * Responsible for rendering shapes
     */
    private ShapeRenderer shapeRenderer;

    /**
     * Responsible for rendering images with indices x, y, width and height
     */
    private SpriteBatch batch;

    /**
     * for calculating the window size
     */
    private int midPointY;
    private int gameHeight;

    /**
     * Fullscreen art
     */
    private TextureRegion splashScreen;
    private TextureRegion gameOverScreen;

    /**
     * Game Objects
     */
    //Background texures
    private TextureRegion bgTexture, halfDownBg;
    private TextureRegion moonBig;
    private TextureRegion cloudOne, cloudTwo, cloudThree;

    private Animation playerAnimation;
    private TextureRegion playerFull, playerMid, playerLast;
    private TextureRegion engineOne, engineTwo, engineThree;

    private TextureRegion playerExplosionOne, playerExplosionTwo, playerExplosionThree;
    private TextureRegion playerExplosionFour, playerExplosionFive, playerExplosionSix;

    private TextureRegion enemyFirstOne, enemyFirstTwo, enemyFirstThree;
    private TextureRegion enemyFirstFour, enemyFirstFive, enemyFirstSix, enemyFirstSeven;
    private TextureRegion enemySecondOne, enemySecondTwo, enemySecondThree;
    private TextureRegion enemySecondFour, enemySecondFive, enemySecondSix, enemySecondSeven;

    private TextureRegion waspOne, waspTwo, waspThree, waspFour;
    private TextureRegion waspDeathOne, waspDeathTwo, waspDeathThree, waspDeathFour, waspDeathFive, waspDeathSix, waspDeathSeven;

    private TextureRegion bulletOne, bulletTwo;

    private TextureRegion playerShootOne, playerShootTwo, playerShootThree, playerShootFour;

    private TextureRegion enemyExplosionOne, enemyExplosionTwo, enemyExplosionThree;

    private Animation engineAnimation;
    private Animation playerExplosionAnimation;
    private float explosionTime;
    private Animation playerShootAnimation;

    private Animation enemyFirstAnimation;
    private Animation enemySecondAnimation;
    private Animation enemySecondOptionAnimation;

    private Animation enemyExplosionAnimation;
    private Animation waspAnimation;
    private Animation waspDeathAnimation;

    private float delta;
    private float waspDeathTime = 0;

    public Renderer() {
        AssetLoader.load();
        this.sprites = AssetLoader.getSprites();

        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        int gameWidth = Constants.GAME_WIDTH;
        gameHeight = screenHeight / (screenWidth / gameWidth);

        midPointY = (int) (gameHeight / 2);

        camera = new OrthographicCamera();
        camera.setToOrtho(true, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);

        batch = new SpriteBatch();
        //Attatch Batch to camera
        batch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initAssets();
    }

    private void initAssets() {
        splashScreen = new TextureRegion(AssetLoader.getSplashScreen(), GAME_WIDTH, GAME_HEIGHT);
        splashScreen.flip(false, true);
        gameOverScreen = new TextureRegion(AssetLoader.getGameoverScreen(), GAME_WIDTH, GAME_HEIGHT);
        gameOverScreen.flip(false, true);

        bgTexture = new TextureRegion(sprites, 224, 0, 32, 256);
        halfDownBg = new TextureRegion(sprites, 224, 128, 32, 128);

        moonBig = new TextureRegion(
                sprites,
                144,
                35,
                80,
                80
        );
        moonBig.flip(false, true);

        cloudOne = new TextureRegion(
                sprites,
                125,
                174,
                95,
                14
        );

        cloudTwo = new TextureRegion(
                sprites,
                127,
                152,
                95,
                19
        );

        cloudThree = new TextureRegion(
                sprites,
                125,
                118,
                97,
                27
        );

        cloudOne.flip(false, true);
        cloudTwo.flip(false, true);
        cloudThree.flip(false, true);

        playerFull = new TextureRegion(
                sprites,
                0,
                0,
                39,
                40
        );
        playerMid = new TextureRegion(
                sprites,
                40,
                0,
                39,
                40
        );
        playerLast = new TextureRegion(
                sprites,
                79,
                0,
                39,
                40
        );
        // image must be flipped because default coordinate system is Y Up and this game uses U Down
        playerFull.flip(false, true);
        playerMid.flip(false, true);
        playerLast.flip(false, true);

        engineOne = new TextureRegion(sprites, 0, 199, 10, 10);
        engineTwo = new TextureRegion(sprites, 0, 208, 10, 10);
        engineThree = new TextureRegion(sprites, 0, 217, 10, 10);
        engineOne.flip(false, true);
        engineTwo.flip(false, true);
        engineThree.flip(false, true);
        TextureRegion[] engines = {engineOne, engineTwo, engineThree};
        engineAnimation = new Animation(0.06f, (Object[]) engines);
        engineAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        playerExplosionOne = new TextureRegion(sprites, 37, 193, 32, 32);
        playerExplosionTwo = new TextureRegion(sprites, 68, 193, 32, 32);
        playerExplosionThree = new TextureRegion(sprites, 98, 193, 32, 32);
        playerExplosionFour = new TextureRegion(sprites, 129, 193, 32, 32);
        playerExplosionFive = new TextureRegion(sprites, 161, 193, 32, 32);
        playerExplosionSix = new TextureRegion(sprites, 192, 193, 32, 32);
        playerExplosionOne.flip(false, true);
        playerExplosionTwo.flip(false, true);
        playerExplosionThree.flip(false, true);
        playerExplosionFour.flip(false, true);
        playerExplosionFive.flip(false, true);
        playerExplosionSix.flip(false, true);
        TextureRegion[] playerExplosions = {playerExplosionOne, playerExplosionTwo, playerExplosionThree, playerExplosionFour, playerExplosionFive, playerExplosionSix};
        playerExplosionAnimation = new Animation(0.08f, (Object[]) playerExplosions);
        playerExplosionAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        playerShootOne = new TextureRegion(sprites, 0, 44, 15, 8);
        playerShootTwo = new TextureRegion(sprites, 30, 44, 15, 8);
        playerShootThree = new TextureRegion(sprites, 45, 44, 15, 8);
        playerShootFour = new TextureRegion(sprites, 60, 44, 15, 8);

        playerShootOne.flip(false, true);
        playerShootTwo.flip(false, true);
        playerShootThree.flip(false, true);
        playerShootFour.flip(false, true);

        TextureRegion[] playerShootFrames = {playerShootOne, playerShootTwo, playerShootThree, playerShootFour};
        playerShootAnimation = new Animation(0.15f, (Object[]) playerShootFrames);
        playerShootAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // BULLETS
        bulletOne = new TextureRegion(
                sprites,
                1,
                183,
                6,
                4
        );

        bulletTwo = new TextureRegion(
                sprites,
                13,
                183,
                7,
                4
        );

        enemyExplosionOne = new TextureRegion(sprites, 129, 227, 30, 30);
        enemyExplosionTwo = new TextureRegion(sprites, 159, 227, 30, 30);
        enemyExplosionThree = new TextureRegion(sprites, 189, 227, 30, 30);
        TextureRegion[] enemyExplosions = new TextureRegion[]{enemyExplosionOne, enemyExplosionTwo, enemyExplosionThree};
        enemyExplosionAnimation = new Animation(0.08f, (Object[]) enemyExplosions);
        enemyExplosionAnimation.setPlayMode(Animation.PlayMode.NORMAL);


        // ENEMIES
        enemyFirstOne = new TextureRegion(sprites, 0, 133, 16, 14);
        enemyFirstTwo = new TextureRegion(sprites, 16, 133, 16, 14);
        enemyFirstThree = new TextureRegion(sprites, 32, 133, 16, 14);
        enemyFirstFour = new TextureRegion(sprites, 48, 133, 16, 14);
        enemyFirstFive = new TextureRegion(sprites, 64, 133, 16, 14);
        enemyFirstSix = new TextureRegion(sprites, 80, 133, 16, 14);
        enemyFirstSeven = new TextureRegion(sprites, 96, 133, 16, 14);
        enemyFirstOne.flip(false, true);
        enemyFirstTwo.flip(false, true);
        enemyFirstThree.flip(false, true);
        enemyFirstFour.flip(false, true);
        enemyFirstFive.flip(false, true);
        enemyFirstSix.flip(false, true);
        enemyFirstSeven.flip(false, true);
        TextureRegion[] enemyFirsts = {enemyFirstOne, enemyFirstTwo, enemyFirstThree, enemyFirstFour, enemyFirstFive, enemyFirstSix, enemyFirstSeven};
        enemyFirstAnimation = new Animation(0.15f, (Object[]) enemyFirsts);
        enemyFirstAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        enemySecondOne = new TextureRegion(sprites, 0, 147, 16, 16);
        enemySecondTwo = new TextureRegion(sprites, 16, 147, 16, 16);
        enemySecondThree = new TextureRegion(sprites, 32, 147, 16, 16);
        enemySecondFour = new TextureRegion(sprites, 48, 147, 16, 16);
        enemySecondFive = new TextureRegion(sprites, 64, 147, 16, 16);
        enemySecondSix = new TextureRegion(sprites, 80, 147, 16, 16);
        enemySecondSeven = new TextureRegion(sprites, 96, 147, 16, 16);
        enemySecondOne.flip(false, true);
        enemySecondTwo.flip(false, true);
        enemySecondThree.flip(false, true);
        enemySecondFour.flip(false, true);
        enemySecondFive.flip(false, true);
        enemySecondSix.flip(false, true);
        enemySecondSeven.flip(false, true);

        TextureRegion[] enemySeconds = {enemySecondFive, enemySecondSix, enemySecondSeven};
        enemySecondAnimation = new Animation(0.15f, (Object[]) enemySeconds);
        enemySecondAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        TextureRegion[] enemySecondsOptionTwo = {enemySecondOne, enemySecondTwo, enemySecondThree};
        enemySecondOptionAnimation = new Animation(0.15f, (Object[]) enemySecondsOptionTwo);
        enemySecondOptionAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        waspOne = new TextureRegion(sprites, 0, 55, 16, 16);
        waspTwo = new TextureRegion(sprites, 16, 55, 16, 16);
        waspThree = new TextureRegion(sprites, 32, 56, 16, 16);
        waspFour = new TextureRegion(sprites, 48, 55, 16, 16);
        waspOne.flip(false, true);
        waspTwo.flip(false, true);
        waspThree.flip(false, true);
        waspFour.flip(false, true);

        TextureRegion[] enemyFirstWasps = {waspOne, waspTwo, waspThree};
        waspAnimation = new Animation(0.08f, (Object[]) enemyFirstWasps);
        waspAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        waspDeathOne = new TextureRegion(sprites, 1, 117, 16, 16);
        waspDeathTwo = new TextureRegion(sprites, 17, 117, 16, 16);
        waspDeathThree = new TextureRegion(sprites, 32, 117, 14, 16);
        waspDeathFour = new TextureRegion(sprites, 47, 117, 14, 16);
        waspDeathFive = new TextureRegion(sprites, 62, 117, 14, 16);
        waspDeathSix = new TextureRegion(sprites, 76, 117, 14, 16);
        waspDeathSeven = new TextureRegion(sprites, 92, 117, 14, 16);
        waspDeathOne.flip(false, true);
        waspDeathTwo.flip(false, true);
        waspDeathThree.flip(false, true);
        waspDeathFour.flip(false, true);
        waspDeathFive.flip(false, true);
        waspDeathSix.flip(false, true);
        waspDeathSeven.flip(false, true);

        TextureRegion[] enemyFirstWaspDeaths = {waspDeathOne, waspDeathTwo, waspDeathThree, waspDeathFour, waspDeathFive, waspDeathSix, waspDeathSeven};
        waspDeathAnimation = new Animation(0.12f, (Object[]) enemyFirstWaspDeaths);
        waspDeathAnimation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    public void dispose() {
        AssetLoader.dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }


    private TextureRegion playerState(Player player) {
        if (player.getState() == Player.EntityState.FULL) {
            return playerFull;
        }

        if (player.getState() == Player.EntityState.MID) {
            return playerMid;
        }

        if (player.getState() == Player.EntityState.LAST) {
            return playerLast;
        }

        return playerFull;
    }


    private void fillBlackBg() {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void drawBgTexture() {
        batch.disableBlending();
        batch.begin();
        int width = 32;
        int windowWidth = 256;
        int x = 0;
        while (windowWidth > x) {
            batch.draw(bgTexture, x, 0, width, GAME_HEIGHT);
            x += width;
        }
        batch.end();
        batch.enableBlending();

    }

    public void drawBgLevelOne(Background background) {
        //System.out.println(background.draw);
        if (background.draw) {
            //draw dark bg color

            batch.begin();
            batch.disableBlending();
            int tileWidth = 32;
            int bgX = (int) background.getPosition().x;
            int bgY = (int) background.getPosition().y;


            for (int i = 0; i < GAME_WIDTH; i += tileWidth) {
                batch.draw(halfDownBg, bgX + i, bgY, tileWidth, GAME_HEIGHT / 2);
                batch.draw(halfDownBg, bgX + i - GAME_WIDTH, bgY, tileWidth, GAME_HEIGHT / 2);
            }

            batch.enableBlending();
            batch.end();

            //draw rectangle below half of screen with light bg color
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0.75f, 0.75f, 0.75f, 1f);
            shapeRenderer.rect(0, background.getPosition().y + 127, GAME_WIDTH, GAME_HEIGHT);
            shapeRenderer.end();
        }
    }

    private void drawHalfDownBgTexture() {
        int width = 32;
        int windowWidth = 455;
        int x = 0;
        while (windowWidth > x) {
            batch.draw(halfDownBg, x, 128, width, 128);
            x += width;
        }
    }

    public void drawCloudOne(Cloud cloud) {
        if (cloud.draw) {
            batch.begin();
            batch.enableBlending();
            batch.draw(cloudOne, cloud.getPosition().x, cloud.getPosition().y, cloud.getWidth(), cloud.getHeight());
            batch.disableBlending();
            batch.end();
        }
    }

    public void drawCloudTwo(Cloud cloud) {
        if (cloud.draw) {
            batch.begin();
            batch.enableBlending();
            batch.draw(cloudTwo, cloud.getPosition().x, cloud.getPosition().y, cloud.getWidth(), cloud.getHeight());
            batch.disableBlending();
            batch.end();
        }
    }

    public void drawCloudThree(Cloud cloud) {
        if (cloud.draw) {
            batch.begin();
            batch.enableBlending();
            batch.draw(cloudThree, cloud.getPosition().x, cloud.getPosition().y, cloud.getWidth(), cloud.getHeight());
            batch.disableBlending();
            batch.end();
        }
    }

    public void drawMoonBig(Moon moon) {
        if (moon.draw) {
            batch.begin();
            batch.enableBlending();
            batch.draw(moonBig, moon.getPosition().x, moon.getPosition().y, moon.getWidth(), moon.getHeight());
            batch.disableBlending();
            batch.end();
        }
    }

    public void drawPlayer(Player player, float runTime, float delta) {
        batch.begin();
        batch.enableBlending();
        batch.draw(
                playerState(player),
                ((int) player.getPosition().x),
                ((int) player.getPosition().y),
                50,
                50,
                Constants.PLAYER_SIZE,
                Constants.PLAYER_SIZE,
                1, 1,
                0
        );
        batch.disableBlending();
        batch.end();

        if (player.getIsPlayermoving()) {
            drawPlayerEngine(runTime, player);
        }

        if (player.isPlayerHit()) {
            drawPlayerExplosion(explosionTime, player);
            explosionTime += delta;
        }

        if (playerExplosionAnimation.isAnimationFinished(explosionTime)) {
            explosionTime = 0;
            player.setPlayerHit(false);
        }

        if (player.isPlayerShooting()) {
            drawPlayerShot(runTime, player);
        }

    }


    private void drawPlayerShot(float runTime, Player player) {
        batch.begin();
        batch.enableBlending();
        batch.draw(
                (TextureRegion) playerShootAnimation.getKeyFrame(runTime),
                player.getPosition().x + 40,
                player.getPosition().y + 14,
                15 * 2,
                8 + 4
        );
        batch.disableBlending();
        batch.end();
    }


    private void drawPlayerEngine(float runTime, Player player) {
        batch.begin();
        batch.enableBlending();
        batch.draw(
                (TextureRegion) engineAnimation.getKeyFrame(runTime),
                player.getPosition().x - 7,
                player.getPosition().y + 15,
                10 * 2,
                10
        );
        batch.disableBlending();
        batch.end();
    }


    private void drawPlayerExplosion(float runTime, Player player) {
        batch.begin();
        batch.enableBlending();
        batch.draw(
                (TextureRegion) playerExplosionAnimation.getKeyFrame(runTime),
                player.getPosition().x - 40,
                player.getPosition().y - 50,
                128,
                128
        );
        batch.disableBlending();
        batch.end();
    }

    private void drawEnemyExplosion(float runTime, AbstractEnemy enemy) {
        batch.begin();
        batch.enableBlending();

        float x = enemy.getPosition().x;
        float y = enemy.getPosition().y;
        float w = 30;
        float h = 30;

        switch (enemy.getEnemyType()) {
            case SIMPLE_ENEMY:
                y -= 5;
                break;

            case BRAIN_SMALL:
                x -= 10;
                y -= 15;
                w = 60;
                h = 60;
                break;

            case WASP:
                x -= 5;
                y -= 10;
                w = 45;
                h = 45;
                break;
        }

        batch.draw(
                (TextureRegion) enemyExplosionAnimation.getKeyFrame(runTime),
                x, y,
                w,
                h
        );
        batch.disableBlending();
        batch.end();
    }


    public void drawPlayerBoundingRect(Player player) {
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Draw player bounding rectangle
//        shapeRenderer.setColor(255f, 0f, 0f, 0.35f);
        shapeRenderer.setColor(255f, 0f, 0f, 1);
        shapeRenderer.rect(player.getBoundingRectangle().x, player.getBoundingRectangle().y + Constants.DRAW_PLAYER_HEIGHT_FIX, player.getWidth(), player.getHeight());

        shapeRenderer.end();

    }

    public void drawPlayerBulletRect(Bullet bullet) {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1f);
        shapeRenderer.rect(
                bullet.getBoundingRectangle().x += PLAYER_WIDTH + 1,
                bullet.getBoundingRectangle().y += PLAYER_HEIGHT - 4,
                BULLET_WIDTH, BULLET_HEIGHT);
        shapeRenderer.end();

        // shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        // shapeRenderer.setColor(255, 0, 0, 1f);
        // shapeRenderer.rect(bullet.getBoundingRectangle().x, bullet.getBoundingRectangle().y, BULLET_WIDTH, BULLET_HEIGHT);
        // shapeRenderer.end();
    }

    public void drawBulletOne(Bullet bullet) {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(200, 200, 100, 0.8f);
        shapeRenderer.circle(
                bullet.getPosition().x,
                bullet.getPosition().y, BULLET_CIRCLE_RADIUS, BULLET_CIRCLE_SEGMENTS);
        shapeRenderer.rect(
                bullet.getPosition().x - BULLET_HEIGHT,
                bullet.getPosition().y - BULLET_CIRCLE_RADIUS, BULLET_WIDTH, BULLET_HEIGHT);
        shapeRenderer.end();
    }

    public void drawBulletTwo(Bullet bullet) {
        batch.begin();
        batch.enableBlending();
        batch.draw(bulletOne, bullet.getPosition().x, bullet.getPosition().y, BULLET_WIDTH, BULLET_HEIGHT);
        batch.disableBlending();
        batch.end();

    }

    public void drawEnemyBoundingRect(Wasp enemy) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Draw player bounding rectangle
        shapeRenderer.setColor(255f, 0f, 0f, 1f);
        shapeRenderer.rect(enemy.getBoundingRectangle().x, enemy.getBoundingRectangle().y, WASP_WIDTH, WASP_HEIGHT);

        shapeRenderer.end();
    }

    public void drawWasp(float runTime, float delta, Array<AbstractEnemy> enemies, AbstractEnemy enemy) {
        if (!enemy.isHit()) {
            batch.begin();
            batch.enableBlending();
            batch.draw(
                    (TextureRegion) waspAnimation.getKeyFrame(runTime),
                    enemy.getPosition().x,
                    enemy.getPosition().y,
                    enemy.getWidth(),
                    enemy.getHeight()
            );
            batch.disableBlending();
            batch.end();
        }
        /**
         else if (enemy.isHit()) {
         drawWaspDeath(enemy.getDeathAnimationTime(), enemy);
         enemy.setDeathAnimationTime(enemy.getDeathAnimationTime() + delta);
         }

         if (waspDeathAnimation.isAnimationFinished(enemy.getDeathAnimationTime())) {
         enemy.setDeathAnimationTime(0);
         enemies.removeValue(enemy, false);
         }

         */
        enemyKillCondition(delta, enemies, enemy);
    }

    public void drawWaspDeath(float runTime, AbstractEnemy enemy) {
        batch.begin();
        batch.enableBlending();
        batch.draw(
                (TextureRegion) waspDeathAnimation.getKeyFrame(runTime),
                enemy.getPosition().x,
                enemy.getPosition().y,
                enemy.getWidth(),
                enemy.getHeight()
        );
        batch.disableBlending();
        batch.end();

        drawEnemyExplosion(runTime, enemy);
    }


    public void drawSimpleEnemy(float runTime, float delta, Array<AbstractEnemy> enemies, AbstractEnemy enemy) {
        if (!enemy.isHit()) {
            batch.begin();
            batch.enableBlending();
            batch.draw(
                    (TextureRegion) enemyFirstAnimation.getKeyFrame(runTime),
                    enemy.getPosition().x,
                    enemy.getPosition().y,
                    enemy.getWidth(),
                    enemy.getHeight()
            );
            batch.disableBlending();
            batch.end();
        }
        enemyKillCondition(delta, enemies, enemy);
    }

    public void drawBrainSmall(float runTime, float delta, Array<AbstractEnemy> enemies, AbstractEnemy enemy) {
        if (!enemy.isHit()) {
            batch.begin();
            batch.enableBlending();
            batch.draw(
                    (TextureRegion) enemySecondAnimation.getKeyFrame(runTime),
                    enemy.getPosition().x,
                    enemy.getPosition().y,
                    enemy.getWidth(),
                    enemy.getHeight()
            );
            batch.disableBlending();
            batch.end();
        }
        enemyKillCondition(delta, enemies, enemy);
    }

    private void enemyKillCondition(float delta, Array<AbstractEnemy> enemies, AbstractEnemy enemy) {
        if (enemy.isHit()) {
            drawEnemyExplosion(enemy.getDeathAnimationTime(), enemy);
            enemy.setDeathAnimationTime(enemy.getDeathAnimationTime() + delta);
        }

        if (enemyExplosionAnimation.isAnimationFinished(enemy.getDeathAnimationTime())) {
            enemy.setDeathAnimationTime(0);
            enemies.removeValue(enemy, false);
        }
    }

    public void drawSplashScreen() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(splashScreen, 0, 0, GAME_WIDTH, GAME_HEIGHT);
        batch.end();

    }

    public void drawGameOverScreen() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(gameOverScreen, 0, 0, GAME_WIDTH, GAME_HEIGHT);
        batch.end();

    }


    ///BACKGROUND
    // STARS
    public void drawSingleStar(float x, float y, float alpha) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, alpha);
        shapeRenderer.rect(
                x,
                y,
                1, 1);
        shapeRenderer.end();

    }

}

