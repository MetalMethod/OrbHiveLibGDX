package com.metalmethodd.orbhive.gameobjects.enemies;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Generic type for all Enemies,
 * used for handling the enemies array on levels
 *
 */
public interface Enemy {

    EnemyType getEnemyType();

    void update();

    Rectangle getBoundingRectangle();

    Vector2 getPosition();

    float getWidth();

    float getHeight();

    boolean isHit();

    void setHit(boolean state);
}

