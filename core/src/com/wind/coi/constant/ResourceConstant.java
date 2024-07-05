package com.wind.coi.constant;

import com.badlogic.gdx.graphics.Color;

/**
 * @author hsc
 * @date 2024/7/5 11:29
 */
public class ResourceConstant {

    /**
     * 固定的世界宽度
     */
    public static final float FIX_WORLD_WIDTH = 480.0F;

    /**
     * 纹理途径
     */
    public static final String ATLAS_PATH = "atlas/game.atlas";

    /**
     * 字体 文件路径
     */
    public static final String BITMAP_FONT_PATH = "font/bitmap_font.fnt";

    /**
     * 背景颜色
     */
    public static final Color BG_RGBA = new Color(0xEEE7D4FF);


    public interface AtlasNames {

        String GAME_LOGO = "game_logo";
        String GAME_SCORE_BG_BEST = "game_score_bg_best";
        String GAME_SCORE_BG_NOW = "game_score_bg_now";

    }

    /**
     * Preferences 键值对存储的相关常量
     */
    public interface Prefs {

        /**
         * Preferences 键值对存储文件名称
         */
        String FILE_NAME = "game_preferences";

        /**
         * 存储字段名的 key: 最佳分数
         */
       String KEY_BEST_SCORE = "best_score";
    }

}
