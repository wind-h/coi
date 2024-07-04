package com.wind.coi.study;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

public class GroupStudy extends ApplicationAdapter {

    Texture img;

    Group group;

    CustomActor actor;

    CustomActor actor2;

    Stage stage;

    @Override
    public void create() {
        img = new Texture("badlogic.jpg");
        stage = new Stage();
        group = new Group();

        actor = new CustomActor(new TextureRegion(img));
        // 位置
        actor.setPosition(0, 0);
        // 设置 旋转 和 缩放 的 起点
        actor.setOrigin(0, 0);
        // 设置旋转角度
        actor.setRotation(-45.0f);
        group.addActor(actor);

        actor2 = new CustomActor(new TextureRegion(img));
        // 位置
        actor2.setPosition(100, 100);
        // 设置 旋转 和 缩放 的 起点
        actor2.setOrigin((float) img.getHeight(), (float) img.getWidth());
        // 设置旋转角度
        actor2.setRotation(45.0f);
        group.addActor(actor2);

        stage.addActor(group);

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
