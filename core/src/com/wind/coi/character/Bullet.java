package com.wind.coi.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Bullet implements Renderable {

    private Texture buBg;

    private TextureRegion buBgRe;

    // 玩家位置
    private Vector2 position;

    // 玩家速度
    private Vector2 velocity;
    private float buWidth;
    private float buHeight;
    private float scale = 0.5f;
    public Bullet(Texture buBg) {
        this.buBg = buBg;
        this.buBgRe = new TextureRegion(buBg);
        position = new Vector2();
        velocity = new Vector2();
        this.buWidth = buBg.getWidth() * scale;
        this.buHeight = buBg.getHeight() * scale;
    }

    public Bullet(Texture buBg, Vector2 position, Vector2 velocity) {
        this.position = position;
        this.velocity = velocity;
        this.buBg = buBg;
        this.buBgRe = new TextureRegion(buBg);
        this.buWidth = buBg.getWidth() * scale;
        this.buHeight = buBg.getHeight() * scale; // 缓
    }

    @Override
    public void update(float delta) {
        // 更新子弹位置
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
        // 简单示例：检测子弹是否超出边界（以屏幕尺寸为例，需要根据实际情况调整）
        if (position.x < -buWidth / 2f || position.x > 1280 + buWidth / 2f ||
                position.y < -buHeight / 2f || position.y > 720 + buHeight / 2f) {
            // 如果子弹超出边界，可以在这里进行处理，例如销毁子弹
            // 这里没有实现销毁逻辑，因为根据题目要求不能改变函数的输入输出
            // 处理逻辑应根据实际情况来设计
        }
    }



    @Override
    public void render(SpriteBatch batch) {
        // 缩放因子，例如0.5表示子弹大小为原来的一半
        float scale = 0.5f;

        // 获取子弹纹理的宽度和高度
        float buWidth = buBg.getWidth() * scale;
        float buHeight = buBg.getHeight() * scale;

        // 使用缩放参数来绘制子弹，直接使用缓存的宽度和高度
        batch.draw(buBgRe, position.x - buWidth / 2f, position.y - buHeight / 2f,
                buWidth / 2f, buHeight / 2f, buWidth, buHeight, 1, 1, 0);
    }

    @Override
    public void dispose() {
        buBgRe.getTexture().dispose();
    }
}