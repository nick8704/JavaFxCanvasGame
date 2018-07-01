package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractShape implements Shape {

    protected final GraphicsContext gc;

    protected int width = 40;
    protected int height = 40;
    private final int MAX_SIZE = 200;
    private final int MIN_SIZE = 20;
    protected final int LINE_WIDHT = 5;
    protected final int ONE_STEP = 5;
    protected final int ONE_STEP_CHANGE_SIZE = 9;

    protected double x;
    protected double y;
    protected ShapeType shapeType;

    protected Color color;

    public AbstractShape(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void drawShadedShape() {
        gc.setFill(color);
    }

    @Override
    public void drawContourShape() {
        gc.setLineWidth(LINE_WIDHT);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void moveUp() {
        y -= ONE_STEP;
    }

    public void moveDown() {
        y += ONE_STEP;
    }

    public void moveLeft() {
        x -= ONE_STEP;
    }

    public void moveRight() {
        x += ONE_STEP;
    }

    public void increaseSize() {
        if (height <= MAX_SIZE) {
            height += ONE_STEP_CHANGE_SIZE;
            width += ONE_STEP_CHANGE_SIZE;
        }
    }

    public void decreaseSize() {
        if (height >= MIN_SIZE) {
            height -= ONE_STEP_CHANGE_SIZE;
            width -= ONE_STEP_CHANGE_SIZE;
        }
    }

    @Override
    public Shape cloneGroup(Group shape) {
        return null;
    }

    @Override
    public boolean consistPoint(int sceneX, int sceneY) {
        return between(sceneX, (int) x, (int) x + width) && between(sceneY, (int) y, (int) y + height);
    }

    private static boolean between(int test, int a, int b) {
        return test >= a && test <= b;
    }
}