package com.wind.coi;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.wind.coi.constant.ResourceConstant;
import com.wind.coi.screen.GameScreen;

public class MainGame extends Game {

    private static final String LOG = MainGame.class.getName();

    private float worldWidth;

    private float worldHeight;

    private AssetManager assetManager;

    /**
     * 纹理图集
     */
    private TextureAtlas atlas;

    /**
     * 字体
     */
    private BitmapFont font;

    private GameScreen gameScreen;

    @Override
    public void create() {
        // 设置日志输出级别
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        // 固定宽度
        worldWidth = ResourceConstant.FIX_WORLD_WIDTH;
        // 高度随宽度改变
        worldHeight = Gdx.app.getGraphics().getHeight() * (worldWidth / Gdx.app.getGraphics().getWidth());
        Gdx.app.debug(LOG, "world w:" + worldWidth + " h:" + worldHeight);

        // 创建资源管理器
        assetManager = new AssetManager();
        assetManager.load(ResourceConstant.ATLAS_PATH, TextureAtlas.class);
        assetManager.load(ResourceConstant.BITMAP_FONT_PATH, BitmapFont.class);
        assetManager.load(ResourceConstant.Audios.MERGE, Sound.class);
        assetManager.load(ResourceConstant.Audios.MOVE, Sound.class);

        // 等待资源加载完成
        assetManager.finishLoading();

        // 获取纹理和字体
        atlas = assetManager.get(ResourceConstant.ATLAS_PATH);
        font = assetManager.get(ResourceConstant.BITMAP_FONT_PATH);

        // 创建游戏主场景
        gameScreen = new GameScreen(this);

        // 设置当前场景
        setScreen(gameScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (assetManager != null) {
            assetManager.dispose();
        }
        if (gameScreen != null) {
            gameScreen.dispose();;
        }
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void setAtlas(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    public float getWorldWidth() {
        return worldWidth;
    }

    public void setWorldWidth(float worldWidth) {
        this.worldWidth = worldWidth;
    }

    public float getWorldHeight() {
        return worldHeight;
    }

    public void setWorldHeight(float worldHeight) {
        this.worldHeight = worldHeight;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
}
