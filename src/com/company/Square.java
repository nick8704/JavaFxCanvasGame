package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public class Square extends AbstractShape {

    public Square(GraphicsContext gc, List<Shape> shapes, boolean active) {
        super(gc, shapes, true);
        color = Color.PLUM;
        shapeType = ShapeType.SQUARE;
    }

    @Override
    public void drawShadedShape() {
        super.drawShadedShape();
        gc.fillRect(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void drawContourShape() {
        super.drawContourShape();
        gc.setStroke(Color.PLUM);
        gc.strokeRect(x, y, WIDTH, HEIGHT);
    }

}
