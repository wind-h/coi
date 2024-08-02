package com.wind.coi.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Random;

public class Enemy implements Renderable {
    Texture texture;
    Vector2 position;
    Vector2 velocity;

    public Enemy(Texture texture) {
        this.texture = texture;
        this.position = new Vector2();
        this.velocity = new Vector2();
    }

    @Override
    public void update(float delta) {
        // 更新敌人位置
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;

        // 边界检查，如果敌人离开屏幕，重置其位置
        if (position.x > Gdx.graphics.getWidth() || position.x < 0 ||
                position.y > Gdx.graphics.getHeight() || position.y < 0) {
            reset();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - texture.getWidth() / 2f,
                position.y - texture.getHeight() / 2f);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    public void reset() {
        // 重新设置敌人的位置和速度
        Random random = new Random();
        position.set(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight());
        velocity.set(random.nextFloat() * 100 - 50, random.nextFloat() * 100 - 50);
    }
}