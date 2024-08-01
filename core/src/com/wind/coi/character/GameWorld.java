package com.wind.coi.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class GameWorld implements Renderable {

        private List<Renderable> renderableList;

        public GameWorld() {
            renderableList = new ArrayList<>();
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

        public void addRenderable(Renderable renderable) {
            renderableList.add(renderable);
        }

        public <T extends Renderable> void addRenderableList(List<T> renderableList) {
            this.renderableList.addAll(renderableList);
        }
    }