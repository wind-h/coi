package com.wind.coi.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author hsc
 * @date 2024/10/10 11:21
 */
public interface Renderable {

    void update(float delta);

    void render(SpriteBatch batch);

    void dispose();

    int getType();
}