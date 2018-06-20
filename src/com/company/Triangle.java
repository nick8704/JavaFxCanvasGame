package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public class Triangle extends AbstractShape {

    public Triangle(GraphicsContext gc, List<Shape> shapes, boolean active) {
        super(gc, shapes, true);
        color = Color.MEDIUMBLUE;
        shapeType = ShapeType.TRIANGLE;
    }

    @Override
    public void drawShadedShape() {
        super.drawShadedShape();
        gc.fillPolygon(new double[]{x, x + WIDTH / 2,x + WIDTH}, new double[]{y + HEIGHT, y, y + HEIGHT}, 3);
    }

    @Override
    public void drawContourShape() {
        super.drawContourShape();
        gc.setStroke(Color.MEDIUMBLUE);
        gc.strokePolygon(new double[]{x, x + WIDTH / 2, x + WIDTH}, new double[]{y + HEIGHT, y, y + HEIGHT}, 3);
    }

}