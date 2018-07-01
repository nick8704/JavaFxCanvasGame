package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends AbstractShape {

    public Circle(GraphicsContext gc) {
        super(gc);
        color = Color.RED;
        shapeType = ShapeType.CIRCLE;
    }

    @Override
    public void drawShadedShape() {
        super.drawShadedShape();
        gc.fillOval(x, y, width, height);
    }

    @Override
    public void drawContourShape() {
        super.drawContourShape();
        gc.setStroke(Color.RED);
        gc.strokeOval(x, y, width, height);
    }

    @Override
    public Shape cloneShape(double x, double y, int width, int height) {
        Shape shape = new Circle(gc);
        shape.setX(x);
        shape.setY(y);
        shape.setWidth(width);
        shape.setHeight(height);
        return shape;
    }

}