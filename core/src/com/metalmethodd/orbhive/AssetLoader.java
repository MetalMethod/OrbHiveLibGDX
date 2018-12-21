package com.metalmethodd.orbhive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {
    private static Texture sprites;

    public static void load() {
        sprites = new Texture(Gdx.files.internal("sprites.png"));

        // filters for pixel art, nearest neighbor scaling up
        sprites.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
    }

    // We must dispose of the sprites whens we are finished.
    public static void dispose() {
        sprites.dispose();
    }

    public static Texture getSprites(){
        return sprites;
    }

}