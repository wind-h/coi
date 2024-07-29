package com.wind.coi.actor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.wind.coi.MainGame;

/**
 * @author hsc
 * @date 2024/7/26 15:33
 */
public abstract class BaseGroup extends Group {

    private MainGame game;

    public BaseGroup(MainGame game) {
        this.game = game;
    }

    public MainGame getGame() {
        return game;
    }

    public void setGame(MainGame game) {
        this.game = game;
    }
}
