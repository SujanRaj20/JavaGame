package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lab2 extends ApplicationAdapter {
	private Ball ball1;
	private Boy boy1;
	private Alien alien1;
	private Man man1;
	private SpriteBatch batch;
	private Brain brain;
	private Texture ballImg, boyImg, alienImg, manImg;

	@Override
	public void create() {
		batch = new SpriteBatch();
		ballImg = new Texture("ball.png");
		boyImg = new Texture("boy.png");
		manImg = new Texture("man.png");
		alienImg = new Texture("alien.png");
		ball1 = new Ball(100, 100, 50);
		boy1 = new Boy(200, 200, 80, 100);
		man1 = new Man(300, 300, 100, 120);
		alien1 = new Alien(400, 400, 120, 80);
		brain = new Brain();
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		brain.handleInput(ball1);
		brain.handleInput(boy1);
		brain.handleInput(man1);
		brain.handleInput(alien1);

		batch.begin();
		ball1.generateImg(batch, ballImg);
		boy1.generateImg(batch, boyImg);
		man1.generateImg(batch, manImg);
		alien1.generateImg(batch, alienImg);
		batch.end();
	}

	@Override
	public void dispose() {
		ballImg.dispose();
		boyImg.dispose();
		manImg.dispose();
		alienImg.dispose();
		batch.dispose();
	}
}