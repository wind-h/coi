package com.wind.coi.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderable {

    void update(float delta);

    void render(SpriteBatch batch);

    void dispose();

    int getType();
}