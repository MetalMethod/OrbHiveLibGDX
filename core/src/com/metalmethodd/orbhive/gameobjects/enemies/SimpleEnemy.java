package com.metalmethodd.orbhive.gameobjects.enemies;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import static com.metalmethodd.orbhive.Constants.SIMPLE_ENEMY_HEIGHT;
import static com.metalmethodd.orbhive.Constants.SIMPLE_ENEMY_WIDTH;

public class SimpleEnemy extends AbstractEnemy{

    public SimpleEnemy(Vector2 position) {
        super(position,SIMPLE_ENEMY_WIDTH, SIMPLE_ENEMY_HEIGHT );
        init();
        enemyType = EnemyType.SIMPLE_ENEMY;
        speed = Constants.SIMPLE_ENEMY_SPEED;
    }

    /**
     * Must always call updateBoundingRectangle()
     * in the end after of all  methods calls
     */
    public void update() {
        position.x -= speed;

        if (!isHit()) {
            position.x -= speed;
        } else {
            position.add(speed * 2, 0);
        }

        //must call updateBoundingRectangle() after all methods
        updateBoundingRectangle();
    }

}
