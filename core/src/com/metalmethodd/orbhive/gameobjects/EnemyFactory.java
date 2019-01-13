package com.metalmethodd.orbhive.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.enemies.SimpleEnemy;
import com.metalmethodd.orbhive.gameobjects.enemies.Wasp;

public class EnemyFactory {


    public static SimpleEnemy createSimpleEnemy() {

        float randomY = (float) ((Math.random() * Constants.GAME_HEIGHT));
        Vector2 position = new Vector2(Constants.GAME_WIDTH, randomY);

        return new SimpleEnemy(position);
    }

    public static Wasp createWasp() {

        float randomY = (float) ((Math.random() * Constants.GAME_HEIGHT));
        Vector2 position = new Vector2(Constants.GAME_WIDTH, randomY);

        return new Wasp(position);
    }


}
