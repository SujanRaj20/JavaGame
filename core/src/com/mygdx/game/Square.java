package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/*
 * This class is a sub-class of Shapes and handles setting Limits/Bounds, render for itself
 */
public class Square {

    // public Square(Vector3 spawnPoint, Shapes<AI> newShape) {
    // super(newShape);
    // // TODO Auto-generated constructor stub

    // }

    protected float length, height;
    private float playerMinX, playerMaxX, playerMinY, playerMaxY;
    Vector3 minScreenCoord = new Vector3();
    Vector3 maxScreenCoord = new Vector3();
    private float shapeToLimit_Ratio = 15f;
    private ShapeRenderer renderer;

    private Color color;

    public Square(float length, float height, Color color) {
        this.length = length;
        this.height = length;
        this.color = color;

        renderer = new ShapeRenderer();
        renderer.setColor(color);

        PopulateMovingLimits();
    }

    // Make use of this method to call in other classes to render.
    /*
     * Make sure to
     * - setProjectionMatrix(camera.combined)
     * - begin(ShapeRenderer.ShapeType.Filled)
     * - renderer.circle? .rect? etc
     * - end
     */
    protected ShapeRenderer Renderer() {
        return renderer;
    }

    protected Color getColor() {
        return color;
    }

    protected void PopulateMovingLimits() {

        this.playerMinX = (this.length / 0.1f) * shapeToLimit_Ratio;
        this.playerMaxX = 640 - playerMinX;

        playerMinY = (this.length / 0.1f) * shapeToLimit_Ratio;
        playerMaxY = 480 - playerMinY;

        Vector3 minCoord = new Vector3(playerMinX, playerMinY, 1.0f);
        Vector3 maxCoord = new Vector3(playerMaxX, playerMaxY, 1.0f);
        SetScreenCoordLimitForObj(minCoord, maxCoord);
    }

    protected void SetScreenCoordLimitForObj(Vector3 minCoord, Vector3 maxCoord) {
        minScreenCoord.set(minCoord);
        maxScreenCoord.set(maxCoord);
    }

    protected Vector3 getMinScreenCoordLimitForObj() {
        return minScreenCoord;
    }

    protected Vector3 getMaxScreenCoordLimitForObj() {
        return maxScreenCoord;
    }

}
