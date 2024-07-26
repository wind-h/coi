package com.wind.coi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * @author hsc
 * @date 2024/7/26 11:18
 */
public class NewMainGame extends Game {

    private AssetManager assetManager;

    private NewGameScreen screen;

    @Override
    public void create () {
        // 初始化
        assetManager = new AssetManager();
        assetManager.load("sprite/no_anim_0.png", Texture.class);
        assetManager.finishLoading();
        screen = new NewGameScreen(this);
        setScreen(screen);
    }


    @Override
    public void dispose() {
        screen.dispose();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
}
