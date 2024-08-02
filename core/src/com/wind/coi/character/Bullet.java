package com.wind.coi.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Bullet implements Renderable {

    private Texture texture;

    private TextureRegion textureRegion;

    // 玩家位置
    private Vector2 position;

    // 玩家速度
    private Vector2 velocity;

    private float buWidth;

    private float buHeight;

    private float scale = 0.5f;

    private GameWorld gameWorld;

    public Bullet(Texture texture) {
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        position = new Vector2();
        velocity = new Vector2();
        this.buWidth = texture.getWidth() * scale;
        this.buHeight = texture.getHeight() * scale;
    }

    public Bullet(Texture texture, Vector2 position, Vector2 velocity) {
        this.position = position;
        this.velocity = velocity;
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.buWidth = texture.getWidth() * scale;
        this.buHeight = texture.getHeight() * scale; // 缓
    }

    public Bullet(Texture texture, Vector2 position, Vector2 velocity, GameWorld gameWorld) {
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.position = position;
        this.velocity = velocity;
        this.gameWorld = gameWorld;
    }

    @Override
    public void update(float delta) {
        // 更新子弹位置
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
        // 简单示例：检测子弹是否超出边界（以屏幕尺寸为例，需要根据实际情况调整）
        if (position.x < -buWidth / 2f || position.x > 1280 + buWidth / 2f ||
                position.y < -buHeight / 2f || position.y > 720 + buHeight / 2f) {
            gameWorld.removeRenderable(this);
        }
    }



    @Override
    public void render(SpriteBatch batch) {
        // 缩放因子，例如0.5表示子弹大小为原来的一半
        float scale = 0.5f;

        // 获取子弹纹理的宽度和高度
        float buWidth = texture.getWidth() * scale;
        float buHeight = texture.getHeight() * scale;

        // 使用缩放参数来绘制子弹，直接使用缓存的宽度和高度
        batch.draw(textureRegion, position.x - buWidth / 2f, position.y - buHeight / 2f,
                buWidth / 2f, buHeight / 2f, buWidth, buHeight, 1, 1, 0);
    }

    @Override
    public void dispose() {
        textureRegion.getTexture().dispose();
    }
}