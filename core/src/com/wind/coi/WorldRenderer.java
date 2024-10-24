package com.wind.coi;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.wind.coi.constants.GlobalConstant;

/**
 * @author hsc
 * @date 2024/10/23 16:59
 */
public class WorldRenderer implements Disposable {

    private OrthographicCamera camera;

    private SpriteBatch batch;

    private WorldController worldController;

    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
        init();
    }

    private void init() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(GlobalConstant.VIEWPORT_WIDTH, GlobalConstant.VIEWPORT_HEIGHT);
        camera.position.set(0, 0 ,0);
        camera.update();

    }

    public void render() {
        renderObjs();
    }

    private void renderObjs() {
        worldController.getCameraHelper().apply(camera);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        Sprite[] sprites = worldController.getSprites();
        for (Sprite sprite : sprites) {
            sprite.draw(batch);
        }
        batch.end();
    }

    public void resize(int width, int height) {
        camera.viewportWidth = (GlobalConstant.VIEWPORT_HEIGHT / height) * width;
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
