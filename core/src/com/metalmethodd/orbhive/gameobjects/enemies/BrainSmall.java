package com.metalmethodd.orbhive.gameobjects.enemies;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;

import static com.metalmethodd.orbhive.Constants.*;

public class BrainSmall extends AbstractEnemy {

    public BrainSmall(Vector2 position) {
        super(position, BRAIN_SMALL_WIDTH, BRAIN_SMALL_HEIGHT);
        init();
        enemyType = EnemyType.BRAIN_SMALL;
        speed = Constants.BRAIN_SMALL_SPEED;
    }


    /**
     * Must always call updateBoundingRectangle()
     * in the end after of all  methods calls
     */
    public void update() {
        if (!isHit()) {
            position.x -= speed;
        } else {
            position.add(speed, 0);
        }

        //must call updateBoundingRectangle() after all methods
        updateBoundingRectangle();
    }

}
