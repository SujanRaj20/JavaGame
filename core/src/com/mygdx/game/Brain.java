package com.mygdx.game;

import com.badlogic.gdx.Gdx; //The Gdx class provides access to the graphics, audio, input, and files APIs
import com.badlogic.gdx.Input; //importing the Input class from the com.badlogic.gdx package. The Input class provides access to input-related methods and constants, such as key presses, mouse movement, and touch screen events.

public class Brain {
    protected void handleInput(Man man) {
        if (Gdx.input.isTouched()) {
            man.x = Gdx.input.getX();
            man.y = Gdx.input.getY();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            man.y += 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            man.y -= 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            man.x -= 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            man.x += 5;
        } //This method updates the position of a Circle object based on input events.
    }


    protected void handleInput(Alien alien) {
        if (Gdx.input.isTouched()) {
            alien.x = Gdx.input.getX();
            alien.y = Gdx.input.getY();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            alien.y += 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            alien.y -= 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            alien.x -= 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            alien.x += 10;
        }//This method updates the position of a Rectangle object based on input events.
    }


    protected void handleInput(Boy boy) {
        if (Gdx.input.isTouched()) {
            boy.x = Gdx.input.getX();
            boy.y = Gdx.input.getY();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            boy.y += 15;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            boy.y -= 15;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            boy.x -= 15;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            boy.x += 15;
        }//This method updates the position of a Square object based on input events.
    }

    protected void handleInput(Ball ball) {
        if (Gdx.input.isTouched()) {
            ball.x = Gdx.input.getX();
            ball.y = Gdx.input.getY();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            ball.y += 15;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            ball.y -= 15;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            ball.x -= 15;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            ball.x += 15;
        }
    }
}