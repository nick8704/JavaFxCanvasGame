package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends AbstractShape {

    public Triangle(GraphicsContext gc, boolean active) {
        super(gc,true);
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

}