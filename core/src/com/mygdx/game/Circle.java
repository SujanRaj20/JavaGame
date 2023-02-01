// imports

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

// Class Attributes (variables)

public class Circle extends Shapes{
    private float Radius;


    //constructor used to initialize objects

    public Circle(float Radius, float x, float y) {
        super(x,y);
        this.Radius = Radius;
    }

    // Method circle

    public void draw(ShapeRenderer shape)  {
        shape.circle(x, y, Radius);
    }

    // Method keyboard movement
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            x = Gdx.input.getX();
            y =  Gdx.input.getY();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += 5;
        }
    }
}