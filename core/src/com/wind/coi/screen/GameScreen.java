package com.wind.coi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.wind.coi.MainGame;
import com.wind.coi.actor.InputHandler;
import com.wind.coi.actor.Player;

/**
 * @author hsc
 * @date 2024/7/26 13:39
 */
public class GameScreen implements Screen {

    private MainGame game;

    private SpriteBatch batch;

    private OrthographicCamera camera;

    private Player player;

    private InputHandler inputHandler;

    public GameScreen(MainGame game) {
        this.game = game;
        batch = new SpriteBatch();
        player = new Player(this.game.getAssetManager().get("sprite/no_anim_0.png"));
        player.setPosition(new Vector2(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f));
        camera = new OrthographicCamera(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getWidth());
        camera.update();
        inputHandler = new InputHandler(player);
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // 清屏
        ScreenUtils.clear(255, 255, 255, 1);
        // 设置相机位置
        camera.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);
        camera.update();
        // 更新玩家的速度向量
        inputHandler.updateVelocity();
        // 开始
        batch.setProjectionMatrix(camera.combined);
        // 开始
        batch.begin();
        // 绘制
        batch.draw(player.getTexture(), player.getPosition().x - (float) player.getTexture().getWidth() / 2,
                player.getPosition().y - (float) player.getTexture().getHeight() / 2);        // 结束
        batch.end();
        // 更新玩家位置
        player.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
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
        player.getTexture().dispose();
    }

}
