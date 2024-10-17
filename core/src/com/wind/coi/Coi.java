package com.wind.coi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class Coi extends ApplicationAdapter {
	SpriteBatch batch;
	private Array<Texture> textures;
	private int[][] mapData;
	@Override
	public void create () {
		batch = new SpriteBatch();
		// 加载纹理
		textures = new Array<>();
		textures.add(new Texture("grass.png")); // 草地
		textures.add(new Texture("stone.png")); // 石头
		textures.add(new Texture("water.png")); // 水

		// 生成160x160的地图数据
		mapData = generateMapData(160, 160);
	}

	@Override
	public void render () {
		// 清屏
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		// 遍历地图数据并绘制瓦片
		for (int y = 0; y < mapData.length; y++) {
			for (int x = 0; x < mapData[y].length; x++) {
				int tileIndex = mapData[y][x];
				if (tileIndex >= 0 && tileIndex < textures.size) {
					batch.draw(textures.get(tileIndex), x * 32, y * 32, 32, 32);
				}
			}
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Texture texture : textures) {
			texture.dispose();
		}
	}

	private int[][] generateMapData(int width, int height) {
		int[][] map = new int[height][width];
		float scale = 0.1f; // 控制噪声的频率

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// 计算Perlin噪声值
				double noiseValue = PerlinNoiseGenerator.perlin(x * scale, y * scale);

				// 根据噪声值确定地形类型
				if (noiseValue < 0.3) {
					map[y][x] = 2; // 水
				} else if (noiseValue < 0.6) {
					map[y][x] = 0; // 草地
				} else {
					map[y][x] = 1; // 石头
				}
			}
		}


		return map;
	}
}
