// imports

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

// Class Attributes (variables)
public class Rectangle extends Shapes{
    private float length;
    private float width;


    //constructor used to initialize objects

    public Rectangle(float length, float width,float x, float y) {
        super(x,y);
        this.length = length;
        this.width = width;
    }

    // Method Rectangle

    public void draw(ShapeRenderer shape) {
        shape.rect(x, y, length, width);
    }

    // Method keyboard movement

    public void handleInput() {
        if (Gdx.input.isTouched()) {
            x = Gdx.input.getX();
            y = Gdx.input.getY();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += 15;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= 15;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= 15;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += 15;
        }
    }
}

