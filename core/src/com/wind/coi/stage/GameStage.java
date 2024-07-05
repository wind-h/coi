package com.wind.coi.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.wind.coi.MainGame;
import com.wind.coi.actor.TopGroup;
import com.wind.coi.constant.ResourceConstant;

/**
 * @author hsc
 * @date 2024/7/5 13:45
 */
public class GameStage extends AbstractBaseStage {

    private TopGroup topGroup;

    public GameStage(MainGame mainGame) {
        super(mainGame);
    }

    public GameStage(MainGame mainGame, Viewport viewport) {
        super(mainGame, viewport);
        init();
    }

    public void init() {
        topGroup = new TopGroup(getMainGame());
        // 水平居中 (gameStage.w / 2 - topGroup.w / 2)
        topGroup.setX(getWidth() / 2 - topGroup.getWidth() / 2);
        topGroup.setY(getHeight() - topGroup.getHeight());
        addActor(topGroup);


        // 当前分数清零
        topGroup.getCurrentScoreGroup().setScore(0);
        Preferences pres = Gdx.app.getPreferences(ResourceConstant.Prefs.FILE_NAME);
        int bestScore = pres.getInteger(ResourceConstant.Prefs.KEY_BEST_SCORE, 0);
        topGroup.getBestScoreGroup().setScore(bestScore);

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
