package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball extends Shapes {

    public Ball(float x, float y, float radius) {
        super(x, y, radius);
    }

    @Override
    public void generateImg(SpriteBatch batch, Texture img) {
        batch.draw(img, x, y, radius * 2, radius * 2);
    }

}
