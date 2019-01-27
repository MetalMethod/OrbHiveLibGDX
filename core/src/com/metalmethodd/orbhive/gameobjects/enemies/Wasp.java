package com.metalmethodd.orbhive.gameobjects.enemies;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.AbstractGameObject;
import com.metalmethodd.orbhive.gameobjects.EnemyFactory;

import static com.metalmethodd.orbhive.Constants.*;

public class Wasp extends AbstractGameObject implements Enemy {

    private EnemyType enemyType;
    private boolean isHitState;

    public Wasp(Vector2 position) {
        super(position, WASP_WIDTH, WASP_HEIGHT);
        init();
        enemyType = EnemyType.WASP;
        speed = getRandomSpeed();
        isHitState = false;
    }

    @Override
    public EnemyType getEnemyType() {
        return enemyType;
    }

    /**
     * Must always call updateBoundingRectangle()
     * in the end after of all  methods calls
     */
    public void update() {
        int coin = EnemyFactory.getRandomInt(1, 2);
        int x = 2;
        int y = 1;

        // movement if wasp in not hit
        if (!isHitState) {
            position.add(-speed, 0);
        }

        if (coin == 1) {
            y = 1;
        }
        if (coin == 2) {
            y = -1;
        }
        position.add(0, y);

        //movement if wasp is hit
        if (isHitState) {
            if (coin == 1) {
                y = 1;
            }
            if (coin == 2) {
                y = -1;
            }
                position.add(speed, y);

        }

        //must call updateBoundingRectangle() after all methods
        updateBoundingRectangle();
    }

    @Override
    public boolean isHit() {
        return isHitState;

    }

    @Override
    public void setHit(boolean state) {
        isHitState = state;
    }

    private int getRandomSpeed() {
        return EnemyFactory.getRandomInt(Constants.WASP_MINIMUM_SPEED, Constants.WASP_MAXIMUM_SPEED);
    }

}
