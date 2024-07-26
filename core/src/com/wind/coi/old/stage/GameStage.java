package com.wind.coi.old.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.wind.coi.old.MainGame;
import com.wind.coi.old.actor.BottomGroup;
import com.wind.coi.old.actor.MiddleGroup;
import com.wind.coi.old.actor.TopGroup;
import com.wind.coi.old.constant.ResourceConstant;

/**
 * @author hsc
 * @date 2024/7/5 13:45
 */
public class GameStage extends AbstractBaseStage {

    private TopGroup topGroup;

    private MiddleGroup middleGroup;

    private BottomGroup bottomGroup;

    public GameStage(MainGame mainGame) {
        super(mainGame);
    }

    public GameStage(MainGame mainGame, Viewport viewport) {
        super(mainGame, viewport);
        init();
    }

    public void init() {

        middleGroup = new MiddleGroup(getMainGame());
        middleGroup.setX(getWidth() / 2 - middleGroup.getWidth() / 2);
        middleGroup.setY(getHeight() / 2 - middleGroup.getHeight() / 2);
        addActor(middleGroup);

        topGroup = new TopGroup(getMainGame());
        // 水平居中 (gameStage.w / 2 - topGroup.w / 2)
        topGroup.setX(getWidth() / 2 - topGroup.getWidth() / 2);
        // 中间组的顶部Y坐标
        float middleGroupTopY = middleGroup.getY() + middleGroup.getHeight();
        // 被中间组占去后顶部剩余的高度
        float topSurplusHeight = getHeight() - middleGroupTopY;
        topGroup.setY(middleGroupTopY + (topSurplusHeight / 2 - topGroup.getHeight() / 2));	// 顶部竖直居中
        addActor(topGroup);

        bottomGroup = new BottomGroup(getMainGame());
        bottomGroup.setX(getWidth() / 2 - bottomGroup.getWidth() / 2);				// 水平居中
        bottomGroup.setY(middleGroup.getY() / 2 - bottomGroup.getHeight() / 2);		// 底部竖直居中
        addActor(bottomGroup);

        // 当前分数清零
        topGroup.getCurrentScoreGroup().setScore(0);
        Preferences pres = Gdx.app.getPreferences(ResourceConstant.Prefs.FILE_NAME);
        int bestScore = pres.getInteger(ResourceConstant.Prefs.KEY_BEST_SCORE, 0);
        topGroup.getBestScoreGroup().setScore(bestScore);

        addListener(new InputListenerImpl());
    }

    /**
     * 重新开始游戏
     */
    public void restartGame() {
        middleGroup.restartGame();
        // 当前分数清零
        topGroup.getCurrentScoreGroup().setScore(0);
    }


    /**
     * 增加当前分数
     */
    public void addCurrScore(int scoreStep) {
        // 增加分数
        topGroup.getCurrentScoreGroup().addScore(scoreStep);
        // 如果当前分数大于最佳分数, 则更新最佳分数
        int currSore = topGroup.getCurrentScoreGroup().getScore();
        int bestSore = topGroup.getBestScoreGroup().getScore();
        if (currSore > bestSore) {
            topGroup.getBestScoreGroup().setScore(currSore);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        // 舞台销毁时保存最佳分数
        Preferences prefs = Gdx.app.getPreferences(ResourceConstant.Prefs.FILE_NAME);
        prefs.putInteger(ResourceConstant.Prefs.KEY_BEST_SCORE, topGroup.getBestScoreGroup().getScore());
        prefs.flush();
    }

    /**
     * 输入事件监听器
     */
    private class InputListenerImpl extends InputListener {

        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            /*
             * 对于 PC 平台, 可同时通过按键控制游戏,
             * 监听方向键的按下, 根据方向键移动卡片
             */
            switch (keycode) {
                case Input.Keys.UP: {
                    middleGroup.toUp();
                    return true;
                }
                case Input.Keys.DOWN: {
                    middleGroup.toDown();
                    return true;
                }
                case Input.Keys.LEFT: {
                    middleGroup.toLeft();
                    return true;
                }
                case Input.Keys.RIGHT: {
                    middleGroup.toRight();
                    return true;
                }
            }

            return super.keyDown(event, keycode);
        }

        @Override
        public boolean keyUp(InputEvent event, int keycode) {
            if (keycode == Input.Keys.BACK) {
                // 在主游戏舞台界面按下返回键并弹起后, 提示是否退出游戏（显示退出确认舞台）
                getMainGame().getGameScreen().setShowExitConfirmStage(true);
                return true;
            }
            return super.keyUp(event, keycode);
        }
    }
}
