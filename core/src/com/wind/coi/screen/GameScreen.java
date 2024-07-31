package com.wind.coi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.wind.coi.*;
import com.wind.coi.service.impl.InputHandler;
import com.wind.coi.service.impl.OrthoCamera;
import com.wind.coi.service.impl.Player;
import com.wind.coi.service.impl.GameWorld;

public class GameScreen implements Screen {

    private MainGame game;

    private SpriteBatch batch;

    private GameWorld gameWorld;

    private OrthoCamera camera;

    private Player player;

    public GameScreen(MainGame game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthoCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player = new Player(this.game.getAssetManager().get("sprite/no_anim_0.png"), camera);
        gameWorld = new GameWorld();
        gameWorld.addUpdateable(player);
        gameWorld.addRenderable(player);
        Gdx.input.setInputProcessor(new InputHandler(player));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(255, 255, 255, 1);
        camera.update(delta);
        batch.setProjectionMatrix(camera.getCamera().combined);
        batch.begin();
        gameWorld.render(batch);
        batch.end();
        gameWorld.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthoCamera(width, height);
        camera.setPosition(player.getPosition());
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
        batch.dispose();
    }
}