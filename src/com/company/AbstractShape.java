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
    protected boolean active;

    protected double x;
    protected double y;
    protected ShapeType shapeType;

    protected Color color;

    public AbstractShape(GraphicsContext gc, boolean active) {
        this.gc = gc;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void moveUp() {
        if (y - ONE_STEP >= 0) {
            y -= ONE_STEP;
        }
    }

    public void moveDown() {
        if (y + height <= Config.HEIGHT) {
            y += ONE_STEP;
        }
    }

    public void moveLeft() {
        if(x >= 0) {
            x -= ONE_STEP;
        }
    }

    public void moveRight() {
        if (x + width <= Config.WIDTH) {
            x += ONE_STEP;
        }
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

}