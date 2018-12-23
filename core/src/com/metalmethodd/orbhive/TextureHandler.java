package com.metalmethodd.orbhive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class TextureHandler {

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
     * Game Objects
     */
    //private Player player;
    private TextureRegion bg, halfDownBg;
    private Animation playerAnimation;
    private TextureRegion playerFull, playerMid, playerLast;
    private TextureRegion engineOne, engineTwo, engineThree;

    private TextureRegion playerExplosionOne, playerExplosionTwo, playerExplosionThree;
    private TextureRegion playerExplosionFour, playerExplosionFive, playerExplosionSix;

    private TextureRegion enemyFirstOne, enemyFirstTwo, enemyFirstThree;
    private TextureRegion enemyFirstFour, enemyFirstFive, enemyFirstSix, enemyFirstSeven;
    private TextureRegion enemySecondOne, enemySecondTwo, enemySecondThree;
    private TextureRegion enemySecondFour, enemySecondFive, enemySecondSix, enemySecondSeven;

    private TextureRegion enemyFirstWaspOne, enemyFirstWaspTwo, enemyFirstWaspThree, enemyFirstWaspFour;
    private TextureRegion enemyFirstWaspDeathOne, enemyFirstWaspDeathTwo, enemyFirstWaspDeathThree, enemyFirstWaspDeathFour, enemyFirstWaspDeathFive, enemyFirstWaspDeathSix, enemyFirstWaspDeathSeven;

    private Animation engineAnimation;
    private Animation playerExplosionAnimation;

    private Animation enemyFirstAnimation;
    private Animation enemySecondAnimation;
    private Animation enemySecondOptionAnimation;

    private Animation enemyFirstWaspAnimation;
    private Animation enemyFirstWaspDeathAnimation;
    private Animation playerShootAnimation;




    public TextureHandler() {
        AssetLoader.load();
        this.sprites = AssetLoader.getSprites();

        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        int gameWidth = Constants.SCREEN_WIDTH;
        gameHeight = screenHeight / (screenWidth / gameWidth);

        midPointY = (int) (gameHeight / 2);

        camera = new OrthographicCamera();
        camera.setToOrtho(true, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        batch = new SpriteBatch();
        //Attatch Batch to camera
        batch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initAssets();

    }


    private void initAssets() {
        bg = new TextureRegion(sprites, 224, 0, 32, 256);
        halfDownBg = new TextureRegion(sprites, 224, 128, 32, 128);

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
        playerExplosionAnimation = new Animation(0.15f, (Object[]) playerExplosions);
        playerExplosionAnimation.setPlayMode(Animation.PlayMode.LOOP);




        //TODO: player shooting sprites
        TextureRegion[] playerShootFrames = {};
        playerShootAnimation = new Animation(0.15f, (Object[]) playerShootFrames);
        playerExplosionAnimation.setPlayMode(Animation.PlayMode.LOOP);



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

        enemyFirstWaspOne = new TextureRegion(sprites, 0, 55, 16, 16);
        enemyFirstWaspTwo = new TextureRegion(sprites, 16, 55, 16, 16);
        enemyFirstWaspThree = new TextureRegion(sprites, 32, 56, 16, 16);
        enemyFirstWaspFour = new TextureRegion(sprites, 48, 55, 16, 16);
        enemyFirstWaspOne.flip(false, true);
        enemyFirstWaspTwo.flip(false, true);
        enemyFirstWaspThree.flip(false, true);
        enemyFirstWaspFour.flip(false, true);

        TextureRegion[] enemyFirstWasps = {enemyFirstWaspOne, enemyFirstWaspTwo, enemyFirstWaspThree};
        enemyFirstWaspAnimation = new Animation(0.08f, (Object[]) enemyFirstWasps);
        enemyFirstWaspAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        enemyFirstWaspDeathOne = new TextureRegion(sprites, 1, 117, 16, 16);
        enemyFirstWaspDeathTwo = new TextureRegion(sprites, 17, 117, 16, 16);
        enemyFirstWaspDeathThree = new TextureRegion(sprites, 32, 117, 14, 16);
        enemyFirstWaspDeathFour = new TextureRegion(sprites, 47, 117, 14, 16);
        enemyFirstWaspDeathFive = new TextureRegion(sprites, 62, 117, 14, 16);
        enemyFirstWaspDeathSix = new TextureRegion(sprites, 76, 117, 14, 16);
        enemyFirstWaspDeathSeven = new TextureRegion(sprites, 92, 117, 14, 16);
        enemyFirstWaspDeathOne.flip(false, true);
        enemyFirstWaspDeathTwo.flip(false, true);
        enemyFirstWaspDeathThree.flip(false, true);
        enemyFirstWaspDeathFour.flip(false, true);
        enemyFirstWaspDeathFive.flip(false, true);
        enemyFirstWaspDeathSix.flip(false, true);
        enemyFirstWaspDeathSeven.flip(false, true);

        TextureRegion[] enemyFirstWaspDeaths = {enemyFirstWaspDeathOne, enemyFirstWaspDeathTwo, enemyFirstWaspDeathThree, enemyFirstWaspDeathFour, enemyFirstWaspDeathFive, enemyFirstWaspDeathSix, enemyFirstWaspDeathSeven};
        enemyFirstWaspDeathAnimation = new Animation(0.1f, (Object[]) enemyFirstWaspDeaths);
        enemyFirstWaspDeathAnimation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    public void dispose(){
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
        int windowWidth = 455;
        int x = 0;
        while (windowWidth > x) {
            batch.draw(bg, x, 0, width, 256);
            x += width;
        }
        batch.end();
        batch.enableBlending();

    }

    public void drawBgLevelOne() {
        batch.disableBlending();
        batch.begin();
        int width = 32;
        int windowWidth = 455;
        int x = 0;
        while (windowWidth > x) {
            batch.draw(bg, x, 0, width, 256);
            x += width;
        }
        batch.end();
        batch.enableBlending();
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

    /**
     * runTime is responsible to determine which frame the player animation should display.
     * The Animation object will use this value (and the frame duration) to determine which TextureRegion to display.
     *
     * @param runTime
     */
    public void render(float runTime) {

        //drawBackgroundColor();

        batch.begin();

        // Disable transparency - this is good for performance when drawing images that do not require transparency.
        batch.disableBlending();

        drawBgTexture();
        //drawHalfDownBgTexture();

        // Draw elements that require transparency
        batch.enableBlending();
        //drawPlayer(runTime, playerState());


        //ENEMIES RENDERING
       /*
        drawEnemyFirst(runTime, enemy);

        drawEnemySecond(runTime);

         drawPlayerExplosion(runTime);

          drawEnemySecondOption(runTime);
        drawWasp(runTime);
        drawWaspDeath(runTime);

        //drawEnemyList();
*/

        // Draw non-bitmap elements
//        drawPlayerBoundingRect();
//        drawEnemyBoundingRect(enemy);
        batch.end();
    }


    public void drawPlayer(Player player, float runTime) {
        batch.begin();
        batch.enableBlending();
        batch.draw(
                playerState(player),
                ((int) player.getPosition().x),
                ((int) player.getPosition().y),
                50,
                50,
                Constants.playerSize,
                Constants.playerSize,
                1, 1,
                0
        );
        batch.disableBlending();
        batch.end();

        if (player.getIsPlayermoving()) {
            drawPlayerEngine(runTime, player);
        }

        /*
        if (controller.isPlayerHit()) {
            drawPlayerExplosion(runTime);
        }

        if(controller.isPlayerShooting()){
            drawPlayerShot(runTime);
        }
*/

    }




/**
    private void drawPlayerShot(float runTime) {
        batch.draw(
                (TextureRegion) playerShootAnimation.getKeyFrame(runTime),
                player.getPosition().x + 50,
                player.getPosition().y +50,
                10,
                10
        );
    }

 */

    private void drawPlayerEngine(float runTime, Player player) {
        batch.begin();
        batch.enableBlending();
        batch.draw(
                (TextureRegion) engineAnimation.getKeyFrame(runTime),
                player.getPosition().x + 1,
                player.getPosition().y + 15,
                10,
                10
        );
        batch.disableBlending();
        batch.end();
    }

    /**
    private void drawPlayerExplosion(float runTime) {
        batch.draw(
                (TextureRegion) playerExplosionAnimation.getKeyFrame(runTime),
                player.getPosition().x,
                player.getPosition().y,
                48,
                48
        );
    }

    private void drawBackgroundColor() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(128 / 255.0f, 128 / 255.0f, 128 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 455, 256);

        shapeRenderer.end();
    }

    private void drawPlayerBoundingRect() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw player bounding rectangle
        shapeRenderer.setColor(255f, 0f, 0f, 0.35f);
        shapeRenderer.rect(player.getBoundingRectangle().x, player.getBoundingRectangle().y, player.getWidth(), player.getHeight());

        shapeRenderer.end();

    }
        private void drawPlayerBulletRect(){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

            // Draw player bullet bounding rectangle
            shapeRenderer.setColor(255f, 0f, 0f, 0.35f);
            shapeRenderer.rect(player.getBoundingRectangle().x, player.getBoundingRectangle().y, 10,10);
            shapeRenderer.end();

        }

    private void drawEnemyBoundingRect(Enemy enemy) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw player bounding rectangle
        shapeRenderer.setColor(255f, 0f, 0f, 0.35f);
        shapeRenderer.rect(enemy.getBoundingRectangle().x, enemy.getBoundingRectangle().y, enemy.getWidth(), enemy.getHeight());

        shapeRenderer.end();
    }

    private void drawEnemyFirst(float runTime, Enemy enemy) {

        batch.draw(
                (TextureRegion) enemyFirstAnimation.getKeyFrame(runTime),
                enemy.getPosition().x,
                enemy.getPosition().y,
                enemy.getWidth(),
                enemy.getHeight()
        );
    }

    private void drawEnemySecond(float runTime, Enemy enemy) {
        batch.draw(
                (TextureRegion) enemySecondAnimation.getKeyFrame(runTime),
                enemy.getPosition().x,
                enemy.getPosition().y,
                enemy.getWidth(),
                enemy.getHeight()
        );
    }

    private void drawEnemySecondOption(float runTime) {
        batch.draw(
                (TextureRegion) enemySecondOptionAnimation.getKeyFrame(runTime),
                100,
                300,
                16,
                16
        );
    }

    private void drawWasp(float runTime, Enemy enemy) {
        batch.draw(
                (TextureRegion) enemyFirstWaspAnimation.getKeyFrame(runTime),
                enemy.getPosition().x,
                enemy.getPosition().y,
                16,
                16
        );
    }

    private void drawWaspDeath(float runTime) {
        batch.draw(
                (TextureRegion) enemyFirstWaspDeathAnimation.getKeyFrame(runTime),
                250,
                100,
                16,
                16
        );
    }*/
}

