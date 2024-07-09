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
        String GAME_BLANK = "game_blank";
        String GAME_LOGO = "game_logo";
        String GAME_RECT_BG = "game_rect_bg";
        String GAME_ROUND_RECT = "game_round_rect";
        String GAME_SCORE_BG_BEST = "game_score_bg_best";
        String GAME_SCORE_BG_NOW = "game_score_bg_now";
        String GAME_BTN_EXIT = "game_btn_exit";
        String GAME_BTN_HELP = "game_btn_help";
    }

    /**
     * 音频资源
     */
    public interface Audios {
        String MOVE = "audio/move.mp3";
        String MERGE = "audio/merge.mp3";
    }

    /**
     * 不同数字的卡片背景颜色
     */
    public interface CardColors {
        Color RGBA_0 = new Color(0xCCC0B3FF);
        Color RGBA_2 = new Color(0xEEE4DAFF);
        Color RGBA_4 = new Color(0xEDE0C8FF);
        Color RGBA_8 = new Color(0xF2B179FF);
        Color RGBA_16 = new Color(0xF49563FF);
        Color RGBA_32 = new Color(0xF5794DFF);
        Color RGBA_64 = new Color(0xF55D37FF);
        Color RGBA_128 = new Color(0xEEE863FF);
        Color RGBA_256 = new Color(0xEDB04DFF);
        Color RGBA_512 = new Color(0xECB04DFF);
        Color RGBA_1024 = new Color(0xEB9437FF);
        Color RGBA_2048 = new Color(0xEA7821FF);
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
