package com.metalmethodd.orbhive.gameobjects.enemies;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.AbstractGameObject;

import static com.metalmethodd.orbhive.Constants.SIMPLE_ENEMY_HEIGHT;
import static com.metalmethodd.orbhive.Constants.SIMPLE_ENEMY_WIDTH;

public class SimpleEnemy extends AbstractGameObject {

    public SimpleEnemy(Vector2 position) {
        super(position,SIMPLE_ENEMY_WIDTH, SIMPLE_ENEMY_HEIGHT );
        init();
    }


}
