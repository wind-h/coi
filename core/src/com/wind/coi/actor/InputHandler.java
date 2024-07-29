package com.wind.coi.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {

    private Player player;

    public InputHandler(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP:
                player.getVelocity().y = 1;
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                player.getVelocity().y = -1;
                break;
            case Input.Keys.A:
            case Input.Keys.LEFT:
                player.getVelocity().x = -1;
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                player.getVelocity().x = 1;
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
                player.getVelocity().y = 0;
                break;
            case Input.Keys.A:
            case Input.Keys.LEFT:
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                player.getVelocity().x = 0;
                break;
        }
        return true;
    }
}