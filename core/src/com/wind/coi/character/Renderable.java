package com.wind.coi.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

public interface Renderable {

    void update(float delta);

    void render(SpriteBatch batch);

    void dispose();
}