package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;

/*
 * This class shall handle the selecting / deselect of AI
 */
public class AI {

    protected final int LEFT = Input.Keys.LEFT;
    protected final int RIGHT = Input.Keys.RIGHT;
    protected final int UP = Input.Keys.UP;
    protected final int DOWN = Input.Keys.DOWN;
    private Shapes<AI> myShape;

    public AI(float moveSpeed, Shapes<AI> myShape) {
        this.myShape = myShape;
    }

    public void StartMoving(int keyPressed, float moveSpeed) {

        if (myShape.getSelected())
            if (myShape.getDirection() != 0) {

                Vector3 curPos = myShape.getPosition();
                float targetPosX = curPos.x + (myShape.getDirection() * moveSpeed);
                float targetPosY = curPos.y + (myShape.getDirection() * moveSpeed);

                if (keyPressed == LEFT || keyPressed == RIGHT) {
                    if (targetPosX > myShape.getMinScreenCoord().x && targetPosX < myShape.getMaxScreenCoord().x) {
                        myShape.setPosition(new Vector3(targetPosX, curPos.y, 0));
                    }
                } else if (keyPressed == UP || keyPressed == DOWN) {
                    if (targetPosY > myShape.getMinScreenCoord().y && targetPosY < myShape.getMaxScreenCoord().y) {
                        myShape.setPosition(new Vector3(curPos.x, targetPosY, 0));
                    }
                }
            }
    }
}
