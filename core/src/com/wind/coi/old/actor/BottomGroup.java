package com.wind.coi.old.actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.wind.coi.old.MainGame;
import com.wind.coi.old.constant.ResourceConstant;

/**
 * @author hsc
 * @date 2024/7/9 13:39
 */
public class BottomGroup extends AbstractBaseGroup {

    // 帮助按钮X坐标
    private static final float HELP_BUTTON_X = 15;
    // 退出按钮X坐标
    private static final float EXIT_BUTTON_X = 240;

    private Button helpButton;

    private Button exitButton;

    public BottomGroup(MainGame mainGame) {
        super(mainGame);
        init();
    }

    @Override
    public void init() {
        createHelpButtonWithListener();

        createExitButtonWithListener();

        setSize(getMainGame().getWorldWidth(), helpButton.getHeight());
    }

    public void createHelpButtonWithListener() {
        Button.ButtonStyle helpStyle = toButtonStyle(ResourceConstant.AtlasNames.GAME_BTN_HELP);
        helpButton = new Button(helpStyle);
        helpButton.setX(HELP_BUTTON_X);
        // 设置按钮点击监听
        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // 显示帮助舞台
                getMainGame().getGameScreen().setShowHelpStage(true);
            }
        });
        addActor(helpButton);
    }

    public void createExitButtonWithListener() {
        Button.ButtonStyle exitStyle = toButtonStyle(ResourceConstant.AtlasNames.GAME_BTN_EXIT);
        exitButton = new Button(exitStyle);
        exitButton.setX(EXIT_BUTTON_X);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getMainGame().getGameScreen().setShowExitConfirmStage(true);
            }
        });
        addActor(exitButton);
    }

    private Button.ButtonStyle toButtonStyle(String gameName) {
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = new TextureRegionDrawable(getMainGame().getAtlas().findRegion(gameName, 1));
        style.down = new TextureRegionDrawable(getMainGame().getAtlas().findRegion(gameName, 2));
        return style;
    }
}
