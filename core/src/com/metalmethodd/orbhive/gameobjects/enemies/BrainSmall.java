package com.metalmethodd.orbhive.gameobjects.enemies;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.AbstractGameObject;

import static com.metalmethodd.orbhive.Constants.*;

public class BrainSmall extends AbstractGameObject implements Enemy {

    private final EnemyType enemyType;

    public BrainSmall(Vector2 position) {
        super(position,BRAIN_SMALL_WIDTH, BRAIN_SMALL_HEIGHT );
        init();
        enemyType = EnemyType.BrainSmall;
        speed = Constants.BRAIN_SMALL_SPEED;
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
        position.x -= speed;

        //must call updateBoundingRectangle() after all methods
        updateBoundingRectangle();
    }

}
