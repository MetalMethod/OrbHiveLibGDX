package com.metalmethodd.orbhive.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.enemies.SimpleEnemy;
import com.metalmethodd.orbhive.gameobjects.enemies.Wasp;

import java.util.concurrent.ThreadLocalRandom;

public class EnemyFactory {

    public static SimpleEnemy createSimpleEnemy() {

        float randomY = (float) (getRandomInt(1, Constants.GAME_HEIGHT));
        Vector2 position = new Vector2(Constants.GAME_WIDTH, randomY);

        return new SimpleEnemy(position);
    }

    public static Wasp createWasp() {

        float randomY = (float) (getRandomInt(1, Constants.GAME_HEIGHT));
        Vector2 position = new Vector2(Constants.GAME_WIDTH, randomY);

        return new Wasp(position);
    }

    public static int getRandomInt(int min, int max){
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
