package com.metalmethodd.orbhive.gameobjects.enemies;

import java.util.concurrent.ThreadLocalRandom;
import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.AbstractGameObject;
import com.metalmethodd.orbhive.gameobjects.EnemyFactory;

import static com.metalmethodd.orbhive.Constants.*;

public class Wasp extends AbstractGameObject implements Enemy{

    private EnemyType enemyType;

    public Wasp(Vector2 position) {
        super(position, WASP_WIDTH, WASP_HEIGHT);
        init();
        enemyType = EnemyType.Wasp;
        speed = getRandomSpeed();
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

    private int getRandomSpeed(){
        return EnemyFactory.getRandomInt(Constants.WASP_MINIMUM_SPEED, Constants.WASP_MAXIMUM_SPEED);
    }

}
