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
        camera.update();
        return true;
    }

}
