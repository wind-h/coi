package com.wind.coi.service.impl;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * 正交摄像机类，用于管理游戏或应用中的摄像机位置和缩放。
 * 该类简化了摄像机的更新过程，使其能够平滑地跟踪目标位置。
 */
public class OrthoCamera {

    private OrthographicCamera camera; // 正交摄像机实例
    private Vector2 targetPosition; // 摄像机的目标位置
    private float smoothing; // 平滑因子，用于控制摄像机移动的速度

    /**
     * 构造函数初始化正交摄像机。
     *
     * @param viewportWidth 摄像机视口的宽度。
     * @param viewportHeight 摄像机视口的高度。
     */
    public OrthoCamera(float viewportWidth, float viewportHeight) {
        camera = new OrthographicCamera(viewportWidth, viewportHeight);
        camera.update();
        targetPosition = new Vector2();
        smoothing = 0.1f;
    }

    /**
     * 设置摄像机的目标位置。
     *
     * @param position 目标位置的坐标。
     */
    public void setPosition(Vector2 position) {
        targetPosition.set(position);
    }

    /**
     * 更新摄像机的位置，使其平滑地移动到目标位置。
     *
     * @param delta 时间间隔，用于计算摄像机的移动速度。
     */
    public void update(float delta) {
        camera.update();
    }

    /**
     * 获取当前摄像机实例。
     *
     * @return 正交摄像机实例。
     */
    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * 获取摄像机的目标位置。
     *
     * @return 目标位置的坐标。
     */
    public Vector2 getTargetPosition() {
        return targetPosition;
    }
}
