package com.metalmethodd.orbhive.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.enemies.BrainSmall;
import com.metalmethodd.orbhive.gameobjects.enemies.SimpleEnemy;
import com.metalmethodd.orbhive.gameobjects.enemies.Wasp;

//TODO find solution to build html module using this import
//import java.util.concurrent.ThreadLocalRandom;

public class EnemyFactory {

    public static int getRandomInt(int min, int max) {
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        // return ThreadLocalRandom.current().nextInt(min, max + 1);

        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static Wasp createWasp() {
        float randomY = (float) (getRandomInt(10, Constants.GAME_HEIGHT-10));
        Vector2 position = new Vector2(Constants.GAME_WIDTH, randomY);

        return new Wasp(position);
    }

    public static SimpleEnemy createSimpleEnemy() {
        float randomY = (float) (getRandomInt(20, Constants.GAME_HEIGHT-20));
        Vector2 position = new Vector2(Constants.GAME_WIDTH, randomY);

        return new SimpleEnemy(position);
    }

    public static BrainSmall createBrain() {
        float randomY = (float) (getRandomInt(20, Constants.GAME_HEIGHT-20));
        Vector2 position = new Vector2(Constants.GAME_WIDTH, randomY);

        return new BrainSmall(position);
    }

}
