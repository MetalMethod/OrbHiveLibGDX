package com.metalmethodd.orbhive.gameobjects.background;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.AbstractGameObject;

public class Cloud extends AbstractGameObject {
    public boolean draw;

    public Cloud(Vector2 position, int width, int height) {
//        super(position, 95, 14);
        super(position, width, height);
        draw = false;
    }

    public void setPosition(float x, float y){
        this.position.set(x, y);
    }

    public void increaseSize(double n){
        width += n;
        height += n;
    }
}

