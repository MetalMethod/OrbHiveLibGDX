package com.metalmethodd.orbhive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {
    private static Texture sprites;
    private static Texture splashScreen;
    private static Texture gameoverScreen;

    public static void load() {
        sprites = new Texture(Gdx.files.internal("sprites.png"));

        // filters for pixel art, nearest neighbor scaling up
        sprites.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        splashScreen = new Texture(Gdx.files.internal("orbhive_title_256x256.png"));
        gameoverScreen = new Texture(Gdx.files.internal("orbhive_title_light_256x256.png"));

        splashScreen.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        gameoverScreen.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
    }

    // We must dispose of the sprites whens we are finished.
    public static void dispose() {
        sprites.dispose();
        splashScreen.dispose();
        gameoverScreen.dispose();
    }

    public static Texture getSprites(){
        return sprites;
    }

    public static Texture getGameoverScreen() {
        return gameoverScreen;
    }

    public static Texture getSplashScreen() {
        return splashScreen;
    }
}