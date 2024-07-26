package com.wind.coi.old.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.wind.coi.old.MainGame;

/**
 * @author hsc
 * @date 2024/7/5 14:23
 */
public class ScoreGroup extends AbstractBaseGroup {

    /**
     * 背景
     */
    private Image bgImage;

    /**
     * 分数文本显示
     */
    private Label scoreLabel;

    /**
     * 当前分数
     */
    private Integer score = 9999;

    private TextureRegion bgRegion;

    public ScoreGroup(MainGame mainGame, TextureRegion bgRegion) {
        super(mainGame);
        this.bgRegion = bgRegion;
        init();
    }

    public void init() {
        // 首先设置组的宽高（以背景的宽高作为组的宽高）
        setSize(this.bgRegion.getRegionWidth(), this.bgRegion.getRegionHeight());

        // 背景
        bgImage = new Image(this.bgRegion);
        addActor(bgImage);

        Label.LabelStyle style = new Label.LabelStyle();
        // 设置字体
        style.font = getMainGame().getFont();
        // 分数标签
        scoreLabel = new Label(score + "", style);
        // 设置字体缩放
        scoreLabel.setFontScale(0.4f);
        // 设置大小
        scoreLabel.setSize(scoreLabel.getPrefWidth(), scoreLabel.getPrefHeight());
        scoreLabel.setPosition(getWidth() / 2 - scoreLabel.getWidth() / 2, 18);
        addActor(scoreLabel);
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
        this.scoreLabel.setText(score + "");
        this.scoreLabel.setWidth(scoreLabel.getPrefWidth());
        this.scoreLabel.setX(getWidth() / 2 - scoreLabel.getWidth() / 2);
    }

    public void addScore(int scoreStep) {
        setScore(this.score + scoreStep);
    }
}
