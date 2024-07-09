package com.wind.coi.data;

public interface DataModel {

    void dataInit();

    int[][] getData();

    /**
     * 获取当前的分数
     */
    int getCurrentScore();

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
         * 随机生成数字时调用
         *
         * @param row 生成的数字所在行
         * @param col 生成的数字所在列
         * @param num 生成的数字
         */
        void onGeneratorNumber(int row, int col, int num);

        /**
         * 两个数字合并时调用
         *
         * @param rowAfterMerge 合并后数字所在行
         * @param colAfterMerge 合并后数字所在列
         * @param numAfterMerge 合并后的数字
         * @param currentScoreAfterMerger 合并后的当前分数
         */
        void onNumberMerge(int rowAfterMerge, int colAfterMerge, int numAfterMerge, int currentScoreAfterMerger);

        void onGameOver(boolean isWin);
    }
}
