package com.mygdx.game;

interface Movement {
    float moveSpeed = 3.0f;
    int direction = 0;

    void getMoveSpeed();
    void setMoveSpeed();
    void setDirection(int direction);
    void Move();
}