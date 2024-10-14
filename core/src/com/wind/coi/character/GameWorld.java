package com.wind.coi.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * @author hsc
 * @date 2024/10/10 11:21
 */
public class GameWorld {

    private Array<Renderable> renderableList;

    Array<Bullet> bulletArray;

    public GameWorld() {
        renderableList = new Array<>();
        bulletArray = new Array<>();
    }

    public void update(float delta) {
        for (Renderable renderable : renderableList) {
            renderable.update(delta);
        }
    }


    public void render(SpriteBatch batch) {
        for (Renderable renderable : renderableList) {
            renderable.render(batch);
        }
    }


    public void dispose() {
        for (Renderable renderable : renderableList) {
            renderable.dispose();
        }
    }

    public void addRenderable(Renderable renderable) {
        renderableList.add(renderable);
        if (renderable instanceof Bullet) {
            bulletArray.add((Bullet) renderable);
        }
    }

    public void removeRenderable(Renderable renderable) {
        renderableList.removeValue(renderable, false);
        if (renderable instanceof Bullet) {
            bulletArray.removeValue((Bullet) renderable, false);
        }
    }

    public <T extends Renderable> void addRenderableList(Array<T> renderableList) {
        this.renderableList.addAll(renderableList);
    }

    public Array<Bullet> getBulletList() {
        return bulletArray;
    }
}