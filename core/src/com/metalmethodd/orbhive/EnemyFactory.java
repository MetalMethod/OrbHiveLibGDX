package com.metalmethodd.orbhive;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.enemys.SimpleEnemy;
import com.metalmethodd.orbhive.enemys.Wasp;

public class EnemyFactory {


    public static SimpleEnemy createSimpleEnemy() {

        float randomY = (float) ((Math.random() * Constants.SCREEN_HEIGHT));
        Vector2 position = new Vector2(Constants.SCREEN_WIDTH, randomY);

        return new SimpleEnemy(position);
    }

    public static Wasp createWasp() {

        float randomY = (float) ((Math.random() * Constants.SCREEN_HEIGHT));
        Vector2 position = new Vector2(Constants.SCREEN_WIDTH, randomY);

        return new Wasp(position);
    }


}
