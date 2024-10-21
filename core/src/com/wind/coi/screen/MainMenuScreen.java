package com.wind.coi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.wind.coi.MainGame;

/**
 * @author hsc
 * @date 2024/10/18 13:24
 */
public class MainMenuScreen implements Screen {

    private MainGame game;

    private Stage stage;

    private OrthographicCamera camera;

    public MainMenuScreen(MainGame mainGame) {
        this.game = mainGame;

        // 初始化摄像机
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWindowH(), game.getWindowH());
        camera.update();

        // 初始化Stage
        Viewport viewport = new StretchViewport(game.getWindowH(), game.getWindowH(), camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        // 显示主菜单
        showMainMenu();
    }

    private void showMainMenu() {
        Table table = new Table();
        table.setFillParent(true);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = new BaseDrawable();
        textButtonStyle.font = game.font;

        Button startButton = new TextButton("开始游戏", textButtonStyle);
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });
        // 定义标签样式
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.WHITE;
        Label titleLabel = new Label("Coi game", labelStyle);
        titleLabel.setFontScale(2.0f);

        table.add(titleLabel).expandX().fillX().padBottom(20).row();
        table.add(startButton).expand().fillX().pad(20);
        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);

        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
        stage.getViewport().update(i, i1, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
