package com.metalmethodd.orbhive.gameobjects.background;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.gameobjects.AbstractGameObject;

public class Background extends AbstractGameObject {

    public boolean draw;
    public Background(Vector2 position, int width, int height) {

        super(position, width, height);
        draw = true;
    }

    public void setPosition(float x, float y){
        this.position.set(x, y);
    }

}

