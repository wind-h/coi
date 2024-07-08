package com.wind.coi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.wind.coi.MainGame;
import com.wind.coi.constant.ResourceConstant;
import com.wind.coi.stage.GameStage;

/**
 * @author hsc
 * @date 2024/7/5 11:38
 */
public class GameScreen extends ScreenAdapter {

    private MainGame mainGame;

    private GameStage gameStage;

    public GameScreen(MainGame mainGame) {
        this.mainGame = mainGame;
        init();
    }

    public void init() {
        // 创建游戏舞台
        gameStage = new GameStage(mainGame, new StretchViewport(mainGame.getWorldWidth(), mainGame.getWorldHeight()));

        // 把输入处理设置到主游戏舞台（必须设置, 否则无法接收用户输入）
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta) {
        // 使用背景颜色清屏
        Gdx.gl.glClearColor(ResourceConstant.BG_RGBA.r, ResourceConstant.BG_RGBA.g, ResourceConstant.BG_RGBA.b, ResourceConstant.BG_RGBA.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 渲染
        gameStage.act();
        gameStage.draw();
    }

    @Override
    public void dispose() {
        if (gameStage != null) {
            gameStage.dispose();
        }
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public void setGameStage(GameStage gameStage) {
        this.gameStage = gameStage;
    }
}
