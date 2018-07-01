package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends AbstractShape {

    public Square(GraphicsContext gc) {
        super(gc);
        color = Color.PLUM;
        shapeType = ShapeType.SQUARE;
    }

    @Override
    public void drawShadedShape() {
        super.drawShadedShape();
        gc.fillRect(x, y, width, height);
    }

    @Override
    public void drawContourShape() {
        super.drawContourShape();
        gc.setStroke(Color.PLUM);
        gc.strokeRect(x, y, width, height);
    }

    @Override
    public Shape cloneShape(double x, double y, int width, int height) {
        Shape shape = new Square(gc);
        shape.setX(x);
        shape.setY(y);
        shape.setWidth(width);
        shape.setHeight(height);
        return shape;
    }
}