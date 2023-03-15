package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class Rectangle {

    protected float length, height;
    private float playerMaxX, playerMinX, playerMaxY, playerMinY;
    Vector3 maxScreenCoord = new Vector3();
    Vector3 minScreenCoord = new Vector3();

    private ShapeRenderer renderer;

    private Color color;
    private float shapeToLimit_Ratio = 15f;

    public Rectangle(float length, float height, Color color) {
        this.length = length;
        this.height = length;
        renderer = new ShapeRenderer();
        renderer.setColor(color);
        PopulateMovingLimits();
    }
    protected ShapeRenderer Renderer() {
        return renderer;
    }

    protected Color getColor() {
        return color;
    }

    protected void PopulateMovingLimits() {

        playerMinX = (this.length / 0.1f) * shapeToLimit_Ratio;
        playerMaxX = 640 - playerMinX;

        playerMinY = (this.height / 0.1f) * shapeToLimit_Ratio;
        playerMaxY = 480 - playerMinY;

        Vector3 maxScreenCoord = new Vector3(playerMaxX, playerMaxY, 1.0f);
        Vector3 minScreenCoord = new Vector3(playerMinX, playerMinY, 1.0f);
        SetScreenCoordLimitForObj(maxScreenCoord, minScreenCoord);
    }

    protected void SetScreenCoordLimitForObj(Vector3 maxCoord, Vector3 minCoord) {
        maxScreenCoord.set(maxCoord);
        minScreenCoord.set(minCoord);
    }
    protected Vector3 getMaxScreenCoordLimitForObj() {
        return maxScreenCoord;
    }
    protected Vector3 getMinScreenCoordLimitForObj() {
        return minScreenCoord;
    }

}
