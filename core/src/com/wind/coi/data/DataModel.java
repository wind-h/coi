package com.wind.coi.data;

public interface DataModel {

    void dataInit();

    int[][] getData();

    // 向上，向下，向左，向右

    void toUp();

    void toDown();

    void toLeft();

    void toRight();

    enum GameStatus {
        /**
         * 游戏中状态
         */
        GAMING,
        /**
         * 游戏胜利状态
         */
        WIN,
        /**
         * 游戏失败状态
         */
        GAME_OVER,
    }

    interface DataListener {
        /**
         * 两个数字合并时调用
         *
         * @param rowAfterMerge 合并后数字所在行
         * @param colAfterMerge 合并后数字所在列
         * @param numAfterMerge 合并后的数字
         * @param currentScoreAfterMerger 合并后的当前分数
         */
        public void onNumberMerge(int rowAfterMerge, int colAfterMerge, int numAfterMerge, int currentScoreAfterMerger);
    }
}
