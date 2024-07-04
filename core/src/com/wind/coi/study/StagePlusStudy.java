package com.wind.coi.study;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class StagePlusStudy extends ApplicationAdapter {

    private static final String TAG = StagePlusStudy.class.getSimpleName();

    Texture img;

    CustomActor actor;

    Stage stage;

    @Override
    public void create() {
        img = new Texture("badlogic.jpg");
        actor = new CustomActor(new TextureRegion(img));
        stage = new Stage();
        stage.addActor(actor);
        // 位置
        actor.setPosition(64, 128);
        // 设置 旋转 和 缩放 的 起点
        actor.setOrigin((float) img.getHeight() / 2, (float) img.getWidth() / 2);
        // 设置旋转角度
        actor.setRotation(-45.0f);
        // 设置精灵的 X 和 Y 轴方向的缩放比
        actor.setScale(0.6f, 0.6f);

        Gdx.input.setInputProcessor(stage);
        stage.addListener(new CustomInputListener());
        actor.addListener(new CustomClickListener());
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        actor.act(Gdx.graphics.getDeltaTime());
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        img.dispose();
    }

    public static class CustomInputListener extends InputListener {

        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.UP: {
                    Gdx.app.log(TAG, "被按下的按键: 方向上键");
                    break;
                }
                case Input.Keys.DOWN: {
                    Gdx.app.log(TAG, "被按下的按键: 方向下键 ");
                    break;
                }
                case Input.Keys.A: {
                    Gdx.app.log(TAG, "被按下的按键: A键");
                    break;
                }
                case Input.Keys.ENTER: {
                    Gdx.app.log(TAG, "被按下的按键: 回车键");
                    break;
                }
                default: {
                    Gdx.app.log(TAG, "其他按键, KeyCode: " + keycode);
                    break;
                }
            }
            return false;
        }
    }

    /**
     * 点击 监听器（只包括 手指点击 或 鼠标点击）
     */
    public static class CustomClickListener extends ClickListener {

        /**
         * 对象（演员/舞台）被点击时调用
         * @param x 点击时（手指抬起时）的 X 轴坐标, 相对于被点击对象（监听器注册者）的左下角
         * @param y 点击时（手指抬起时）的 Y 轴坐标, 相对于被点击对象（监听器注册者）的左下角
         */
        @Override
        public void clicked(InputEvent event, float x, float y) {
            // 获取响应这个点击事件的演员
            Actor actor = event.getListenerActor();
            Gdx.app.log(TAG, "被点击: " + x + ", " + y + "; Actor: " + actor.getClass().getSimpleName());
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
