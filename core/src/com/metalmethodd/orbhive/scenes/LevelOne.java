package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.GameInputHandler;
import com.metalmethodd.orbhive.OrbHiveGame;
import com.metalmethodd.orbhive.Player;


public class LevelOne extends BaseLevel {

    private Player player;

    private Rectangle bulletOne;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public LevelOne(OrbHiveGame game) {
        super(game);

        player = new Player(new Vector2(100,100));

        gameInputHandler = new GameInputHandler(player);

        bulletOne = new Rectangle(player.getPosition().x * 2, player.getPosition().y * 2, 8, 8);
    }

    public void render(float delta) {
        runTime += delta;
        player.update(delta);

        checkExitGame();
        drawBackgroundColor();

        textureHandler.drawBgLevelOne();

        textureHandler.drawPlayer(player, runTime);
        textureHandler.drawPlayerBoundingRect(player);

        if (player.isShooting()){


            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(bulletOne.x, bulletOne.y , bulletOne.width, bulletOne.height);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.end();

        }

        bulletOne.x += 5;

        if (bulletOne.x >= Constants.SCREEN_WIDTH * 2) {
            player.setShooting(false);
            bulletOne.set(player.getPosition().x * 2, player.getPosition().y * 2, 8, 8);
            

        }




        /*
        if player overlaps wasp
            do shit - player and wasp methods

        Intersector.overlaps(player_rectangle , wasp_rectangle)
                */

    }

    @Override
    /**
     * Dispose all texture, sprites, animations, background, etc
     */
    public void dispose() {
        // dispose images here
        batch.dispose();
        shapeRenderer.dispose();
    }
}
