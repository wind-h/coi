package com.wind.coi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.wind.coi.screen.MainMenuScreen;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.DEFAULT_CHARS;

/**
 * @author hsc
 * @date 2024/10/18 13:20
 */
public class MainGame extends Game {

    private AssetManager assetManager;

    private Integer windowW;

    private Integer windowH;

    public BitmapFont font;

    @Override
    public void create() {
        loadResources();
        windowW = Gdx.app.getGraphics().getWidth();
        windowH = Gdx.app.getGraphics().getHeight();
        setScreen(new MainMenuScreen(this));
    }

    private void loadResources() {
        assetManager = new AssetManager();
        // 草地
        assetManager.load("grass.png", Texture.class);
        // 石头
        assetManager.load("stone.png", Texture.class);
        // 水
        assetManager.load("water.png", Texture.class);
        // 在适当的地方初始化
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/simhei.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameter.color = Color.WHITE;
        freeTypeFontParameter.size = 40;
        freeTypeFontParameter.characters = DEFAULT_CHARS + "你需要的文字，不能重复，都写在这里开始游戏";
        font = generator.generateFont(freeTypeFontParameter);
        assetManager.finishLoading();
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

    public Integer getWindowW() {
        return windowW;
    }

    public Integer getWindowH() {
        return windowH;
    }
}
