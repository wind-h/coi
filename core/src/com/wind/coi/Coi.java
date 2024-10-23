package com.wind.coi;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.wind.coi.core.Core;

public class Coi implements ApplicationListener {

	public final static String TAG = Coi.class.getName();

	private WorldController worldController;

	private WorldRenderer worldRenderer;

	private boolean paused;
	
	@Override
	public void create () {
		Core.app.log(TAG, "创建");
		Core.app.setLogLevel(Application.LOG_DEBUG);
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		paused = false;
	}

	@Override
	public void resize (int width, int height) {
		Core.app.log(TAG, "重设大小width:" + width + " height:" + height);
		worldRenderer.resize(width, height);
	}

	@Override
	public void render () {
		if (!paused) {
			worldController.update(Core.app.getGraphics().getDeltaTime());
		}
		ScreenUtils.clear(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);
		worldRenderer.render();
	}

	@Override
	public void pause () {
		Core.app.log(TAG, "暂停");
		paused = true;
	}

	@Override
	public void resume () {
		Core.app.log(TAG, "继续");
		paused = false;
	}
	
	@Override
	public void dispose () {
		Core.app.log(TAG, "销毁");
		worldRenderer.dispose();
	}
}
