package com.metalmethodd.orbhive.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.metalmethodd.orbhive.OrbHiveGame;

import static com.metalmethodd.orbhive.Constants.GAME_HEIGHT;
import static com.metalmethodd.orbhive.Constants.GAME_WIDTH;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new OrbHiveGame(), config);

        config.title = "OrbHive";
        config.width = GAME_WIDTH * 2;
        config.height = GAME_HEIGHT * 2;

    }
}
