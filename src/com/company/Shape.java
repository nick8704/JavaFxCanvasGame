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

    boolean consistPoint(int sceneX, int sceneY);

    Shape cloneShape(double x, double y, int width, int height);

    Shape cloneGroup(Group shape);
}