package com.wind.coi.old.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.wind.coi.old.MainGame;
import com.wind.coi.old.constant.ResourceConstant;

/**
 * @author hsc
 * @date 2024/7/5 13:56
 */
public class TopGroup extends AbstractBaseGroup {

    /**
     * 2048 logo
     */
    private static Image logoImage;

    private ScoreGroup bestScoreGroup;

    private ScoreGroup currentScoreGroup;

    public TopGroup(MainGame mainGame) {
        super(mainGame);
        init();
    }

    @Override
    public void init() {
        logoImage = new Image(getMainGame().getAtlas().findRegion(ResourceConstant.AtlasNames.GAME_LOGO));
        logoImage.setX(20);
        addActor(logoImage);

        // 设置组的宽高（以世界的宽度, LOGO 的高度 作为组的宽高）
        setSize(getMainGame().getWorldWidth(), logoImage.getHeight());

        // 当前分数
        currentScoreGroup = new ScoreGroup(getMainGame(), getMainGame().getAtlas().findRegion(ResourceConstant.AtlasNames.GAME_SCORE_BG_NOW));
        currentScoreGroup.setX(186);
        currentScoreGroup.setY(getHeight() - currentScoreGroup.getHeight());
        addActor(currentScoreGroup);

        // 最佳分数
        bestScoreGroup = new ScoreGroup(getMainGame(), getMainGame().getAtlas().findRegion(ResourceConstant.AtlasNames.GAME_SCORE_BG_BEST));
        bestScoreGroup.setX(334);
        bestScoreGroup.setY(getHeight() - bestScoreGroup.getHeight());
        addActor(bestScoreGroup);


    }

    public ScoreGroup getBestScoreGroup() {
        return bestScoreGroup;
    }

    public ScoreGroup getCurrentScoreGroup() {
        return currentScoreGroup;
    }
}
