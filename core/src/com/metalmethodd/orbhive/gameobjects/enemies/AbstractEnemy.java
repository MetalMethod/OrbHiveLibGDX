package com.metalmethodd.orbhive.gameobjects.enemies;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.AbstractGameObject;
import com.metalmethodd.orbhive.gameobjects.EnemyFactory;

public class AbstractEnemy extends AbstractGameObject implements Enemy {

    protected EnemyType enemyType;
    protected boolean isHitState;
    protected float deathAnimationTime;

    public AbstractEnemy(Vector2 position, int width, int height) {
        super(position, width, height);
        init();
        isHitState = false;
        deathAnimationTime = 0;
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

    public float getDeathAnimationTime() {
        return deathAnimationTime;
    }

    public void setDeathAnimationTime(float deathAnimationTime) {
        this.deathAnimationTime = deathAnimationTime;
    }

    protected int getRandomSpeed() {
        return EnemyFactory.getRandomInt(Constants.WASP_MINIMUM_SPEED, Constants.WASP_MAXIMUM_SPEED);
    }
}
