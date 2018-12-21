package com.metalmethodd.orbhive.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.metalmethodd.orbhive.OrbHiveGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new OrbHiveGame(), config);

		config.title = "OrbHive";
		config.width = 455 *2;
		config.height = 256 *2;
	}
}
