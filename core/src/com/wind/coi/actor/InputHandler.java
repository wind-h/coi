package com.wind.coi.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class InputHandler extends InputAdapter {

    private Player player;

    public InputHandler(Player player) {
        this.player = player;
    }

    public void updateVelocity() {
        float xVelocity = 0;
        float yVelocity = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yVelocity += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yVelocity -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xVelocity -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xVelocity += 1;
        }

        player.setVelocity(new Vector2(xVelocity, yVelocity));
    }
}