package com.metalmethodd.orbhive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.metalmethodd.orbhive.scenes.SplashScreen;

public class OrbHiveGame extends Game {
    public SpriteBatch batch;

    SplashScreen splash;

    @Override
    public void create() {
        batch = new SpriteBatch();
        splash = new SplashScreen(this);
        this.setScreen(splash);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public SplashScreen getSplash() {
        return splash;
    }
}
