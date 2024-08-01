package com.wind.coi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.wind.coi.*;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    private OrthographicCamera camera;

    // 玩家纹理
    private Texture playerBg;
    // 玩家位置
    private Vector2 playerPosition;
    // 玩家速度
    private Vector2 playerVelocity;

    private Texture buBg;

    private TextureRegion buBgRe;

    private List<Vector2> buList;
    private List<Vector2> buVelocityList; // 存储所有子弹的速度

    private MainGame game;

    private SpriteBatch batch;


    public GameScreen(MainGame game) {
        camera = new OrthographicCamera(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        this.game = game;
        batch = new SpriteBatch();
        playerBg = this.game.getAssetManager().get("sprite/no_anim_0.png");
        playerPosition = new Vector2(playerBg.getWidth(), playerBg.getHeight());
        playerVelocity = new Vector2();
        buBg = this.game.getAssetManager().get("circular.png");
        buBgRe = new TextureRegion(buBg);
        buList = new ArrayList<>();
        buVelocityList = new ArrayList<>();
        Gdx.input.setInputProcessor(new InputHandler());
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(255, 255, 255, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // 根据玩家位置和纹理大小渲染玩家纹理
        batch.draw(playerBg, playerPosition.x -  playerBg.getWidth() / 2f, playerPosition.y - playerBg.getHeight() / 2f);
        for (int i = 0; i < buList.size(); i++) {
            Vector2 position = buList.get(i);
            Vector2 velocity = buVelocityList.get(i);

            // 更新子弹位置
            position.x += velocity.x * delta;
            position.y += velocity.y * delta;
            // 缩放因子，例如0.5表示子弹大小为原来的一半
            float scale = 0.5f;

            // 获取子弹纹理的宽度和高度
            float buWidth = buBg.getWidth() * scale;
            float buHeight = buBg.getHeight() * scale;

            // 使用缩放参数来绘制子弹
            batch.draw(buBgRe, position.x - buWidth / 2f, position.y - buHeight / 2f,
                    buWidth / 2f, buHeight / 2f, buWidth, buHeight, 1, 1, 0);
        }
        batch.end();
        // 根据速度和时间更新玩家位置
        playerPosition.x += playerVelocity.x * delta;
        playerPosition.y += playerVelocity.y * delta;
    }

    public void move(float x, float y) {
        playerVelocity.x += x * 20f;
        playerVelocity.y += y * 20f;
    }

    @Override
    public void resize(int width, int height) {

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

    public class InputHandler extends InputAdapter {

        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
                move(-1, 0);
            } else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
                move(1, 0);
            } else if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
                move(0, 1);
            } else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
                move(0, -1);
            }
            return true;
        }

        @Override
        public boolean keyUp(int keycode) {
            if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
                move(1, 0);
            } else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
                move(-1, 0);
            } else if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
                move(0, -1);
            } else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
                move(0, 1);
            }
            return true;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (button == Input.Buttons.LEFT) {
                // 获取鼠标点击位置
                Vector2 mousePos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
                Vector3 tempVec3 = new Vector3(mousePos.x, mousePos.y, 0f);
                camera.unproject(tempVec3);
                // 将屏幕坐标转换为游戏世界坐标
                camera.unproject(tempVec3.set(screenX, Gdx.graphics.getHeight() - screenY, 0f));

                // 创建子弹的位置
                Vector2 bulletPosition = new Vector2(playerPosition.x, playerPosition.y);
                // 计算从玩家到鼠标点击位置的向量
                Vector2 direction = mousePos.cpy().sub(playerPosition).nor();
                // 设置子弹速度，例如200单位/秒
                Vector2 bulletVelocity = direction.scl(200);

                // 添加子弹到列表中
                buList.add(bulletPosition);
                buVelocityList.add(bulletVelocity);
            }
            return true;
        }
    }
}