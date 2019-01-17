package com.metalmethodd.orbhive.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Background extends AbstractGameObject{
    public Background(Vector2 position, int width, int height) {
        super(position, width, height);
    }

    public void setPosition(Vector2 position){
        this.position.set(position);
    }
}

