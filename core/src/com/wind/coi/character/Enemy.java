package com.wind.coi.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.wind.coi.enums.TypeEnum;

import java.util.Random;

public class Enemy implements Renderable {

    Texture texture;

    Vector2 position;

    Vector2 velocity;

    private GameWorld gameWorld;

    public Enemy(Texture texture, GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.texture = texture;
        this.position = new Vector2();
        this.velocity = new Vector2();
    }

    @Override
    public void update(float delta) {
        // 更新敌人位置
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;

        Array<Bullet> bulletList = gameWorld.getBulletList();
        for (Bullet bullet : bulletList) {
            if (isColliding(bullet, this)) {
                gameWorld.removeRenderable(bullet);
                gameWorld.removeRenderable(this);
            }
        }

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

    @Override
    public int getType() {
        return TypeEnum.ENEMY.ordinal();
    }

    // 碰撞检测的简单实现，这里仅做示例，实际应用中可能需要更复杂的算法
    private boolean isColliding(Bullet bullet, Enemy enemy) {
        // 使用AABB（Axis-Aligned Bounding Box）碰撞检测
        // 预计算以提高性能，避免在判断中重复计算
        float bulletLeft = bullet.position.x - bullet.buWidth / 2f;
        float bulletRight = bullet.position.x + bullet.buWidth / 2f;
        float bulletTop = bullet.position.y - bullet.buHeight / 2f;
        float bulletBottom = bullet.position.y + bullet.buHeight / 2f;

        float enemyLeft = enemy.position.x - enemy.texture.getWidth() / 2f;
        float enemyRight = enemy.position.x + enemy.texture.getWidth() / 2f;
        float enemyTop = enemy.position.y - enemy.texture.getHeight() / 2f;
        float enemyBottom = enemy.position.y + enemy.texture.getHeight() / 2f;

        // 分别检查水平和垂直方向是否没有碰撞
        boolean notCollidingHorizontally = bulletRight < enemyLeft || bulletLeft > enemyRight;
        boolean notCollidingVertically = bulletBottom < enemyTop || bulletTop > enemyBottom;

        // 如果两个物体在水平或垂直方向上没有碰撞，则总体上没有碰撞
        return !(notCollidingHorizontally || notCollidingVertically);
    }

    public void reset() {
        // 重新设置敌人的位置和速度
        Random random = new Random();
        position.set(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight());
        velocity.set(random.nextFloat() * 100 - 50, random.nextFloat() * 100 - 50);
    }
}