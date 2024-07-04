package com.wind.coi.study;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

public class StageStudy extends ApplicationAdapter {

    Texture img;

    ActorStudy.CustomActor actor;

    Stage stage;

    @Override
    public void create() {
        img = new Texture("badlogic.jpg");
        actor = new ActorStudy.CustomActor(new TextureRegion(img));
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
