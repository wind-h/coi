package com.wind.coi.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.wind.coi.MainGame;
import com.wind.coi.constant.ResourceConstant;

/**
 * @author hsc
 * @date 2024/7/9 13:39
 */
public class BottomGroup extends AbstractBaseGroup {

    private Button helpButton;

    private Button exitButton;

    public BottomGroup(MainGame mainGame) {
        super(mainGame);
        init();
    }

    public void init() {
        Button.ButtonStyle helpStyle = new Button.ButtonStyle();
        helpStyle.up = new TextureRegionDrawable(getMainGame().getAtlas().findRegion(ResourceConstant.AtlasNames.GAME_BTN_HELP, 1));
        helpStyle.down = new TextureRegionDrawable(getMainGame().getAtlas().findRegion(ResourceConstant.AtlasNames.GAME_BTN_HELP, 2));
        helpButton = new Button(helpStyle);
        helpButton.setX(15);
        // todo 点击事件
        addActor(helpButton);

        setSize(getMainGame().getWorldWidth(), helpButton.getHeight());

        Button.ButtonStyle exitStyle = new Button.ButtonStyle();
        exitStyle.up = new TextureRegionDrawable(getMainGame().getAtlas().findRegion(ResourceConstant.AtlasNames.GAME_BTN_EXIT, 1));
        exitStyle.down = new TextureRegionDrawable(getMainGame().getAtlas().findRegion(ResourceConstant.AtlasNames.GAME_BTN_EXIT, 2));
        exitButton = new Button(exitStyle);
        exitButton.setX(240);
        // todo 点击事件
        addActor(exitButton);
    }
}
