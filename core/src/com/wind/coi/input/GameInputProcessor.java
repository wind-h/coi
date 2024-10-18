package com.wind.coi.input;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

/**
 * @author hsc
 * @date 2024/10/18 15:33
 */
public class GameInputProcessor extends InputAdapter {

    private OrthographicCamera camera;
    private Vector3 touchDownPos;
    private boolean isDragging;

    public GameInputProcessor(OrthographicCamera camera) {
        this.camera = camera;
    }

    private static final float MIN_ZOOM = 0.25f; // 最小缩放值
    private static final float MAX_ZOOM = 1.25f;  // 最大缩放值

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchDownPos = new Vector3(screenX, screenY, 0);
        camera.unproject(touchDownPos);
        isDragging = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        isDragging = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (isDragging) {
            Vector3 currentTouchPos = new Vector3(screenX, screenY, 0);
            camera.unproject(currentTouchPos);
            camera.translate(touchDownPos.x - currentTouchPos.x, touchDownPos.y - currentTouchPos.y);
            touchDownPos.set(currentTouchPos);
            clampCamera();
            camera.update();
        }
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (amountY > 0) {
            camera.zoom /= 0.9f; // 缩小
        } else if (amountY < 0) {
            camera.zoom *= 0.9f; // 放大
        }
        clampCamera();
        camera.update();
        return true;
    }

    private void clampCamera() {
        float halfWidth = camera.viewportWidth * camera.zoom / 2;
        float halfHeight = camera.viewportHeight * camera.zoom / 2;

        // 限制摄像机位置
        camera.position.x = Math.max(halfWidth, Math.min(camera.position.x, 160 * 32 - halfWidth));
        camera.position.y = Math.max(halfHeight, Math.min(camera.position.y, 160 * 32 - halfHeight));

        // 限制摄像机缩放
        camera.zoom = Math.max(MIN_ZOOM, Math.min(camera.zoom, MAX_ZOOM));
    }

}
