package com.wind.coi.service.impl;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.wind.coi.service.Renderable;
import com.wind.coi.service.Updateable;

import java.util.ArrayList;
import java.util.List;

public class GameWorld implements Updateable, Renderable {

    private List<Updateable> updateables;

    private List<Renderable> renderables;
    
    public GameWorld() {
        updateables = new ArrayList<>();
        renderables = new ArrayList<>();
    }

    public void addEntity(Updateable entity) {
        updateables.add(entity);
    }

    public void addEntity(Renderable entity) {
        renderables.add(entity);
    }

    @Override
    public void update(float delta) {
        for (Updateable updateable : updateables) {
            updateable.update(delta);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        for (Renderable renderable : renderables) {
            renderable.render(batch);
        }
    }
}