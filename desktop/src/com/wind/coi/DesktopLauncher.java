package com.wind.coi;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	private static boolean rebuildAtlas = true;

	private static boolean drawDebugOutline = true;

	public static void main (String[] arg) {
		if (rebuildAtlas) {
			TexturePacker.Settings settings = new TexturePacker.Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			settings.debug = drawDebugOutline;
			settings.duplicatePadding = true;
			TexturePacker.process(settings, "assets/game", "assets", "coi.pack");
		}
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("coi");
		config.setWindowedMode(800, 480);
		new Lwjgl3Application(new Coi(), config);
	}
}
