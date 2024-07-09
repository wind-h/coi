package com.wind.coi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.wind.coi.MainGame;
import com.wind.coi.constant.ResourceConstant;
import com.wind.coi.stage.ExitStage;
import com.wind.coi.stage.GameOverStage;
import com.wind.coi.stage.GameStage;
import com.wind.coi.stage.HelpStage;

/**
 * @author hsc
 * @date 2024/7/5 11:38
 */
public class GameScreen extends ScreenAdapter {

    private MainGame mainGame;

    private GameStage gameStage;

    private HelpStage helpStage;

    private ExitStage exitStage;

    private GameOverStage gameOverStage;

    public GameScreen(MainGame mainGame) {
        this.mainGame = mainGame;
        init();
    }

    public void init() {
        // 创建游戏舞台
        gameStage = new GameStage(mainGame, new StretchViewport(mainGame.getWorldWidth(), mainGame.getWorldHeight()));
        helpStage = new HelpStage(mainGame, new StretchViewport(mainGame.getWorldWidth(), mainGame.getWorldHeight()));
        exitStage = new ExitStage(mainGame, new StretchViewport(mainGame.getWorldWidth(), mainGame.getWorldHeight()));
        gameOverStage = new GameOverStage(mainGame, new StretchViewport(mainGame.getWorldWidth(), mainGame.getWorldHeight()));

        // 把输入处理设置到主游戏舞台（必须设置, 否则无法接收用户输入）
        Gdx.input.setInputProcessor(gameStage);
    }


    /**
     * 重新开始游戏
     */
    public void restartGame() {
        gameStage.restartGame();
    }

    /**
     * 帮助舞台 是否显示
     */
    public void setShowHelpStage(boolean showHelpStage) {
        helpStage.setVisible(showHelpStage);
        if (helpStage.getVisible()) {
            // 如果显示帮助舞台, 则把输入处理设置到帮助舞台
            Gdx.input.setInputProcessor(helpStage);
        } else {
            // 不显示帮助舞台, 把输入处理设置回主游戏舞台
            Gdx.input.setInputProcessor(gameStage);
        }
    }

    /**
     * 退出确认舞台 是否显示
     */
    public void setShowExitConfirmStage(boolean showExitConfirmStage) {
        exitStage.setVisible(showExitConfirmStage);
        if (exitStage.getVisible()) {
            Gdx.input.setInputProcessor(exitStage);
        } else {
            Gdx.input.setInputProcessor(gameStage);
        }
    }

    /**
     * 显示结束舞台（并设置结束舞台中的文本显示状态和分数）
     */
    public void showGameOverStage(boolean isWin, int score) {
        // 设置结束舞台中的文本显示状态状态和分数
        gameOverStage.setGameOverState(isWin, score);

        gameOverStage.setVisible(true);
        Gdx.input.setInputProcessor(gameOverStage);
    }

    /**
     * 隐藏结束舞台
     */
    public void hideGameOverStage() {
        gameOverStage.setVisible(false);
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

        if (helpStage.getVisible()) {
            helpStage.act();
            helpStage.draw();
        }

        if (exitStage.getVisible()) {
            exitStage.act();
            exitStage.draw();
        }

        if (gameOverStage.getVisible()) {
            gameOverStage.act();
            gameOverStage.draw();
        }
    }

    @Override
    public void dispose() {
        if (gameStage != null) {
            gameStage.dispose();
        }
        if (helpStage != null) {
            helpStage.dispose();
        }
        if (exitStage != null) {
            exitStage.dispose();
        }
        if (gameOverStage != null) {
            gameOverStage.dispose();
        }
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public void setGameStage(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public void setMainGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public HelpStage getHelpStage() {
        return helpStage;
    }

    public void setHelpStage(HelpStage helpStage) {
        this.helpStage = helpStage;
    }

    public ExitStage getExitStage() {
        return exitStage;
    }

    public void setExitStage(ExitStage exitStage) {
        this.exitStage = exitStage;
    }

    public GameOverStage getGameOverStage() {
        return gameOverStage;
    }

    public void setGameOverStage(GameOverStage gameOverStage) {
        this.gameOverStage = gameOverStage;
    }
}
