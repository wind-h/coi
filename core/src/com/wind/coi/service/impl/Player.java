package com.wind.coi.service.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.wind.coi.service.Renderable;
import com.wind.coi.service.Updateable;

public class Player implements Updateable, Renderable {

    // 玩家纹理
    private Texture texture;
    // 玩家位置
    private Vector2 position;
    // 玩家速度
    private Vector2 velocity;
    // 游戏相机
    private OrthoCamera camera;
    private float speed = 20f;
    private float textureWidth;
    private float textureHeight;

    /**
     * 构造函数初始化玩家对象。
     * @param texture 玩家纹理
     * @param camera 游戏相机
     */
    public Player(Texture texture, OrthoCamera camera) {
        this.texture = texture;
        this.camera = camera;
        position = new Vector2();
        velocity = new Vector2();
        //缓存纹理宽度和高度，避免每次渲染时都重新计算
        this.textureWidth = texture.getWidth();
        this.textureHeight = texture.getHeight();
    }

    /**
     * 更新玩家的速度。
     * @param x 往x轴方向的移动速度
     * @param y 往y轴方向的移动速度
     */
    public void move(float x, float y) {
        velocity.x += x * speed;
        velocity.y += y * speed;
    }

    /**
     * 更新玩家的位置，根据速度和时间delta进行计算。
     * 同时更新相机位置，保持玩家在屏幕中央。
     * @param delta 时间间隔
     */
    @Override
    public void update(float delta) {
        // 根据速度和时间更新玩家位置
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
        //更新相机位置，保持玩家在屏幕中央
        camera.getCamera().position.x = position.x;
        camera.getCamera().position.y = position.y;
        //考虑平滑过渡的逻辑（示例，根据实际需求调整）
        camera.update(delta);
    }

    /**
     * 渲染玩家纹理。
     * @param batch SpriteBatch，用于绘制纹理
     */
    @Override
    public void render(SpriteBatch batch) {
        // 根据玩家位置和纹理大小渲染玩家纹理
        batch.draw(texture, position.x -  textureWidth / 2f, position.y - textureHeight / 2f);
    }


    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public OrthoCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthoCamera camera) {
        this.camera = camera;
    }
}
