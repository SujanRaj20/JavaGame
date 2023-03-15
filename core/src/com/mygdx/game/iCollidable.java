package com.mygdx.game;

import java.util.List;

public interface iCollidable<T> {

    public boolean collidesWith(List<iCollidable<T>> other);

    void handleCollision(List<Shapes<T>> otherAIs);

    void reactToCollision();
}
