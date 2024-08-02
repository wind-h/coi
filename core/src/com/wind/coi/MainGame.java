package com.wind.coi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.wind.coi.screen.GameScreen;

/**
 * @author hsc
 * @date 2024/7/26 11:18
 */
public class MainGame extends Game {

    private AssetManager assetManager;

    private GameScreen screen;

    @Override
    public void create() {
        // 资源初始化
        assetManager = new AssetManager();
        assetManager.load("sprite/no_anim_0.png", Texture.class);
        assetManager.load("circular.png", Texture.class);
        assetManager.finishLoading();
        screen = new GameScreen(this);
        setScreen(screen);
    }


    @Override
    public void dispose() {
        screen.dispose();
        assetManager.dispose();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
}
