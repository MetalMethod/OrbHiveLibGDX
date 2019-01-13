package com.metalmethodd.orbhive.gameobjects.enemies;

import java.util.concurrent.ThreadLocalRandom;
import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.AbstractGameObject;
import static com.metalmethodd.orbhive.Constants.*;

public class Wasp extends AbstractGameObject {

    public Wasp(Vector2 position) {
        super(position, WASP_WIDTH, WASP_HEIGHT);
        init();
        speed = getRandomSpeed();
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
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNumber = ThreadLocalRandom.current().nextInt(Constants.WASP_MINIMUM_SPEED, Constants.WASP_MAXIMUM_SPEED + 1);

        return randomNumber;
    }

}
