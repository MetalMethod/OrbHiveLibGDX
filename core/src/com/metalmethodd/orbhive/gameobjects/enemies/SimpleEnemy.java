package com.metalmethodd.orbhive.gameobjects.enemies;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.AbstractGameObject;

import static com.metalmethodd.orbhive.Constants.SIMPLE_ENEMY_HEIGHT;
import static com.metalmethodd.orbhive.Constants.SIMPLE_ENEMY_WIDTH;

public class SimpleEnemy extends AbstractGameObject implements Enemy {

    private final EnemyType enemyType;

    public SimpleEnemy(Vector2 position) {
        super(position,SIMPLE_ENEMY_WIDTH, SIMPLE_ENEMY_HEIGHT );
        init();
        enemyType = EnemyType.SIMPLE_ENEMY;
        speed = Constants.SIMPLE_ENEMY_SPEED;
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

    @Override
    public boolean isHit() {
        return false;
    }

    @Override
    public void setHit(boolean state) {

    }

}
