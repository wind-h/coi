package com.wind.coi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * @author hsc
 * @date 2024/7/26 13:39
 */
public class NewGameScreen extends InputAdapter implements Screen {

    private NewMainGame game;

    private SpriteBatch batch;

    private Texture texture;

    private Vector2 vector2;

    private OrthographicCamera camera;

    private Vector2 moveDirection = new Vector2(0, 0); // 记录按下的方向


    private float moveSpeed = 30f; // 移动速度

    public NewGameScreen(NewMainGame game) {
        this.game = game;
        batch = new SpriteBatch();
        texture = this.game.getAssetManager().get("sprite/no_anim_0.png");
        camera = new OrthographicCamera(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getWidth());
        camera.update();
        vector2 = new Vector2(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // 清屏
        ScreenUtils.clear(255, 255, 255, 1);
        // 设置相机位置
        camera.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);
        camera.update();
        // 更新玩家位置
        vector2.x += moveDirection.x * moveSpeed * delta;
        vector2.y += moveDirection.y * moveSpeed * delta;
        // 开始
        batch.setProjectionMatrix(camera.combined);
        // 开始
        batch.begin();
        // 绘制
        batch.draw(texture, vector2.x - (float) texture.getWidth() / 2, vector2.y - (float) texture.getHeight() / 2);
        // 结束
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
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
        texture.dispose();
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {
//        // 当鼠标移动时被调用
//        Gdx.app.log("MouseMoved", "Mouse moved to: (" + screenX + ", " + screenY + ")");
//        // 当鼠标移动时被调用
//        float adjustedY = Gdx.graphics.getHeight() - screenY; // 调整Y坐标
//        vector2.set(screenX, adjustedY); // 更新角色位置
        return true; // 返回true表示事件已经被处理
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP:
                moveDirection.y = 1;
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                moveDirection.y = -1;
                break;
            case Input.Keys.A:
            case Input.Keys.LEFT:
                moveDirection.x = -1;
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                moveDirection.x = 1;
                break;
            case Input.Keys.ESCAPE:
                Gdx.app.exit(); // 退出应用
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP:
            case Input.Keys.S:
            case Input.Keys.DOWN:
                moveDirection.y = 0;
                break;
            case Input.Keys.A:
            case Input.Keys.LEFT:
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                moveDirection.x = 0;
                break;
        }
        return true; // 表示已经处理了按键事件
    }
}
