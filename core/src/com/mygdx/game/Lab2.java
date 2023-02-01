package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;

// declare shape, rectangle, square, circle instance variables
public class Lab2 extends ApplicationAdapter {
	private ShapeRenderer shape;
	private Rectangle rectangle;
	private Square square;
	private Circle circle;

	//instansiation of rectangle,square

	@Override
	public void create () {
			shape = new ShapeRenderer();
			rectangle = new Rectangle(35,10, 100, 100);
			square = new Square(20,20, 70, 70);
			circle = new Circle(35, 30, 30);
	}


	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		rectangle.draw(shape);
		square.draw(shape);
		circle.draw(shape);
		shape.end();

		rectangle.handleInput();
		square.handleInput();
		circle.handleInput();
	}

}






