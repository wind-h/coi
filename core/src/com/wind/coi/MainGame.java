package com.wind.coi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.wind.coi.screen.MainMenuScreen;

/**
 * @author hsc
 * @date 2024/10/18 13:20
 */
public class MainGame extends Game {

    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        // 草地
        assetManager.load("grass.png", Texture.class);
        // 石头
        assetManager.load("stone.png", Texture.class);
        // 水
        assetManager.load("water.png", Texture.class);
        assetManager.finishLoading();
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        super.dispose();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
