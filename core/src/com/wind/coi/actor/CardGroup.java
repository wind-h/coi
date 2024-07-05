package com.wind.coi.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.wind.coi.MainGame;
import com.wind.coi.constant.ResourceConstant;

/**
 * @author hsc
 * @date 2024/7/5 15:44
 */
public class CardGroup extends AbstractBaseGroup {

    /**
     * 卡片背景
     */
    private Image bgImage;

    /**
     * 卡片数字文本显示
     */
    private Label numLabel;

    /**
     * 卡片数字
     */
    private Integer num = 0;

    public CardGroup(MainGame mainGame) {
        super(mainGame);
        init();
    }

    public void init() {
        // 设置卡片的背景
        bgImage = new Image(getMainGame().getAtlas().findRegion(ResourceConstant.AtlasNames.GAME_ROUND_RECT));
        addActor(bgImage);

        // 设置卡片的大小
        setSize(bgImage.getWidth(), bgImage.getHeight());

        // 设置字体和字体颜色
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = getMainGame().getFont();
        style.fontColor = Color.BLACK;
        numLabel = new Label(num + "", style);
        // 设置字体缩放
        numLabel.setFontScale(0.48f);
        // 设置标签的大小
        numLabel.setSize(numLabel.getPrefWidth(), numLabel.getPrefHeight());
        // 设置水平和垂直居中
        numLabel.setX(getWidth() / 2 - numLabel.getWidth() / 2);
        numLabel.setY(getHeight() / 2 - numLabel.getHeight() / 2);
        addActor(numLabel);

        setNum(num);
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
        if (num == 0) {
            numLabel.setText("");
        } else {
            numLabel.setText(num + "");
        }
        numLabel.setWidth(numLabel.getPrefWidth());
        numLabel.setY(getHeight() / 2 - numLabel.getHeight() / 2);

        // 根据不同的数字给卡片背景设置颜色
        switch (this.num) {
            case 2: {
                bgImage.setColor(ResourceConstant.CardColors.RGBA_2);
                break;
            }
            case 4: {
                bgImage.setColor(ResourceConstant.CardColors.RGBA_4);
                break;
            }
            case 8: {
                bgImage.setColor(ResourceConstant.CardColors.RGBA_8);
                break;
            }
            case 16: {
                bgImage.setColor(ResourceConstant.CardColors.RGBA_16);
                break;
            }
            case 32: {
                bgImage.setColor(ResourceConstant.CardColors.RGBA_32);
                break;
            }
            case 64: {
                bgImage.setColor(ResourceConstant.CardColors.RGBA_64);
                break;
            }
            case 128: {
                bgImage.setColor(ResourceConstant.CardColors.RGBA_128);
                break;
            }
            case 256: {
                bgImage.setColor(ResourceConstant.CardColors.RGBA_256);
                break;
            }
            case 512: {
                bgImage.setColor(ResourceConstant.CardColors.RGBA_512);
                break;
            }
            case 1024: {
                bgImage.setColor(ResourceConstant.CardColors.RGBA_1024);
                break;
            }
            case 2048: {
                bgImage.setColor(ResourceConstant.CardColors.RGBA_2048);
                break;
            }
            default: {
                bgImage.setColor(ResourceConstant.CardColors.RGBA_0);
                break;
            }
        }
    }
}
