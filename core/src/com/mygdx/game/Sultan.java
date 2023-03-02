package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sultan extends Shapes {

    public Sultan (float x, float y, float width, float height) {
        super(x, y, width, height);
    }


    @Override
    public void generateImg(SpriteBatch batch, Texture img) {
        batch.draw(img, x, y, width, height);
    }
}
