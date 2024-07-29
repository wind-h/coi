package com.wind.coi.service.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.wind.coi.constants.GameConstants;
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


    }

    /**
     * 更新玩家的速度。
     * @param x 往x轴方向的移动速度
     * @param y 往y轴方向的移动速度
     */
    public void move(float x, float y) {
        velocity.x += x * GameConstants.PLAYER_SPEED;
        velocity.y += y * GameConstants.PLAYER_SPEED;
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

        // 更新相机位置，使玩家保持在屏幕中央
//        camera.setPosition(new Vector2(position.x +  texture.getWidth() / 2f - halfViewportWidth,
//                position.y + texture.getHeight() / 2f - halfViewportHeight));
    }

    /**
     * 渲染玩家纹理。
     * @param batch SpriteBatch，用于绘制纹理
     */
    @Override
    public void render(SpriteBatch batch) {
        // 根据玩家位置和纹理大小渲染玩家纹理
        batch.draw(texture, position.x -  texture.getWidth() / 2f, position.y - texture.getHeight() / 2f);
    }

    /**
     * 获取玩家的位置。
     * @return 玩家的位置向量
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * 获取玩家的速度。
     * @return 玩家的速度向量
     */
    public Vector2 getVelocity() {
        return velocity;
    }
}
