package com.wind.coi.service.impl;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {
    private Player player;

    public InputHandler(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
            player.move(-1, 0);
        } else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            player.move(1, 0);
        } else if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
            player.move(0, 1);
        } else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            player.move(0, -1);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
            player.move(1, 0);
        } else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            player.move(-1, 0);
        } else if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
            player.move(0, -1);
        } else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            player.move(0, 1);
        }
        return true;
    }
}