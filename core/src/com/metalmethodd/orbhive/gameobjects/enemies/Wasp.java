package com.metalmethodd.orbhive.gameobjects.enemies;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.gameobjects.EnemyFactory;
import static com.metalmethodd.orbhive.Constants.*;

public class Wasp extends AbstractEnemy{

    private int deathDirection;

    public Wasp(Vector2 position) {
        super(position, WASP_WIDTH, WASP_HEIGHT);
        init();
        enemyType = EnemyType.WASP;
        speed = getRandomSpeed();
        isHitState = false;
        deathDirection = EnemyFactory.getRandomInt(0, 1);
    }

    /**
     * Must always call updateBoundingRectangle()
     * in the end after of all  methods calls
     */
    public void update() {
        int coin = EnemyFactory.getRandomInt(0, 1);
        int y = 1;

        // movement if wasp in not hit
        if (!isHitState) {
            position.add(-speed, 0);

            if (coin == 0) {
                y = 1;
            }
            if (coin == 1) {
                y = -1;
            }
            position.add(0, y);
        }

        //movement if wasp is hit
        if (isHit()) {
            if(deathDirection == 0){
                y = 1;
            }
            if(deathDirection == 1){
                y = -1;
            }
            position.add(speed, y);
        }

        //must call updateBoundingRectangle() after all methods
        updateBoundingRectangle();
    }

}
