package com.wind.coi.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.wind.coi.enums.TypeEnum;

/**
 * @author hsc
 * @date 2024/10/10 11:21
 */
public class Player implements Renderable {

    // 玩家纹理
    public Texture playerBg;

    // 玩家位置
    public Vector2 playerPosition;

    // 玩家速度
    public Vector2 playerVelocity;

    public Player(Texture playerBg) {
        this.playerBg = playerBg;
        playerPosition = new Vector2(playerBg.getWidth(), playerBg.getHeight());
        playerVelocity = new Vector2();
    }

    public void move(float x, float y) {
        playerVelocity.x += x * 20f;
        playerVelocity.y += y * 20f;
    }

    @Override
    public void update(float delta) {
        // 根据速度和时间更新玩家位置
        playerPosition.x += playerVelocity.x * delta;
        playerPosition.y += playerVelocity.y * delta;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(playerBg, playerPosition.x - playerBg.getWidth() / 2f, playerPosition.y - playerBg.getHeight() / 2f);
    }

    @Override
    public void dispose() {
        playerBg.dispose();
    }

    @Override
    public int getType() {
        return TypeEnum.PLAYER.ordinal();
    }
}