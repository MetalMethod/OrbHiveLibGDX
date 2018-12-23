package com.metalmethodd.orbhive.scenes;

import com.metalmethodd.orbhive.OrbHiveGame;

public class LevelOne extends BaseLevel {


    public LevelOne(OrbHiveGame game) {
        super(game);
    }

    public void render(float delta) {
        checkExitGame();
        drawBackgroundColor();

        textureHandler.drawBgLevelOne();
        //drawHalfDownBgTexture();

        //drawPlayer(runTime, playerState());


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
