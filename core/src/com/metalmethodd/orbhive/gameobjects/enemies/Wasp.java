package com.metalmethodd.orbhive.gameobjects.enemies;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.gameobjects.AbstractGameObject;

import static com.metalmethodd.orbhive.Constants.*;


public class Wasp extends AbstractGameObject {

    public Wasp(Vector2 position) {
        super(position, WASP_WIDTH, WASP_HEIGHT);
        init();
        speed = WASP_ENEMY_SPEED;
    }
    /**
     * Must always call updateBoundingRectangle()
     * in the end after of all  methods calls
     */
    public void update() {
        position.x -= speed;

        //must call updateBoundingRectangle() after all methods
        updateBoundingRectangle();
    }



}
