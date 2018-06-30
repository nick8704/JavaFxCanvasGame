package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends AbstractShape {

    public Circle(GraphicsContext gc, boolean active) {
        super(gc, true);
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

}