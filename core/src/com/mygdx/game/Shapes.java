package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.List;

/*
 * This class controls the Movement, general codes for setting/getting position
 */
public abstract class Shapes<T> implements InputProcessor, Cloneable {
    // ShapeTypes available
    private enum ShapeType {
        BALL, RECTANGLE, SQUARE
    }

    protected ShapeType curShape; // Current shape as above ^

    // Input keys
    protected final int LEFT = Input.Keys.LEFT;
    protected final int RIGHT = Input.Keys.RIGHT;
    protected final int UP = Input.Keys.UP;
    protected final int DOWN = Input.Keys.DOWN;
    protected int keyPressed;

    // // Position of this object
    // private Vector3 origin; // Get clickedPos from main script for instantiating
    // rect
    protected Vector3 spawnPoint = new Vector3(0, 0, 0);
    private Vector3 curPosScreenUnit = new Vector3(0, 0, 0); // This is screen unit (640x480)
    private Vector3 curPosGameUnit = new Vector3(0, 0, 0); // This is game unit (-1?? to 1.??) camera.unproject is used
                                                           // to convert screen to game units

    protected float length, height, radius;
    protected Color color;

    // Movement variables for this object
    protected int direction;
    protected float moveSpeed;

    private boolean selected; // Sets to true if user clicked on this AI

    protected Vector3 minCoord = new Vector3();
    protected Vector3 maxCoord = new Vector3(); // This is the min and max coordinate in game units
    protected float myOffsetFromOrigin;
    protected Vector3 minScreenCoord = new Vector3();
    protected Vector3 maxScreenCoord = new Vector3();

    // Renderer to draw Shape
    protected ShapeRenderer renderer;
    private OrthographicCamera camera;

    protected AI myAI;

    T obj;

    public Shapes(Vector3 spawnPoint, T newShape) {
        obj = newShape;

        if (obj instanceof Ball) {
            this.curShape = ShapeType.BALL;
            System.out.println((Ball) obj);

            // ((Ball)obj).radius = 20;
            minScreenCoord = ((Ball) obj).getMinScreenCoordLimitForObj();
            maxScreenCoord = ((Ball) obj).getMaxScreenCoordLimitForObj();

        } else if (obj instanceof Square) {
            this.curShape = ShapeType.SQUARE;
            minScreenCoord = ((Square) obj).getMinScreenCoordLimitForObj();
            maxScreenCoord = ((Square) obj).getMaxScreenCoordLimitForObj();
        } else {
            this.curShape = ShapeType.RECTANGLE;
            minScreenCoord = ((Rectangle) obj).getMinScreenCoordLimitForObj();
            maxScreenCoord = ((Rectangle) obj).getMaxScreenCoordLimitForObj();
        }

        setPosition(spawnPoint);
        setMoveSpeed(2.0f);

        // This is aggregation?
        myAI = new AI(getMoveSpeed(), (Shapes<AI>) this);
    }

    // public Shapes(Vector3 spawnPoint, Ball newShape) {
    // // Used in AI StartMoving()
    // this.curShape = ShapeType.BALL;
    // myBall = newShape;
    // minScreenCoord = newShape.getMinScreenCoordLimitForObj();
    // maxScreenCoord = newShape.getMaxScreenCoordLimitForObj();

    // setPosition(spawnPoint);
    // setMoveSpeed(2.0f);
    // myAI = new AI(getMoveSpeed(), (Shapes<AI>) this);
    // }

    // public Shapes(Vector3 spawnPoint, Square newShape) {
    // this.curShape = ShapeType.SQUARE;
    // mySquare = newShape;
    // minScreenCoord = newShape.getMinScreenCoordLimitForObj();
    // maxScreenCoord = newShape.getMaxScreenCoordLimitForObj();

    // myOffsetFromOrigin = 32f;
    // setPosition(spawnPoint);
    // setMoveSpeed(2.0f);
    // myAI = new AI(getMoveSpeed(), (Shapes<AI>) this);
    // }

