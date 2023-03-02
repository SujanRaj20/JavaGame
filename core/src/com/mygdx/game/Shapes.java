package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Shapes {
    protected float x, y;
    protected float width, height;
    protected float radius;

    public Shapes(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Shapes(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void generateImg(SpriteBatch batch, Texture img) {
        batch.draw(img, x, y, width, height);
    }
}