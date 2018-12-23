package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.GameInputHandler;
import com.metalmethodd.orbhive.OrbHiveGame;
import com.metalmethodd.orbhive.Player;

public class LevelOne extends BaseLevel {

    private Player player;

    public LevelOne(OrbHiveGame game) {
        super(game);

        player = new Player(new Vector2(100,100));

        gameInputHandler = new GameInputHandler(player);
    }

    public void render(float delta) {
        runTime += delta;
        player.update(delta);

        checkExitGame();
        drawBackgroundColor();

        textureHandler.drawBgLevelOne();

        textureHandler.drawPlayer(player, runTime);

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
    }
}
