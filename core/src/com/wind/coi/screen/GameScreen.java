package com.wind.coi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.wind.coi.GameUI;
import com.wind.coi.MainGame;
import com.wind.coi.input.GameInputProcessor;
import com.wind.coi.map.Map;

public class GameScreen implements Screen {

    public MainGame game;

    private SpriteBatch batch;

    private OrthographicCamera camera;

    private Map map;

    private Array<Texture> textures;

    private GameUI gameUI;

    private Integer mapW = 160;

    private Integer mapH = 160;

    private Integer cellSize = 32;

    public GameScreen(MainGame game) {
        this.game = game;

        batch = new SpriteBatch();

        // 加载纹理
        textures = new Array<>();
        textures.add(game.getAssetManager().get("grass.png")); // 草地
        textures.add(game.getAssetManager().get("stone.png")); // 石头
        textures.add(game.getAssetManager().get("water.png")); // 水

        // 初始化摄像机
        camera = new OrthographicCamera();
        // 初始视口大小为整个地图
        camera.setToOrtho(false, game.getWindowW(), game.getWindowW());
        camera.update();

        // 生成160x160的地图数据
        map = new Map(mapW, mapH, cellSize, textures);

        gameUI = new GameUI(this);

        // 设置输入处理器
        InputProcessor inputProcessor = new GameInputProcessor(camera);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // 遍历地图数据并绘制瓦片
        for (int y = 0; y < map.getMapWidth(); y++) {
            for (int x = 0; x < map.getMapHeight(); x++) {
                int tileIndex = map.getMapData()[y][x];
                if (tileIndex >= 0 && tileIndex < textures.size) {
                    batch.draw(textures.get(tileIndex), x * map.getCellSize(), y * map.getCellSize(), map.getCellSize(), map.getCellSize());
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
}