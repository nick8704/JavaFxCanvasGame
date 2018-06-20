package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public class Circle extends AbstractShape {

    public Circle(GraphicsContext gc, List<Shape> shapes) {
        super(gc, shapes);
        color = Color.RED;
        shapeType = ShapeType.CIRCLE;
    }

    @Override
    public void drawShadedShape() {
        super.drawShadedShape();
        gc.fillOval(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void drawContourShape() {
        super.drawContourShape();
        gc.setStroke(Color.RED);
        gc.strokeOval(x, y, WIDTH, HEIGHT);
    }

}