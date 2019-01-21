package com.metalmethodd.orbhive.gameobjects.background;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.AbstractGameObject;

public class Moon extends AbstractGameObject {
    public boolean draw;

    public Moon(Vector2 position, int width, int height) {
        super(position, width, height);
        width = Constants.MOON_BIG_SIZE;
        height = Constants.MOON_BIG_SIZE;
        draw = true;
    }

    public void setPosition(int x, int y){
        this.position.set(x, y);
    }

    public void increaseSize(double n){
        width += n;
        height += n;
    }
}

