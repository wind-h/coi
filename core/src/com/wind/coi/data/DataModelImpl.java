package com.wind.coi.data;

import java.util.Random;

public class DataModelImpl implements DataModel  {

    /**
     * 数据的总行数
     */
    private final int rowSum;

    /**
     * 数据的总列数
     */
    private final int colSum;

    /**
     * 数据
     */
    private final int[][] data;

    /**
     * 游戏状态
     */
    private GameStatus gameStatus = GameStatus.GAMING;

    /**
     * 当前分数
     */
    private int currentScore;

    /**
     * 数据监听器
     */
    private final DataListener dataListener;

    /**
     * 随机数生成器
     */
    private final Random random;


    public DataModelImpl(int rowSum, int colSum, DataListener dataListener) {
        this.rowSum = rowSum;
        this.colSum = colSum;
        data = new int[this.rowSum][this.colSum];
        this.dataListener = dataListener;
        random = new Random();

    }

    @Override
    public void dataInit() {
        // 数据清零
        for (int row = 0; row < rowSum; row++) {
            for (int col = 0; col < colSum; col++) {
                data[row][col] = 0;
            }
        }

        // 生成两个数字
        randomGeneratorNumber();
        randomGeneratorNumber();
    }

    public void randomGeneratorNumber() {
        int emptyCardCount = 0;
        for (int row = 0; row < rowSum; row++) {
            for (int col = 0; col < colSum; col++) {
                if (data[row][col] == 0) {
                    emptyCardCount++;
                }

            }
        }
        int newNumOnEmptyCardPosition = random.nextInt(emptyCardCount);
        int newNum = random.nextFloat() < 0.2f ? 4 : 2;
        int emptyCardPosition = 0;
        for (int row = 0; row < rowSum; row++) {
            for (int col = 0; col < colSum; col++) {
                if (data[row][col] != 0) {
                    continue;
                }
                if (emptyCardPosition == newNumOnEmptyCardPosition) {
                    data[row][col] = newNum;
                }
                emptyCardPosition++;
            }
        }
    }

    @Override
    public int[][] getData() {
        return data;
    }

    @Override
    public int getCurrentScore() {
        return currentScore;
    }

    @Override
    public void toUp() {
        if (gameStatus != GameStatus.GAMING) {
            return;
        }
        boolean hasMove = false;
        for (int col = 0; col < colSum; col++) {
            for (int row = 0; row < rowSum; row++) {
                for (int tempRow = row + 1; tempRow < rowSum; tempRow++) {
                    // data[row][tempCol]为空不做任何操作
                    if (data[tempRow][col] == 0) {
                        continue;
                    }
                    // data[row][tempCol]不为空 data[row][col]为空，则直接将data[row][tempCol]移动到data[row][col]
                    if (data[row][col] == 0) {
                        data[row][col] = data[tempRow][col];
                        data[tempRow][col] = 0;
                        hasMove = true;
                    // data[row][tempCol]和data[row][col]相等，则data[row][col]变为2*data[row][col]
                    } else if (data[row][col] == data[tempRow][col]) {
                        currentScore += data[tempRow][col];
                        data[row][col] += data[tempRow][col];
                        data[tempRow][col] = 0;
                        hasMove = true;
                        // 监听
                        dataListener.onNumberMerge(row, col, data[row][col], currentScore);
                    }
                }
            }
        }
        if (hasMove) {
            checkGameFinish();
            randomGeneratorNumber();
            checkGameFinish();
        }
    }

    @Override
    public void toDown() {
        if (gameStatus != GameStatus.GAMING) {
            return;
        }
        boolean hasMove = false;
        for (int col = 0; col < colSum; col++) {
            for (int row = rowSum - 1; row >= 0; row--) {
                for (int tempRow = row - 1; tempRow >= 0; tempRow--) {
                    // data[row][tempCol]为空不做任何操作
                    if (data[tempRow][col] == 0) {
                        continue;
                    }
                    // data[row][tempCol]不为空 data[row][col]为空，则直接将data[row][tempCol]移动到data[row][col]
                    if (data[row][col] == 0) {
                        data[row][col] = data[tempRow][col];
                        data[tempRow][col] = 0;
                        hasMove = true;
                        row--;
                        // data[row][tempCol]和data[row][col]相等，则data[row][col]变为2*data[row][col]
                    } else if (data[row][col] == data[tempRow][col]) {
                        currentScore += data[tempRow][col];
                        data[row][col] += data[tempRow][col];
                        data[tempRow][col] = 0;
                        hasMove = true;
                        // 监听
                        dataListener.onNumberMerge(row, col, data[row][col], currentScore);
                    }
                }
            }
        }
        if (hasMove) {
            checkGameFinish();
            randomGeneratorNumber();
            checkGameFinish();
        }
    }

