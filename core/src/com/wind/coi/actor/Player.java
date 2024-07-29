package com.wind.coi.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * @author hsc
 * @date 2024/7/26 15:48
 */
public class Player {

    private Texture texture;

    private Vector2 position;

    private Vector2 velocity; // 记录按下的方向

    private float speed; // 移动速度

    public Player(Texture texture) {
        this.texture = texture;
        this.speed = 30f;
        this.position = new Vector2();
        this.velocity = new Vector2(0, 0);
    }

    public void setPosition(Vector2 position) {
        this.position.set(position);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity.set(velocity);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Texture getTexture() {
        return texture;
    }

    public void update(float delta) {
        position.add(velocity.scl(speed * delta));
    }
}
