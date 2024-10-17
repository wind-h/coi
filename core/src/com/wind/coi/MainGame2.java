package com.wind.coi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * @author hsc
 * @date 2024/10/16 11:25
 */
public class MainGame2 extends ApplicationAdapter implements GestureDetector.GestureListener {

    private OrthographicCamera camera;

    private GestureDetector gestureDetector;

    private SpriteBatch batch;

    private Texture texture;

    Map map;

    private float worldWidth;

    private float worldHeight;

    private float viewportWidth;

    private float viewportHeight;

    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture("color.png");
        map = new Map(100, 100, 16, texture);

        viewportWidth = Gdx.app.getGraphics().getWidth();
        viewportHeight = Gdx.app.getGraphics().getHeight();
        camera = new OrthographicCamera(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        worldWidth = map.getMapWidth() * map.getCellSize();
        worldHeight = map.getMapHeight() * map.getCellSize();

        // 初始化手势检测器
        gestureDetector = new GestureDetector(this);
        Gdx.input.setInputProcessor(gestureDetector);
    }

    @Override
    public void render() {
        // 清除屏幕
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // 开始绘制
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        map.draw(batch);
        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

    @Override
    public boolean touchDown(float v, float v1, int i, int i1) {
        return false;
    }

    @Override
    public boolean tap(float v, float v1, int i, int i1) {
        return false;
    }

    @Override
    public boolean longPress(float v, float v1) {
        return false;
    }

    @Override
    public boolean fling(float v, float v1, int i) {
        return false;
    }

    @Override
    public boolean pan(float v, float v1, float v2, float v3) {
        float newX = camera.position.x + v2;
        float newY = camera.position.y - v3;

        newX = Math.max(viewportWidth / 2, Math.min(worldWidth - viewportWidth / 2, newX));
        newY = Math.max(viewportHeight / 2, Math.min(worldHeight - viewportHeight / 2, newY));

        // 应用新的位置
        camera.position.set(newX, newY, 0);
        camera.update();
        return true;
    }

    @Override
    public boolean panStop(float v, float v1, int i, int i1) {
        return false;
    }

    @Override
    public boolean zoom(float v, float v1) {
        float zoomFactor = v1 / v;
        camera.zoom *= zoomFactor;
        camera.zoom = Math.max(0.2f, Math.min(2.0f, camera.zoom));
        camera.update();
        return true;
    }

    @Override
    public boolean pinch(Vector2 vector2, Vector2 vector21, Vector2 vector22, Vector2 vector23) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
