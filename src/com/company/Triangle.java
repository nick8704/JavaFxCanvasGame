package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends AbstractShape {

    public Triangle(GraphicsContext gc) {
        super(gc);
        color = Color.MEDIUMBLUE;
        shapeType = ShapeType.TRIANGLE;
    }

    @Override
    public void drawShadedShape() {
        super.drawShadedShape();
        gc.fillPolygon(new double[]{x, x + width / 2,x + width}, new double[]{y + height, y, y + height}, 3);
    }

    @Override
    public void drawContourShape() {
        super.drawContourShape();
        gc.setStroke(Color.MEDIUMBLUE);
        gc.strokePolygon(new double[]{x, x + width / 2, x + width}, new double[]{y + height, y, y + height}, 3);
    }

    @Override
    public Shape cloneShape(double x, double y, int width, int height) {
        Shape shape = new Triangle(gc);
        shape.setX(x);
        shape.setY(y);
        shape.setWidth(width);
        shape.setHeight(height);
        return shape;
    }

}