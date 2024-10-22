package com.wind.coi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.wind.coi.core.Core;

public class Coi extends ApplicationAdapter {

	private OrthographicCamera camera;
	SpriteBatch batch;
	Texture img;
	Sprite sprite;
	
	@Override
	public void create () {
		Core.app.log("", "创建");

		float width = Core.graphics.getWidth();
		float height = Core.graphics.getHeight();

		//camera = new OrthographicCamera(width, height);
		batch = new SpriteBatch();

		img = new Texture("badlogic.jpg");
		//img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		//TextureRegion region = new TextureRegion(img, 0, 0, 512, 275);

		//sprite = new Sprite(img);
		//sprite.setSize(0.9f, 0.9f * (sprite.getHeight() / sprite.getWidth()));
		//sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		//sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
	}

	@Override
	public void resize (int width, int height) {
		Core.app.log("", "重设大小width:" + width + " height:" + height);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		//batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	@Override
	public void pause () {
		Core.app.log("", "暂停");
	}

	@Override
	public void resume () {
		Core.app.log("", "继续");
	}
	
	@Override
	public void dispose () {
		Core.app.log("", "销毁");
		batch.dispose();
		img.dispose();
	}
}
