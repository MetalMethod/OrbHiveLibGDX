package com.metalmethodd.orbhive.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.metalmethodd.orbhive.OrbHiveGame;

public class LevelOne implements Screen {

    private SpriteBatch batch;
    private OrbHiveGame game;

    public LevelOne(OrbHiveGame game){
        this.game = game;
        this.batch = game.batch;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        System.out.println("level one");

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
