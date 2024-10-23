package com.wind.coi;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.wind.coi.core.Core;

/**
 * @author hsc
 * @date 2024/10/23 16:59
 */
public class WorldController extends InputAdapter {

    public final static String TAG = WorldController.class.getName();

    private Sprite[] sprites;

    private int selectedSprite;

    public WorldController() {
        Core.input.setInputProcessor(this);
        init();
    }

    private void init() {
        sprites = new Sprite[5];
        int width = 32;
        int height = 32;
        Pixmap pixmap = createProceduralPixmap(width, height);
        Texture texture = new Texture(pixmap);
        for (int i = 0; i < sprites.length; i++) {
            Sprite sprite = new Sprite(texture);
            sprite.setSize(1, 1);
            sprite.setOrigin(sprite.getWidth() / 2f, sprite.getHeight() / 2);
            float randomX = MathUtils.random(-2f, 2f);
            float randomY = MathUtils.random(-2f, 2f);
            sprite.setPosition(randomX, randomY);
            sprites[i] = sprite;
        }
        selectedSprite = 0;

    }

    public Pixmap createProceduralPixmap(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 0, 0, 0.5f);
        pixmap.fill();
        pixmap.setColor(1,1,0, 1);
        pixmap.drawLine(0, 0, width, height);
        pixmap.drawLine(width, 0, 0, height);
        pixmap.setColor(0, 1,1,1);
        pixmap.drawRectangle(0,0, width, height);
        return pixmap;
    }

    public void update(float deltaTime) {
        updateObjs(deltaTime);
    }

    private void handleDebugInput(float deltaTime) {
        if (Core.app.getType() != Application.ApplicationType.Desktop) {
            return;
        }
        float speed = 5 * deltaTime;
        if (Core.input.isKeyPressed(Input.Keys.A)) {
            moveSelectedSprite(-speed, 0);
        } else  if (Core.input.isKeyPressed(Input.Keys.D)) {
            moveSelectedSprite(speed, 0);
        } else  if (Core.input.isKeyPressed(Input.Keys.W)) {
            moveSelectedSprite(0, speed);
        } else  if (Core.input.isKeyPressed(Input.Keys.S)) {
            moveSelectedSprite(0, -speed);
        }
    }

    private void moveSelectedSprite(float x, float y) {
        sprites[selectedSprite].translate(x, y);
    }

    private void updateObjs(float deltaTime) {
        float rotation = sprites[selectedSprite].getRotation();
        rotation += 90 * deltaTime;
        rotation %= 360;
        sprites[selectedSprite].setRotation(rotation);
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.R) {
            init();
            Core.app.debug(TAG, "game world resetted");
        } else if (keycode == Input.Keys.SPACE) {
            selectedSprite = (selectedSprite + 1) % sprites.length;
            Core.app.debug(TAG, "sprites #" + selectedSprite + " selected");
        }
        return false;
    }
}
