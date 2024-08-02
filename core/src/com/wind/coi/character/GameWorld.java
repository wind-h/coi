package com.wind.coi.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.List;

public class GameWorld implements Renderable {

    private Array<Renderable> renderableList;

    public GameWorld() {
        renderableList = new Array<>();
    }

    @Override
    public void update(float delta) {
        for (Renderable renderable : renderableList) {
            renderable.update(delta);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        for (Renderable renderable : renderableList) {
            renderable.render(batch);
        }
    }

    @Override
    public void dispose() {
        for (Renderable renderable : renderableList) {
            renderable.dispose();
        }
    }

    public void addRenderable(Renderable renderable) {
        renderableList.add(renderable);
    }

    public void removeRenderable(Renderable renderable) {
        renderableList.removeValue(renderable, false);
    }

    public <T extends Renderable> void addRenderableList(Array<T> renderableList) {
        this.renderableList.addAll(renderableList);
    }
}