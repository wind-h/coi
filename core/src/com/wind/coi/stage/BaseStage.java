package com.wind.coi.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.wind.coi.MainGame;

/**
 * @author hsc
 * @date 2024/7/26 15:31
 */
public abstract class BaseStage extends Stage {

    private MainGame game;

    private Boolean visible = true;

    public BaseStage(MainGame game) {
        this.game = game;
    }

    public MainGame getGame() {
        return game;
    }

    public void setGame(MainGame game) {
        this.game = game;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
