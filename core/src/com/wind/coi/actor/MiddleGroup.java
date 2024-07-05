package com.wind.coi.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.wind.coi.MainGame;
import com.wind.coi.constant.ResourceConstant;

/**
 * 中间部分演员组, 2048 数字卡片展示区域, 滑动事件捕获区域
 * @author hsc
 * @date 2024/7/5 15:04
 */
public class MiddleGroup extends AbstractBaseGroup {

    private Image bgImage;

    // 卡片的行列数
    private static final int CARD_ROW_SUM = 4;

    private static final int CARD_COL_SUM = 4;

    private CardGroup[][] allCards = new CardGroup[CARD_ROW_SUM][CARD_COL_SUM];


    public MiddleGroup(MainGame mainGame) {
        super(mainGame);
        init();
    }

    public void init() {
        // 添加背景
        bgImage = new Image(getMainGame().getAtlas().findRegion(ResourceConstant.AtlasNames.GAME_RECT_BG));
        addActor(bgImage);

        // 设置大小
        setSize(bgImage.getWidth(), bgImage.getHeight());

        // 初始化所有卡片
        for (int row = 0; row < CARD_ROW_SUM; row++) {
            for (int col = 0; col < CARD_COL_SUM; col++) {
                allCards[row][col] = new CardGroup(getMainGame());
                allCards[row][col].setOrigin(Align.center);
                addActor(allCards[row][col]);
            }
        }

        // 获取卡片的宽高
        float cardWidth = allCards[0][0].getWidth();
        float cardHeight = allCards[0][0].getHeight();

        // 卡片中间的缝隙，水平缝隙和垂直缝隙
        float horizontalInterval = (getWidth() - cardWidth * CARD_COL_SUM) / (CARD_COL_SUM + 1);
        float verticalInterval = (getHeight() - cardHeight * CARD_ROW_SUM) / (CARD_ROW_SUM + 1);

        float cardY;
        for (int row = 0; row < CARD_ROW_SUM; row++) {
            cardY = getHeight() - (verticalInterval + cardHeight) * (row + 1);
            for (int col = 0; col < CARD_COL_SUM; col++) {
                allCards[row][col].setX(horizontalInterval + (cardWidth + horizontalInterval) * col);
                allCards[row][col].setY(cardY);
            }
        }

    }
}
