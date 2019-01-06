package com.metalmethodd.orbhive;

import com.badlogic.gdx.math.Vector2;

public class EnemyFactory {


public static SimpleEnemy createEnemy(){

    float randomY = (float) ((Math.random() * Constants.SCREEN_HEIGHT));
    Vector2 position = new Vector2(Constants.SCREEN_WIDTH, randomY);

    return new SimpleEnemy(position);
}


}
