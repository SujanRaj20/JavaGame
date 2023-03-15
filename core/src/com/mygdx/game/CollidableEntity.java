package com.mygdx.game;

import java.util.List;
import com.badlogic.gdx.math.Vector3;

public class CollidableEntity<T> extends Shapes<T> implements iCollidable<T> {
    public CollidableEntity(Vector3 spawnPoint, T newShape) {
        super(spawnPoint, newShape);
    }

    protected void Movement(List<Shapes<T>> otherAIs) {
        handleCollision((List<Shapes<T>>) otherAIs);
        myAI.StartMoving(keyPressed, getMoveSpeed());
    }

    @Override
    public boolean collidesWith(List<iCollidable<T>> other) {
        return false;
    }

    @Override
    public void handleCollision(List<Shapes<T>> otherShapes) {
        for (Shapes<T> shapes : otherShapes) {
            Shapes<T> shape = (Shapes<T>) shapes;
            if (shape instanceof iCollidable) {

                if (shape != this && this.getSelected()) {

                    Vector3 curPos = getPosition();

                    // Minimum boundary for this object
                    float minTargetPosX = curPos.x - myOffsetFromOrigin;
                    float minTargetPosY = 480 - Math.abs(curPos.y - 480) - 24.0f;
                    Vector3 minTargetPos = new Vector3(minTargetPosX, minTargetPosY, 1.0f);

                    // Maximum boundary for this object
                    float maxTargetPosX = curPos.x + myOffsetFromOrigin;
                    float maxTargetPosY = 480 - Math.abs(curPos.y - 480) + 24.0f;
                    Vector3 maxTargetPos = new Vector3(maxTargetPosX, maxTargetPosY, 1.0f);

                    Vector3 rightMidPoint = new Vector3(maxTargetPosX, (maxTargetPosY + minTargetPosY) / 2, 1.0f);
                    Vector3 leftMidPoint = new Vector3(minTargetPosX, (maxTargetPosY + minTargetPosY) / 2, 1.0f);
                    Vector3 midTopPoint = new Vector3((minTargetPosX + maxTargetPosX) / 2, maxTargetPosY, 1.0f);
                    Vector3 midBotPoint = new Vector3((minTargetPosX + maxTargetPosX) / 2, minTargetPosY, 1.0f);
                    Vector3 rightBotPoint = new Vector3(maxTargetPosX, minTargetPosY, 1.0f);
                    Vector3 leftTopPoint = new Vector3(minTargetPosX, maxTargetPosY, 1.0f);
                    Vector3 aiMinCoord = GameToScreenUnit(shape.getMinCoord());
                    Vector3 aiMaxCoord = GameToScreenUnit(shape.getMaxCoord());

                    // These checks for 6 points of collision
                    if (inRange(maxTargetPos, aiMinCoord, aiMaxCoord)
                            || inRange(minTargetPos, aiMinCoord, aiMaxCoord)
                            || inRange(rightMidPoint, aiMinCoord, aiMaxCoord)
                            || inRange(leftMidPoint, aiMinCoord, aiMaxCoord)
                            || inRange(midTopPoint, aiMinCoord, aiMaxCoord)
                            || inRange(midBotPoint, aiMinCoord, aiMaxCoord)
                            || inRange(rightBotPoint, aiMinCoord, aiMaxCoord)
                            || inRange(leftTopPoint, aiMinCoord, aiMaxCoord)) {

                        // If collided = send back
                        setPosition(new Vector3(50f, 450f, 0));
                    }
                }
            }
        }
    }
    public void reactToCollision() {
    }
}
