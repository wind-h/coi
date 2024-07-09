package com.wind.coi.actor;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.wind.coi.MainGame;
import com.wind.coi.constant.ResourceConstant;
import com.wind.coi.data.DataModel;
import com.wind.coi.data.DataModelImpl;

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

    private DataModel dataModel;

    /**
     * 滑动生效的最小距离
     */
    private static final float SLIDE_MIN_DIFF = 20;

    private Sound mergeSound;

    private Sound moveSound;

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

        mergeSound = getMainGame().getAssetManager().get(ResourceConstant.Audios.MERGE);
        moveSound = getMainGame().getAssetManager().get(ResourceConstant.Audios.MOVE);

        addListener(new InputListenerImpl());

        dataModel = new DataModelImpl(CARD_ROW_SUM, CARD_COL_SUM, new DateListenerImpl());
        dataModel.dataInit();

        // 数据模型初始化后同步到演员数组
        syncDataToCardGroups();
    }

    /**
     * 同步 数据模型中的数据 到 卡片演员数组
     */
    private void syncDataToCardGroups() {
        int[][] data = dataModel.getData();
        for (int row = 0; row < CARD_ROW_SUM; row++) {
            for (int col = 0; col < CARD_COL_SUM; col++) {
                allCards[row][col].setNum(data[row][col]);
            }
        }
    }

    /**
     * 重新开始游戏
     */
    public void restartGame() {
        dataModel.dataInit();
        syncDataToCardGroups();
    }

    public void toUp() {
        // 操作数据模型中的数据
        dataModel.toUp();
        // 操作完数据模型中的数据后, 需要同步到卡片演员数组
        syncDataToCardGroups();
        // 播放移动操作的音效
        moveSound.play();
    }

    public void toDown() {
        // 操作数据模型中的数据
        dataModel.toDown();
        // 操作完数据模型中的数据后, 需要同步到卡片演员数组
        syncDataToCardGroups();
        // 播放移动操作的音效
        moveSound.play();
    }

    public void toLeft() {
        // 操作数据模型中的数据
        dataModel.toLeft();
        // 操作完数据模型中的数据后, 需要同步到卡片演员数组
        syncDataToCardGroups();
        // 播放移动操作的音效
        moveSound.play();
    }

    public void toRight() {
        // 操作数据模型中的数据
        dataModel.toRight();
        // 操作完数据模型中的数据后, 需要同步到卡片演员数组
        syncDataToCardGroups();
        // 播放移动操作的音效
        moveSound.play();
    }

    public class InputListenerImpl extends InputListener {

        private float downX;

        private float downY;

        /**
         * 触摸按下
         */
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            downX = x;
            downY = y;
            return true;
        }

        /**
         * 触摸松开
         */
        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            float diffX = x - downX;
            float diffY = y - downY;

            if (Math.abs(diffX) >= SLIDE_MIN_DIFF && Math.abs(diffX) * 0.5F > Math.abs(diffY)) {
                // 左右滑动
                if (diffX > 0) {
                    toRight();
                } else {
                    toLeft();
                }
            } else if (Math.abs(diffY) >= SLIDE_MIN_DIFF && Math.abs(diffY) * 0.5F > Math.abs(diffX)) {
                // 上下滑动
                if (diffY > 0) {
                    toUp();
                } else {
                    toDown();
                }
            }
        }
    }



    public class DateListenerImpl implements DataModel.DataListener {

        @Override
        public void onGeneratorNumber(int row, int col, int num) {
            // 有数字生成新, 该数字所在位置的卡片附加一个动画效果, 0.2 秒内缩放值从 0.2 到 1.0
            allCards[row][col].setScale(0.2F);
            ScaleToAction scaleTo = Actions.scaleTo(1.0F, 1.0F, 0.2F);
            allCards[row][col].addAction(scaleTo);
        }

        @Override
        public void onNumberMerge(int rowAfterMerge, int colAfterMerge, int numAfterMerge, int currentScoreAfterMerger) {
            // 有卡片合成, 在合成位置附加动画效果, 缩放值从 0.8 到 1.2, 再到 1.0
            allCards[rowAfterMerge][colAfterMerge].setScale(0.8F);
            SequenceAction sequence = Actions.sequence(
                    Actions.scaleTo(1.2F, 1.2F, 0.1F),
                    Actions.scaleTo(1.0F, 1.0F, 0.1F)
            );
            allCards[rowAfterMerge][colAfterMerge].addAction(sequence);
            // 播放数字合成的音效
            mergeSound.play();
            // 增加当前分数
            getMainGame().getGameScreen().getGameStage().addCurrScore(numAfterMerge);
        }

        @Override
        public void onGameOver(boolean isWin) {
            // 游戏结束, 展示结束舞台
            getMainGame().getGameScreen().showGameOverStage(isWin, dataModel.getCurrentScore());
        }
    }
}
