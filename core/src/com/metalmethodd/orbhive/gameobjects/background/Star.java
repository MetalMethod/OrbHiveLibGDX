package com.metalmethodd.orbhive.gameobjects.background;

import com.badlogic.gdx.math.Vector2;
import com.metalmethodd.orbhive.gameobjects.AbstractGameObject;

public class Star extends AbstractGameObject {
    private float alpha;

    public Star(int x, int y, float alpha) {
        super(new Vector2(x, y), 1, 1);
        this.alpha = alpha;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setPosition(float x, float y){
        this.position.set(x, y);
    }

    public void increaseSize(double n){
        width += n;
        height += n;
    }
}

