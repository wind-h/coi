package com.wind.coi.study;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenStudy extends Game {

    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 800;

    // 开始场景
    private StartScreen startScreen;

    // 主游戏场景
    private GameScreen gameScreen;

    @Override
    public void create() {
        // 创建开始场景
        startScreen = new StartScreen(this);

        // 创建主游戏场景
        gameScreen = new GameScreen();

        // 设置当前场景为开始场景
        setScreen(startScreen);
    }

    /**
     * 开始场景展示完毕后调用该方法切换到主游戏场景
     */
    public void showGameScreen() {
        // 设置当前场景为主游戏场景
        setScreen(gameScreen);

        if (startScreen != null) {
            // 由于 StartScreen 只有在游戏启动时展示一下, 之后都不需要展示,
            // 所以启动完 GameScreen 后手动调用 StartScreen 的 dispose() 方法销毁开始场景。
            startScreen.dispose();

            // 场景销毁后, 场景变量值空, 防止二次调用 dispose() 方法
            startScreen = null;
        }
    }

    @Override
    public void dispose() {
        super.dispose(); // super.dispose() 不能删除, 在父类中还有其他操作（调用当前场景的 hide 方法）
        // 游戏程序退出时, 手动销毁还没有被销毁的场景
        if (startScreen != null) {
            startScreen.dispose();
            startScreen = null;
        }
        if (gameScreen != null) {
            gameScreen.dispose();
            gameScreen = null;
        }
    }

    public static class GameScreen extends ScreenAdapter {

        Texture img;

        CustomActor actor;

        Stage stage;

        public GameScreen() {
            img = new Texture("badlogic.jpg");
            stage = new Stage();
            actor = new CustomActor(new TextureRegion(img));
            // 位置
            actor.setPosition(0, 0);
            stage.addActor(actor);
        }

        @Override
        public void render(float delta) {
            ScreenUtils.clear(0, 0, 0, 1);
            stage.act();
            stage.draw();
        }

        @Override
        public void dispose() {
            stage.dispose();
            img.dispose();
        }
    }

    public static class StartScreen implements Screen {

        private ScreenStudy mainGame;

        private Texture logo;

        private Stage stage;

        private GroupStudy.CustomActor actor;

        private float deltaSum;

        public StartScreen(ScreenStudy mainGame) {
            this.mainGame = mainGame;
            stage = new Stage();
            logo = new Texture("img.png");
            actor = new GroupStudy.CustomActor(new TextureRegion(logo));
            actor.setPosition(
                    stage.getWidth() / 2 - actor.getWidth() / 2,
                    stage.getHeight() / 2 - actor.getHeight() / 2);
            stage.addActor(actor);

        }

        @Override
        public void show() {
            deltaSum = 0;
        }

        @Override
        public void render(float v) {
            deltaSum += v;
            if (deltaSum >= 3.0f) {
                if (mainGame != null) {
                    mainGame.showGameScreen();
                    return;
                }
            }
            // 使用淡蓝色清屏
            Gdx.gl.glClearColor(0.75F, 1, 0.98F, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // 更新舞台逻辑
            stage.act();
            // 绘制舞台
            stage.draw();
        }

        @Override
        public void resize(int i, int i1) {

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
            logo.dispose();
            stage.dispose();
        }
    }

    public static class CustomActor extends Actor {

        private TextureRegion region;

        public CustomActor(TextureRegion region) {
            super();
            this.region = region;
            // 将演员的宽高设置为纹理区域的宽高（必须设置, 否则宽高默认都为 0, 绘制后看不到）
            setSize(this.region.getRegionWidth(), this.region.getRegionHeight());
        }

        public TextureRegion getRegion() {
            return region;
        }

        public void setRegion(TextureRegion region) {
            this.region = region;
            setSize(this.region.getRegionWidth(), this.region.getRegionHeight());
        }

        @Override
        public void act(float delta) {
            super.act(delta);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            if (region == null || !isVisible()) {
                return;
            }
            batch.draw(region, getX(), getY(),
                    getOriginX(), getOriginY(),
                    getWidth(), getHeight(),
                    getScaleX(), getScaleY(),
                    getRotation()
            );
        }
    }
}
