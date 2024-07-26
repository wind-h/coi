package com.wind.coi.old.actor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.wind.coi.old.MainGame;

/**
 * @author hsc
 * @date 2024/7/5 13:54
 */
public abstract class AbstractBaseGroup extends Group {

    private MainGame mainGame;

    public AbstractBaseGroup(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public void setMainGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public void init() {

    }
}
