package com.metalmethodd.orbhive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetLoader {
    private static Texture sprites;
    private static Texture sprites2;
    private static Texture splashScreen;
    private static Texture gameoverScreen;

    private static FileHandle fontFile, fontImage;
    private static BitmapFont textFont;


    public static void load() {
        sprites = new Texture(Gdx.files.internal("sprites.png"));
        sprites2 = new Texture(Gdx.files.internal("sprites2.png"));

        // filters for pixel art, nearest neighbor scaling up
        sprites.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        sprites2.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        splashScreen = new Texture(Gdx.files.internal("orbhive_title_256x256.png"));
        gameoverScreen = new Texture(Gdx.files.internal("orbhive_title_light_256x256.png"));

        splashScreen.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        gameoverScreen.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
    }

    // We must dispose of the sprites whens we are finished.
    public static void dispose() {
        sprites.dispose();
        sprites2.dispose();
        splashScreen.dispose();
        gameoverScreen.dispose();

    }

    public static Texture getSprites(){
        return sprites;
    }

    public static Texture getSprites2(){
        return sprites2;
    }

    public static Texture getGameoverScreen() {
        return gameoverScreen;
    }

    public static Texture getSplashScreen() {
        return splashScreen;
    }

    public static BitmapFont createFont() {
        fontFile = Gdx.files.internal("fonts/font.fnt");
        fontImage = Gdx.files.internal("fonts/font.png");
        return textFont = new BitmapFont(fontFile, fontImage, true);
    }
}