package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Shapes {
    protected float x, y;
    protected float width, height;

    public Shapes(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public void generateImg(SpriteBatch batch, Texture img) {
        batch.draw(img, x, y, width, height);
    }
}