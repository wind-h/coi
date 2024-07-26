package com.wind.coi.old.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.wind.coi.old.MainGame;


/**
 * @author hsc
 * @date 2024/7/5 13:42
 */
public abstract class AbstractBaseStage extends Stage {

    private MainGame mainGame;

    /**
     * 舞台是否可见（是否更新和绘制）
     */
    private Boolean visible = true;

    public AbstractBaseStage(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public AbstractBaseStage(MainGame mainGame, Viewport viewport) {
        super(viewport);
        this.mainGame = mainGame;
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public void setMainGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public void init() {

    }
}
