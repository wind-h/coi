package com.wind.coi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.wind.coi.*;
import com.wind.coi.character.Bullet;
import com.wind.coi.character.Enemy;
import com.wind.coi.character.GameWorld;
import com.wind.coi.character.Player;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    private OrthographicCamera camera;

    private MainGame game;

    private SpriteBatch batch;

    // 添加敌人列表
    private List<Enemy> enemyList;

    private float timer = 0f; // 初始化计时器
    private final float ENEMY_SPAWN_TIME = 5f; // 敌人生成间隔时间，单位为秒

    private Player player;

    private List<Bullet> bulletList;

    private GameWorld gameWorld;

    public GameScreen(MainGame game) {
        camera = new OrthographicCamera(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        this.game = game;
        batch = new SpriteBatch();
        player = new Player(this.game.getAssetManager().get("sprite/no_anim_0.png"));
        gameWorld = new GameWorld();
        gameWorld.addRenderable(player);
        bulletList = new ArrayList<>();
        Gdx.input.setInputProcessor(new InputHandler(player));
        enemyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) { // 生成10个敌人
            Enemy enemy = new Enemy(game.getAssetManager().get("sprite/no_anim_0.png"));
            enemy.reset();
            enemyList.add(enemy);
            gameWorld.addRenderable(enemy);
        }
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
        gameWorld.render(batch);
        batch.end();
        spawnEnemies(delta);
        gameWorld.update(delta);
    }

    // 敌人生成逻辑
    private void spawnEnemies(float delta) {
        // 更新计时器
        timer += delta;

        // 检查是否到了生成敌人的时刻
        if (timer >= ENEMY_SPAWN_TIME) {
            // 生成敌人
            Enemy enemy = new Enemy(game.getAssetManager().get("sprite/no_anim_0.png"));
            enemy.reset();
            enemyList.add(enemy);
            gameWorld.addRenderable(enemy);
            // 重置计时器
            timer = 0f;
        }
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

        private Player player;

        public InputHandler(Player player) {
            this.player = player;
        }

        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
                player.move(-1, 0);
            } else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
                player.move(1, 0);
            } else if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
                player.move(0, 1);
            } else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
                player.move(0, -1);
            }
            return true;
        }

        @Override
        public boolean keyUp(int keycode) {
            if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
                player.move(1, 0);
            } else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
                player.move(-1, 0);
            } else if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
                player.move(0, -1);
            } else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
                player.move(0, 1);
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
                Vector2 bulletPosition = new Vector2(player.playerPosition.x, player.playerPosition.y);
                // 计算从玩家到鼠标点击位置的向量
                Vector2 direction = mousePos.cpy().sub(player.playerPosition).nor();
                // 设置子弹速度，例如200单位/秒
                Vector2 bulletVelocity = direction.scl(200);
                Bullet bullet = new Bullet(game.getAssetManager().get("circular.png"), bulletPosition, bulletVelocity);
                bulletList.add(bullet);
                gameWorld.addRenderable(bullet);
            }
            return true;
        }
    }
}