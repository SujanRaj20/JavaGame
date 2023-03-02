package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lab2 extends ApplicationAdapter {
	private Sultan sultan1;
	private Boy boy1;
	private Alien alien1;
	private Man man1;
	private SpriteBatch batch;
	private Brain brain;
	private Texture sultanImg, boyImg, alienImg, manImg;

	@Override
	public void create() {
		batch = new SpriteBatch();
		sultanImg = new Texture("sultan.png");
		boyImg = new Texture("boy.png");
		manImg = new Texture("man.png");
		alienImg = new Texture("alien.png");
		sultan1 = new Sultan (400, 200, 120, 120);
		boy1 = new Boy(200, 200, 40, 50);
		man1 = new Man(300, 300, 50, 60);
		alien1 = new Alien(400, 400, 55, 55);
		brain = new Brain();
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		brain.handleInput(sultan1);
		brain.handleInput(boy1);
		brain.handleInput(man1);
		brain.handleInput(alien1);

		batch.begin();
		sultan1.generateImg(batch, sultanImg);
		boy1.generateImg(batch, boyImg);
		man1.generateImg(batch, manImg);
		alien1.generateImg(batch, alienImg);
		batch.end();
	}

	@Override
	public void dispose() {
		sultanImg.dispose();
		boyImg.dispose();
		manImg.dispose();
		alienImg.dispose();
		batch.dispose();
	}
}