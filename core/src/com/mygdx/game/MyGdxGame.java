package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
	private BitmapFont font;

	public final static float SCALE = 32f;
	public final static float INV_SCALE = 1.f / SCALE;
	public final static float VP_WIDTH = 1280 * INV_SCALE;
	public final static float VP_HEIGHT = 720 * INV_SCALE;

	private OrthographicCamera camera;
	private ExtendViewport viewport;

	// To select on shapes
	private Shapes<AI> selectedShapes;

	// stores all shapes
	private List<Shapes> allShapes = new ArrayList<Shapes>();

	// Shapes created for demo
	private NonCollidableEntity<Ball> ball1;
	private CollidableEntity<Square> square1;
	private CollidableEntity<Square> square2;
	private CollidableEntity<Rectangle> rect1;

	private Vector3 clickedPos = new Vector3();
	private boolean instantiateRect = false;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		font = new BitmapFont();
		font.setColor(Color.RED);

		camera = new OrthographicCamera();
		viewport = new ExtendViewport(VP_WIDTH, VP_HEIGHT, camera);

		Gdx.input.setInputProcessor(this);

		// Create my Shapes
		ball1 = new NonCollidableEntity<Ball>(new Vector3(500f, 400, 1f), new Ball(0.1f, Color.SALMON));
		square1 = new CollidableEntity<Square>(new Vector3(500, 100, 1f), new Square(0.2f, 0.1f, Color.BLUE));
		square2 = new CollidableEntity<Square>(new Vector3(500, 250, 1f), new Square(0.2f, 0.1f, Color.GREEN));
		rect1 = new CollidableEntity<Rectangle>(new Vector3(200, 250, 1.0f), new Rectangle(0.5f, 0.5f, Color.RED));

		// shapes create added into array
		allShapes.add(ball1);
		allShapes.add(square1);
		allShapes.add(square2);
		allShapes.add(rect1);

		for (Shapes<AI> myShape : allShapes) {
			myShape.setCamera(camera);
		}
	}

	@Override
	public void render() {
		ScreenUtils.clear(1f, 15f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (instantiateRect) {
			InstantiateOrDestroyOnClick();
			instantiateRect = false;
		}

		// Render and allow movement for all shapes
		for (Shapes myShape : allShapes) {
			// System.out.println("haloss: " + myShape);
			myShape.renderShape(camera);
			myShape.Movement(allShapes);
		}
	}


	Vector3 curLoc = new Vector3(350, 200, 0);
	Vector3 tp = new Vector3();
	boolean dragging;

	private void InstantiateOrDestroyOnClick() {

		if (instantiateRect) {

			// Check for user clicking
			for (Shapes<AI> myShape : allShapes) {

				if (myShape.inRange(clickedPos, myShape.getMinCoord(), myShape.getMaxCoord())) {
					allShapes.remove(myShape);
					return;
				}
			}
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {

		for (Shapes<AI> myShape : allShapes) {
			((InputProcessor) myShape).keyDown(keycode);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub

		// Set clickedPos to screen units
		clickedPos.set(screenX, screenY, 0);
		// Set clickedPos to game units
		camera.unproject(clickedPos);
		System.out.println("Clicked: " + clickedPos);

		if (button == Input.Buttons.LEFT) {

			// Reset selectedShapes selected to false
			if (selectedShapes != null)
				selectedShapes.setSelected(false);

			for (Shapes<AI> myShape : allShapes) {

				selectedShapes = ((Shapes<AI>) myShape.isSelected(clickedPos));
				System.out.println(selectedShapes);
				// If is assigned, exit loop
				if (selectedShapes != null)
					if (selectedShapes.getSelected()) {
						return true;
					}
			}

		} else if (button == Input.Buttons.RIGHT) {
			// to create rectangle
			instantiateRect = true;
			return true;
		}

		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if (button != Input.Buttons.LEFT || pointer > 0)
			return false;
		camera.unproject(tp.set(screenX, screenY, 0));
		dragging = false;
		return true;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		if (!dragging)
			return false;
		camera.unproject(tp.set(screenX, screenY, 0));
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}