    @Override
    public void toLeft() {
        if (gameStatus != GameStatus.GAMING) {
            return;
        }
        boolean hasMove = false;
        for (int row = 0; row < rowSum; row++) {
            for (int col = 0; col < colSum; col++) {
                for (int tempCol = col + 1; tempCol < colSum; tempCol++) {
                    // data[row][tempCol]为空不做任何操作
                    if (data[row][tempCol] == 0) {
                        continue;
                    }
                    // data[row][tempCol]不为空 data[row][col]为空，则直接将data[row][tempCol]移动到data[row][col]
                    if (data[row][col] == 0) {
                        data[row][col] = data[row][tempCol];
                        data[row][tempCol] = 0;
                        hasMove = true;
                        // data[row][tempCol]和data[row][col]相等，则data[row][col]变为2*data[row][col]
                    } else if (data[row][col] == data[row][tempCol]) {
                        currentScore += data[row][tempCol];
                        data[row][col] += data[row][tempCol];
                        data[row][tempCol] = 0;
                        hasMove = true;
                        // 监听
                        dataListener.onNumberMerge(row, col, data[row][col], currentScore);
                    }
                }
            }
        }
        if (hasMove) {
            checkGameFinish();
            randomGeneratorNumber();
            checkGameFinish();
        }
    }

    @Override
    public void toRight() {
        if (gameStatus != GameStatus.GAMING) {
            return;
        }
        boolean hasMove = false;
        for (int row = 0; row < rowSum; row++) {
            for (int col = colSum - 1; col >= 0; col--) {
                for (int tempCol = col - 1; tempCol >= 0; tempCol--) {
                    // data[row][tempCol]为空不做任何操作
                    if (data[row][tempCol] == 0) {
                        continue;
                    }
                    // data[row][tempCol]不为空 data[row][col]为空，则直接将data[row][tempCol]移动到data[row][col]
                    if (data[row][col] == 0) {
                        data[row][col] = data[row][tempCol];
                        data[row][tempCol] = 0;
                        hasMove = true;
                        // data[row][tempCol]和data[row][col]相等，则data[row][col]变为2*data[row][col]
                    } else if (data[row][col] == data[row][tempCol]) {
                        currentScore += data[row][tempCol];
                        data[row][col] += data[row][tempCol];
                        data[row][tempCol] = 0;
                        hasMove = true;
                        // 监听
                        dataListener.onNumberMerge(row, col, data[row][col], currentScore);
                    }
                }
            }
        }
        if (hasMove) {
            checkGameFinish();
            randomGeneratorNumber();
            checkGameFinish();
        }
    }

    public void checkGameFinish() {
        for (int row = 0; row < rowSum; row++) {
            for (int col = 0; col < colSum; col++) {
                if (data[row][col] == 2048) {
                    gameStatus = GameStatus.WIN;
                    // todo 监听
                    break;
                };
            }
        }
        if (!isMoveable()) {
            gameStatus = GameStatus.GAME_OVER;
            // todo 监听
            return;
        }
    }

    /**
     * 判断是否还可移动
     */
    private boolean isMoveable() {
        // 校验水平方向上是否可移动
        for (int row = 0; row < rowSum; row++) {
            for (int col = 0; col < colSum - 1; col++) {
                // 有空位或连续的两个卡片位置数字相等, 则可移动
                if (data[row][col] == 0 || data[row][col + 1] == 0 || data[row][col] == data[row][col + 1]) {
                    return true;
                }
            }
        }

        // 校验竖直方向上是否可移动
        for (int col = 0; col < colSum; col++) {
            for (int row = 0; row < rowSum - 1; row++) {
                if (data[row][col] == 0 || data[row + 1][col] == 0 || data[row][col] == data[row + 1][col]) {
                    return true;
                }
            }
        }
        return false;
    }
}
