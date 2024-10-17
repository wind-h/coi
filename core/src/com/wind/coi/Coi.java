package com.wind.coi;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Coi extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch batch;
    private Array<Texture> textures;
    private int[][] mapData;
    private OrthographicCamera camera;
    private Vector3 touchDownPos;
    private boolean isDragging;
    private InputMultiplexer inputMultiplexer;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // 加载纹理
        textures = new Array<>();
        textures.add(new Texture("grass.png")); // 草地
        textures.add(new Texture("stone.png")); // 石头
        textures.add(new Texture("water.png")); // 水

        // 生成160x160的地图数据
        mapData = generateMapData(160, 160);

        // 初始化摄像机
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 160 * 32, 160 * 32); // 初始视口大小为整个地图
        camera.update();

        // 设置输入处理器
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private int[][] generateMapData(int width, int height) {
        int[][] map = new int[height][width];
        float scale = 0.1f; // 控制噪声的频率

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // 计算Perlin噪声值
                double noiseValue = PerlinNoiseGenerator.perlin(x * scale, y * scale);

                // 根据噪声值确定地形类型
                if (noiseValue < 0.3) {
                    map[y][x] = 2; // 水
                } else if (noiseValue < 0.6) {
                    map[y][x] = 0; // 草地
                } else {
                    map[y][x] = 1; // 石头
                }
            }
        }

        return map;
    }

    @Override
    public void render() {
        // 清屏
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 更新摄像机
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // 遍历地图数据并绘制瓦片
        for (int y = 0; y < mapData.length; y++) {
            for (int x = 0; x < mapData[y].length; x++) {
                int tileIndex = mapData[y][x];
                if (tileIndex >= 0 && tileIndex < textures.size) {
                    batch.draw(textures.get(tileIndex), x * 32, y * 32, 32, 32);
                }
            }
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        for (Texture texture : textures) {
            texture.dispose();
        }
    }

    // InputProcessor methods
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.PLUS || keycode == Input.Keys.EQUALS) {
            camera.zoom *= 0.9f; // 缩小
            clampCamera();
            camera.update();
        } else if (keycode == Input.Keys.MINUS) {
            camera.zoom /= 0.9f; // 放大
            clampCamera();
            camera.update();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

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
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
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
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
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

    // 限制摄像机边界
    private void clampCamera() {
        float halfWidth = camera.viewportWidth * camera.zoom / 2;
        float halfHeight = camera.viewportHeight * camera.zoom / 2;
        camera.position.x = Math.max(halfWidth, Math.min(camera.position.x, 160 * 32 - halfWidth));
        camera.position.y = Math.max(halfHeight, Math.min(camera.position.y, 160 * 32 - halfHeight));
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
