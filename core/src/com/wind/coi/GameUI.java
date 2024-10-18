package com.wind.coi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.wind.coi.screen.GameScreen;
import com.wind.coi.screen.MainMenuScreen;

public class GameUI {

    private Stage stage;
    MainGame game;

    public GameUI(GameScreen gameScreen) {
        this.game = gameScreen.game;
        initializeUI();
    }

    private void initializeUI() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWindowW(), game.getWindowH());
        camera.update();

        Viewport viewport = new StretchViewport(game.getWindowW(), game.getWindowH(), camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        showBackButton();
    }

    private void showBackButton() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = new BaseDrawable();
        textButtonStyle.font = new BitmapFont();

        Button backButton = new TextButton("Back to Menu", textButtonStyle);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.top().left();
        table.add(backButton).pad(10);

        stage.addActor(table);
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }
}