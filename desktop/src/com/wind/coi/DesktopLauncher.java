package com.wind.coi;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		final float width = 320;
		final float ratio = width / 480.0f;
		final float scale = 1.0f;
		int realWidth = (int) (width * scale);
		int realHeight = (int) (width / ratio * scale);
		config.setWindowedMode(realWidth, realHeight);
		config.setResizable(false);
		config.setForegroundFPS(60);
		config.setTitle("2048");
		new Lwjgl3Application(new MainGame(), config);
	}
}