    // public Shapes(Vector3 spawnPoint, Rectangle newShape) {
    // this.curShape = ShapeType.RECTANGLE;
    // myRect = newShape;
    // minScreenCoord = newShape.getMinScreenCoordLimitForObj();
    // maxScreenCoord = newShape.getMaxScreenCoordLimitForObj();

    // myOffsetFromOrigin = 64f;

    // setPosition(spawnPoint);
    // setMoveSpeed(2.0f);
    // myAI = new AI(getMoveSpeed(), (Shapes<AI>) this);
    // }

    // Used to set position for shape, for moving mainly
    protected void setPosition(Vector3 curPosition) {
        this.curPosScreenUnit.set(new Vector3(curPosition.x, curPosition.y, 1.0f));
    }

    // Set screenPos to gamePos, need to rename
    protected void setScreenPosition(OrthographicCamera camera) {
        camera.unproject(this.curPosGameUnit.set(getPosition()));
    }

    // Get vector 3 position in screen unit
    protected Vector3 getPosition() {
        return this.curPosScreenUnit;
    }

    // Get vector 3 position in game unit , cannot refaactor to rename...
    protected Vector3 getGamePosition() {
        return this.curPosGameUnit;
    }

    // Returns current object if is selected by clicking
    public Shapes<AI> isSelected(Vector3 clickedPos) {

        // Get updated minmax coord for selecting
        SetMinMaxCoordForSelect();

        // Check if clicked position is in range with min and max coord
        if (inRange(clickedPos, getMinCoord(), getMaxCoord())) {
            setSelected(true);
            return (Shapes) this;
        }
        setSelected(false);
        return null;
    }

    protected void setSelected(boolean var) {
        selected = var;
    }

    protected boolean getSelected() {
        return selected;
    }

    // Update the coordinates available for user to select
    // Will be called after every render to continuously update
    protected void SetMinMaxCoordForSelect() {
        Vector2 offset = new Vector2();

        switch (this.curShape) {
            case BALL:
                offset.set(((Ball) obj).radius, ((Ball) obj).radius);
                break;
            case SQUARE:
                offset.set(((Square) obj).length / 2, ((Square) obj).length / 2);
                break;
            case RECTANGLE:
                offset.set(((Rectangle) obj).length / 2, ((Rectangle) obj).height / 2);
                break;
        }

        minCoord = new Vector3(getGamePosition().x - offset.x, (getGamePosition().y * -1)
                - offset.y,
                1);
        maxCoord = new Vector3(getGamePosition().x + offset.x, (getGamePosition().y * -1)
                + offset.y,
                1);
    }

    // Returns Vector3 on bottom-left corner coord of this object in Game Units
    public Vector3 getMinCoord() {
        return minCoord;
    }

    // Returns Vector3 on top-right corner coord of this object in Game Units
    public Vector3 getMaxCoord() {
        return maxCoord;
    }

    // Returns Vector3 on bottom-left corner coord of this in Screen Units
    public Vector3 getMinScreenCoord() {
        return minScreenCoord;
    }

    // Returns Vector3 on top-right corner coord of this in Screen Units
    public Vector3 getMaxScreenCoord() {
        return maxScreenCoord;
    }

    // Returns bool depending if clickedPos is within range of min and max position
    protected boolean inRange(Vector3 clickedPos, Vector3 myMinPos, Vector3 myMaxPos) {
        if (clickedPos.x >= myMinPos.x && clickedPos.x <= myMaxPos.x && clickedPos.y >= myMinPos.y
                && clickedPos.y <= myMaxPos.y) {
            return true;
        }
        return false;
    }

