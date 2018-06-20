package com.company;

public interface Shape {

    void drawShadedShape();

    void drawContourShape();

    void moveUp();

    void moveDown();

    void moveLeft();

    void moveRight();

    void increaseSize();

    void decreaseSize();

    void setWIDTH(int WIDTH);

    void setHEIGHT(int HEIGHT);

    int getWIDTH();

    int getHEIGHT();

    void setX(double x);

    void setY(double y);

    boolean isActive();

    void setActive(boolean active);

}