package com.metalmethodd.orbhive.gameobjects.enemies;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.AbstractGameObject;
import com.metalmethodd.orbhive.gameobjects.EnemyFactory;

import static com.metalmethodd.orbhive.Constants.WASP_HEIGHT;
import static com.metalmethodd.orbhive.Constants.WASP_WIDTH;

public class AbstractEnemy extends AbstractGameObject implements Enemy {

    protected EnemyType enemyType;
    protected boolean isHitState;

    public AbstractEnemy(Vector2 position, int width, int height) {
        super(position, width, height);
        init();
        isHitState = false;
    }


    @Override
    public void update() {

    }

    @Override
    public EnemyType getEnemyType() {
        return enemyType;
    }

    @Override
    public boolean isHit() {
        return isHitState;
    }

    @Override
    public void setHit(boolean state) {
        isHitState = state;
    }

    protected int getRandomSpeed() {
        return EnemyFactory.getRandomInt(Constants.WASP_MINIMUM_SPEED, Constants.WASP_MAXIMUM_SPEED);
    }
}