    protected void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    // Override in Shapes.java
    protected void renderShape(OrthographicCamera camera) {
        // Sets game position from screen position
        // System.out.println("This is Ball");

        setScreenPosition(camera);

        // renderer.setProjectionMatrix(camera.combined);
        // renderer.begin(ShapeRenderer.ShapeType.Filled);
        Vector3 offset;
        switch (this.curShape) {
            case BALL:
                // myBall.Renderer().setProjectionMatrix(camera.combined);
                // myBall.Renderer().begin(ShapeRenderer.ShapeType.Filled);
                // myBall.Renderer().circle(getGamePosition().x, getGamePosition().y,
                // myBall.radius, 40);
                // myBall.Renderer().end();
                ((Ball) obj).Renderer().setProjectionMatrix(camera.combined);
                ((Ball) obj).Renderer().begin(ShapeRenderer.ShapeType.Filled);
                ((Ball) obj).Renderer().circle(getGamePosition().x, getGamePosition().y, ((Ball) obj).radius, 40);
                ((Ball) obj).Renderer().end();
                break;
            case SQUARE:
                offset = new Vector3(getGamePosition().x - ((Square) obj).length / 2,
                        (getGamePosition().y * -1) - ((Square) obj).length / 2, 0);
                ((Square) obj).Renderer().setProjectionMatrix(camera.combined);
                ((Square) obj).Renderer().begin(ShapeRenderer.ShapeType.Filled);
                ((Square) obj).Renderer().rect(offset.x, offset.y, ((Square) obj).length, ((Square) obj).length);
                ((Square) obj).Renderer().end();
                break;
            case RECTANGLE:
                offset = new Vector3(getGamePosition().x - ((Rectangle) obj).length / 2,
                        (getGamePosition().y * -1) - ((Rectangle) obj).length / 2, 0);
                ((Rectangle) obj).Renderer().setProjectionMatrix(camera.combined);
                ((Rectangle) obj).Renderer().begin(ShapeRenderer.ShapeType.Filled);
                ((Rectangle) obj).Renderer().rect(offset.x, offset.y, ((Rectangle) obj).length,
                        ((Rectangle) obj).height);
                ((Rectangle) obj).Renderer().end();
                break;
        }

        SetMinMaxCoordForSelect();
    }

    protected float getMoveSpeed() {
        return this.moveSpeed;
    }

    protected void setMoveSpeed(float newMoveSpeed) {
        this.moveSpeed = newMoveSpeed;
    }

    // Sets direction using keyDown method
    protected void setDirection(int direction) {
        if (getSelected()) {
            if (direction == LEFT || direction == DOWN)
                this.direction = -1;
            else if (direction == RIGHT || direction == UP)
                this.direction = 1;
            else
                this.direction = 0; // 0 is neutral, not moving

            keyPressed = direction;
        }
    }

    protected int getDirection() {
        return this.direction;
    }

    // Handles Movement for entity, will be overriden in CollidableEntity
    protected void Movement(List<Shapes<T>> otherAIs) {

    }

    protected Vector3 ScreenToGameUnit(Vector3 screenVector3) {
        return camera.unproject(screenVector3);
    }

    protected Vector3 GameToScreenUnit(Vector3 gameVector3) {
        if (camera != null && gameVector3 != null)
            return camera.project(gameVector3);
        return Vector3.Zero;
    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub

        if (getSelected()) {
            // Set direction if LEFT RIGHT UP DOWN is pressed
            if (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT || keycode == Input.Keys.UP
                    || keycode == Input.Keys.DOWN) {

                setDirection(keycode);
            }

            // If any of the keys below are pressed, adjust movement speed
            switch (keycode) {
                case Input.Keys.W:
                    if (getMoveSpeed() < 10.0f)
                        setMoveSpeed(getMoveSpeed() * 1.5f);
                    break;
                case Input.Keys.S:
                    if (getMoveSpeed() > 0.5f)
                        setMoveSpeed(getMoveSpeed() * 0.75f);
                    break;
                default:
                    break;
            }
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
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
