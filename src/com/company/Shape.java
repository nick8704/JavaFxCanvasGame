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

    void setWidth(int width);

    void setHeight(int height);

    int getWidth();

    int getHeight();

    void setX(double x);

    void setY(double y);

    double getX();

    double getY();

    boolean isActive();

    void setActive(boolean active);

    boolean consistPoint(int sceneX, int sceneY);

}