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
import com.wind.coi.MainGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    // 在GameScreen类中添加敌人类
    private class Enemy implements Renderable {
        Texture texture;
        Vector2 position;
        Vector2 velocity;

        Enemy(Texture texture) {
            this.texture = texture;
            this.position = new Vector2();
            this.velocity = new Vector2();
        }

        @Override
        public void update(float delta) {
            // 更新敌人位置
            position.x += velocity.x * delta;
            position.y += velocity.y * delta;

            // 边界检查，如果敌人离开屏幕，重置其位置
            if (position.x > Gdx.graphics.getWidth() || position.x < 0 ||
                    position.y > Gdx.graphics.getHeight() || position.y < 0) {
                reset();
            }
        }

        @Override
        public void render(SpriteBatch batch) {
            batch.draw(texture, position.x - texture.getWidth() / 2f,
                    position.y - texture.getHeight() / 2f);
        }

        void reset() {
            // 重新设置敌人的位置和速度
            Random random = new Random();
            position.set(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight());
            velocity.set(random.nextFloat() * 100 - 50, random.nextFloat() * 100 - 50);
        }
    }

    public interface Renderable {

        void update(float delta);

        void render(SpriteBatch batch);
    }

    public class GameWorld implements Renderable {

        private List<Renderable> renderableList;

        public GameWorld() {
            renderableList = new ArrayList<>();
        }

        @Override
        public void update(float delta) {
            for (Renderable renderable : renderableList) {
                renderable.update(delta);
            }
        }

        @Override
        public void render(SpriteBatch batch) {
            for (Renderable renderable : renderableList) {
                renderable.render(batch);
            }
        }

        public void addRenderable(Renderable renderable) {
            renderableList.add(renderable);
        }

        public <T extends Renderable> void addRenderableList(List<T> renderableList) {
            this.renderableList.addAll(renderableList);
        }
    }

    public class Player implements Renderable {

        // 玩家纹理
        private Texture playerBg;

        // 玩家位置
        private Vector2 playerPosition;

        // 玩家速度
        private Vector2 playerVelocity;

        public Player(Texture playerBg) {
            this.playerBg = playerBg;
            playerPosition = new Vector2(playerBg.getWidth(), playerBg.getHeight());
            playerVelocity = new Vector2();
        }

        public void move(float x, float y) {
            playerVelocity.x += x * 20f;
            playerVelocity.y += y * 20f;
        }

        @Override
        public void update(float delta) {
            // 根据速度和时间更新玩家位置
            playerPosition.x += playerVelocity.x * delta;
            playerPosition.y += playerVelocity.y * delta;
        }

        @Override
        public void render(SpriteBatch batch) {
            batch.draw(playerBg, playerPosition.x - playerBg.getWidth() / 2f, playerPosition.y - playerBg.getHeight() / 2f);
        }
    }

    public class Bullet implements Renderable {

        private Texture buBg;

        private TextureRegion buBgRe;

        // 玩家位置
        private Vector2 position;

        // 玩家速度
        private Vector2 velocity;

        public Bullet(Texture buBg) {
            this.buBg = buBg;
            this.buBgRe = new TextureRegion(buBg);
            position = new Vector2();
            velocity = new Vector2();
        }

        public Bullet(Texture buBg, Vector2 position, Vector2 velocity) {
            this.position = position;
            this.velocity = velocity;
            this.buBg = buBg;
            this.buBgRe = new TextureRegion(buBg);
        }

        @Override
        public void update(float delta) {
            // 更新子弹位置
            position.x += velocity.x * delta;
            position.y += velocity.y * delta;
        }

        @Override
        public void render(SpriteBatch batch) {
            // 缩放因子，例如0.5表示子弹大小为原来的一半
            float scale = 0.5f;

            // 获取子弹纹理的宽度和高度
            float buWidth = buBg.getWidth() * scale;
            float buHeight = buBg.getHeight() * scale;

            // 使用缩放参数来绘制子弹
            batch.draw(buBgRe, position.x - buWidth / 2f, position.y - buHeight / 2f,
                    buWidth / 2f, buHeight / 2f, buWidth, buHeight, 1, 1, 0);
        }
    }
}