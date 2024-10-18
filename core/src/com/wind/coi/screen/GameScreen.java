package com.wind.coi.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.wind.coi.GameUI;
import com.wind.coi.MainGame;
import com.wind.coi.map.Map;

public class GameScreen implements Screen, GestureDetector.GestureListener {
    public MainGame game;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touchDownPos;
    private boolean isDragging;
    private Map map;
    private GameUI gameUI;

    private static final float MIN_ZOOM = 0.25f; // 最小缩放值
    private static final float MAX_ZOOM = 1.25f;  // 最大缩放值

    public GameScreen(MainGame game) {
        this.game = game;

        batch = new SpriteBatch();

        // 加载纹理
        Array<Texture> textures = new Array<>();
        textures.add(game.getAssetManager().get("grass.png")); // 草地
        textures.add(game.getAssetManager().get("stone.png")); // 石头
        textures.add(game.getAssetManager().get("water.png")); // 水

        // 初始化摄像机
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 160 * 32, 160 * 32); // 初始视口大小为整个地图
        camera.update();

        // 生成160x160的地图数据
        map = new Map(160, 160, 32, textures);

        gameUI = new GameUI(this);

        // 设置输入处理器
        InputProcessor inputProcessor = new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                touchDownPos = new Vector3(screenX, screenY, 0);
                camera.unproject(touchDownPos);
                isDragging = true;
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                isDragging = false;
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (isDragging) {
                    Vector3 currentTouchPos = new Vector3(screenX, screenY, 0);
                    camera.unproject(currentTouchPos);
                    camera.translate(touchDownPos.x - currentTouchPos.x, touchDownPos.y - currentTouchPos.y);
                    touchDownPos.set(currentTouchPos);
                    clampCamera();
                    camera.update();
                }
                return true;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                if (amountY > 0) {
                    camera.zoom *= 0.9f; // 缩小
                } else if (amountY < 0) {
                    camera.zoom /= 0.9f; // 放大
                }
                clampCamera();
                camera.update();
                return true;
            }
        };

        Gdx.input.setInputProcessor(new GestureDetector(this));
        Gdx.input.setInputProcessor(new InputMultiplexer(inputProcessor, new GestureDetector(this)));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // 遍历地图数据并绘制瓦片
        for (int y = 0; y < map.getMapWidth(); y++) {
            for (int x = 0; x < map.getMapHeight(); x++) {
                int tileIndex = map.getMapData()[y][x];
                if (tileIndex >= 0 && tileIndex < map.getTextures().size) {
                    batch.draw(map.getTextures().get(tileIndex), x * map.getCellSize(), y * map.getCellSize(), map.getCellSize(), map.getCellSize());
                }
            }
        }

        batch.end();

        // 渲染UI
        gameUI.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
        gameUI.resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        gameUI.dispose();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX, -deltaY);
        clampCamera();
        camera.update();
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        float zoomFactor = initialDistance / distance;
        camera.zoom *= zoomFactor;
        clampCamera();
        camera.update();
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {
    }

    // 限制摄像机边界和缩放范围
    private void clampCamera() {
        float halfWidth = camera.viewportWidth * camera.zoom / 2;
        float halfHeight = camera.viewportHeight * camera.zoom / 2;

        // 限制摄像机位置
        camera.position.x = Math.max(halfWidth, Math.min(camera.position.x, 160 * 32 - halfWidth));
        camera.position.y = Math.max(halfHeight, Math.min(camera.position.y, 160 * 32 - halfHeight));

        // 限制摄像机缩放
        camera.zoom = Math.max(MIN_ZOOM, Math.min(camera.zoom, MAX_ZOOM));
    }
}