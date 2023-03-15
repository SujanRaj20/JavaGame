package com.mygdx.game;

import java.util.List;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
public class NonCollidableEntity<T> extends Shapes<T> {

    public NonCollidableEntity(Vector3 spawnPoint, T newShape) {
        super(spawnPoint, newShape);
    }

    protected void Movement(List<Shapes<T>> otherAIs) {
        myAI.StartMoving(keyPressed, getMoveSpeed());

    }
}
