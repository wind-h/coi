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

    private CameraHelper cameraHelper;

    public WorldController() {
        Core.input.setInputProcessor(this);
        cameraHelper = new CameraHelper();
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
        handleDebugInput(deltaTime);
        updateObjs(deltaTime);
        cameraHelper.update(deltaTime);
    }

    private void handleDebugInput(float deltaTime) {
        if (Core.app.getType() != Application.ApplicationType.Desktop) {
            return;
        }
        // 控制选中的精灵
        float sprMoveSpeed = 5 * deltaTime;
        if (Core.input.isKeyPressed(Input.Keys.A)) {
            // 向后移动
            moveSelectedSprite(-sprMoveSpeed, 0);
        } else  if (Core.input.isKeyPressed(Input.Keys.D)) {
            // 向前移动
            moveSelectedSprite(sprMoveSpeed, 0);
        } else  if (Core.input.isKeyPressed(Input.Keys.W)) {
            // 向上移动
            moveSelectedSprite(0, sprMoveSpeed);
        } else  if (Core.input.isKeyPressed(Input.Keys.S)) {
            // 向下移动
            moveSelectedSprite(0, -sprMoveSpeed);
        }
        // 相机控制(移动)
        float camMoveSpeed = 5 * deltaTime;
        float camMoveSpeedAccelerationFactor = 5;
        if (Core.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            camMoveSpeed *= camMoveSpeedAccelerationFactor;
        } else if (Core.input.isKeyPressed(Input.Keys.LEFT)) {
            // 向后移动
            moveCamera(-camMoveSpeed, 0);
        } else  if (Core.input.isKeyPressed(Input.Keys.RIGHT)) {
            // 向前移动
            moveCamera(camMoveSpeed, 0);
        } else  if (Core.input.isKeyPressed(Input.Keys.UP)) {
            // 向上移动
            moveCamera(0, camMoveSpeed);
        } else  if (Core.input.isKeyPressed(Input.Keys.DOWN)) {
            // 向下移动
            moveCamera(0, -camMoveSpeed);
        } else if (Core.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            cameraHelper.setPosition(0,0);
        }

        // 相机控制(缩放)
        float camZoomSpeed = 1 * deltaTime;
        float camZoomSpeedAccelerationFactor = 5;
        if (Core.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            camZoomSpeed *= camZoomSpeedAccelerationFactor;
        } else if (Core.input.isKeyPressed(Input.Keys.COMMA)) {
            cameraHelper.addZoom(camZoomSpeed);
        } else  if (Core.input.isKeyPressed(Input.Keys.PERIOD)) {
            cameraHelper.addZoom(-camZoomSpeed);
        } else  if (Core.input.isKeyPressed(Input.Keys.SLASH)) {
            // 按下斜杆键将相机的缩放引子置为1
            cameraHelper.setZoom(1);
        }
    }

    private void moveSelectedSprite(float x, float y) {
        // 设置相对于绘制角色的当前位置的位置。
        sprites[selectedSprite].translate(x, y);
    }

    private void moveCamera(float x, float y) {
        x += cameraHelper.getPosition().x;
        y += cameraHelper.getPosition().y;
        cameraHelper.setPosition(x, y);
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
            if (cameraHelper.hasTarget()) {
                cameraHelper.setTarget(sprites[selectedSprite]);
            }
            Core.app.debug(TAG, "sprites #" + selectedSprite + " selected");
        } else if (keycode == Input.Keys.ENTER) {
            cameraHelper.setTarget(cameraHelper.hasTarget() ? null : sprites[selectedSprite]);
            Core.app.debug(TAG, "camera follow enable:" + cameraHelper.hasTarget());
        }
        return false;
    }

    public CameraHelper getCameraHelper() {
        return cameraHelper;
    }

    public void setCameraHelper(CameraHelper cameraHelper) {
        this.cameraHelper = cameraHelper;
    }
}
