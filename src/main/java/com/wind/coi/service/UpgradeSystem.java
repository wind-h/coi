package com.wind.coi.service;


import com.wind.coi.entity.Character;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hsc
 * @date 2024/6/27 14:02
 */
public class UpgradeSystem {

    private static final Map<Integer, Integer> LEVEL_EXP_THRESHOLDS = new HashMap<>();

    static {
        LEVEL_EXP_THRESHOLDS.put(1, 100);
        LEVEL_EXP_THRESHOLDS.put(2, 250);
        LEVEL_EXP_THRESHOLDS.put(3, 500);
        // ...填充其它等级的经验阈值
    }

    /**
     * 角色获得经验并自动提升等级。
     * @param character 角色对象
     * @param exp 获得的经验值
     */
    public static void grantExperience(Character character, int exp) {
        if (character.getLevel() >= getMaxLevel()) {
            return;
        }
        character.setExp(character.getExp() + exp);
        System.out.println(character.getName() + " 当前 exp " + character.getExp());
        while (character.getExp() >= getNextLevelExpThreshold(character.getLevel())) {
            character.setExp(character.getExp() - getNextLevelExpThreshold(character.getLevel()));
            levelUp(character);
        }
    }

    /**
     * 获取指定等级的下一个等级所需的经验值阈值。
     * @param currentLevel 当前等级
     * @return 下一个等级的经验阈值
     */
    private static int getNextLevelExpThreshold(int currentLevel) {
        return LEVEL_EXP_THRESHOLDS.getOrDefault(currentLevel + 1, Integer.MAX_VALUE);
    }

    /**
     * 提升角色等级。
     * @param character 角色对象
     */
    private static void levelUp(Character character) {
        character.setLevel(character.getLevel() + 1);
        System.out.println(character.getName() + " 升级到 Level " + character.getLevel());
    }

    /**
     * 获取游戏的最高等级。
     * @return 最高等级
     */
    private static int getMaxLevel() {
        return LEVEL_EXP_THRESHOLDS.keySet().stream().max(Integer::compareTo).orElse(0);
    }
}
