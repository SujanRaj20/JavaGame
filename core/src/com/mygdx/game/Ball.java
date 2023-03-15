package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/*
 * This class stores the info that a ball needs and handles setting Limits/Bounds
 */
public class Ball {

    protected float radius;
    int direction = 0;

    // Used for determining limits for movement
    private float shapeToLimit_Ratio = 30f;
    protected float playerMinX, playerMaxX, playerMinY, playerMaxY;
    Vector3 minScreenCoord = new Vector3();
    Vector3 maxScreenCoord = new Vector3();

    private ShapeRenderer renderer;

    private Color color;

    public Ball(float radius, Color color) {
        this.color = color;
        this.radius = radius;

        renderer = new ShapeRenderer();
        renderer.setColor(color);
        renderer.rotate(1, 0f, 0, 180);
        PopulateMovingLimits();
    }

    protected ShapeRenderer Renderer() {
        return renderer;
    }

    protected Color getColor() {
        return color;
    }

    protected void PopulateMovingLimits() {
        playerMinX = (this.radius / 0.1f) * shapeToLimit_Ratio;
        playerMaxX = 640 - playerMinX;

        playerMinY = (this.radius / 0.1f) * shapeToLimit_Ratio;
        playerMaxY = 480 - playerMinY;

        Vector3 minMovingCoord = new Vector3(playerMinX, playerMinY, 1.0f);
        Vector3 maxMovingCoord = new Vector3(playerMaxX, playerMaxY, 1.0f);
        SetScreenCoordLimitForObj(minMovingCoord, maxMovingCoord);
    }

    protected void SetScreenCoordLimitForObj(Vector3 minMovingCoord, Vector3 maxMovingCoord) {
        minScreenCoord.set(minMovingCoord);
        maxScreenCoord.set(maxMovingCoord);

    }

    protected Vector3 getMinScreenCoordLimitForObj() {
        return minScreenCoord;
    }

    protected Vector3 getMaxScreenCoordLimitForObj() {
        return maxScreenCoord;
    }
}