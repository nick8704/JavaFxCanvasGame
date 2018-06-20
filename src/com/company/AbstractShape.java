package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public abstract class AbstractShape implements Shape {

    protected final GraphicsContext gc;

    protected int WIDTH = 40;
    protected int HEIGHT = 40;
    private final int MAX_SIZE = 200;
    private final int MIN_SIZE = 20;
    protected final int LINE_WIDHT = 5;
    protected final int ONE_STEP = 5;
    protected final int ONE_STEP_CHANGE_SIZE = 9;

    protected double x;
    protected double y;
    protected ShapeType shapeType;

    protected Color color;

    public AbstractShape(GraphicsContext gc, List<Shape> shapes) {
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

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public void moveUp() {
        if(y - ONE_STEP >= 0) {
            y -= ONE_STEP;
        }
    }

    public void moveDown() {
        if(y + HEIGHT <= Config.HEIGHT) {
            y += ONE_STEP;
        }
    }

    public void moveLeft() {
        if(x >= 0) {
            x -= ONE_STEP;
        }
    }

    public void moveRight() {
        if(x + WIDTH <= Config.WIDTH) {
            x += ONE_STEP;
        }
    }

    public void increaseSize() {
        if(HEIGHT <= MAX_SIZE) {
            HEIGHT += ONE_STEP_CHANGE_SIZE;
            WIDTH += ONE_STEP_CHANGE_SIZE;
        }
    }

    public void decreaseSize() {
        if(HEIGHT >= MIN_SIZE) {
            HEIGHT -= ONE_STEP_CHANGE_SIZE;
            WIDTH -= ONE_STEP_CHANGE_SIZE;
        }
    }
}