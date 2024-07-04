package com.wind.coi.study;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SpriteStudy extends ApplicationAdapter {

    SpriteBatch batch;

    Texture img;

    Sprite sprite;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        sprite = new Sprite(img, 0, 0, 128, 128);
        // 位置
        sprite.setPosition(64, 128);
        // 设置 旋转 和 缩放 的 起点
        sprite.setOrigin(0, 0);
        // 设置旋转角度
        sprite.setRotation(15.0f);
        // 设置精灵的 X 和 Y 轴方向的缩放比
        sprite.setScale(0.5f, 0.5f);
        // 设置精灵在水平方向取镜像, 竖直方不取镜像
        sprite.flip(true, false);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
