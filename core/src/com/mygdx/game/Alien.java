package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Alien extends Shapes {

    public Alien(float x, float y, float width, float height) {
        super(x, y, width, height);
    }


    @Override
    public void generateImg(SpriteBatch batch, Texture img) {
        batch.draw(img, x, y, 120, 120);
    }

}