package com.company.save;

import com.company.AbstractShape;
import com.company.Board;
import com.company.Circle;
import com.company.Shape;
import com.company.ShapeType;
import com.company.Square;
import com.company.Triangle;
import java.util.List;

public class SaveConfiguration {

    private ShapeType shapeType;
    private int width;
    private int height;
    private double x;
    private double y;

    private List<SaveConfiguration> list;

    public SaveConfiguration() {
    }

    public ShapeType getSaveType() {
        return shapeType;
    }

    public List<SaveConfiguration> getList() {
        return list;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static SaveConfiguration createSaveConfiguration(Shape shape) {
        SaveConfiguration result = new SaveConfiguration();
        if (shape instanceof AbstractShape) {
            AbstractShape abstractShape = (AbstractShape) shape;
            result.x = abstractShape.getX();
            result.y = abstractShape.getY();
            result.width = abstractShape.getWidth();
            result.height = abstractShape.getHeight();
        }
        if (shape instanceof Square) {
            result.shapeType = ShapeType.SQUARE;
        }
        if (shape instanceof Triangle) {
            result.shapeType = ShapeType.TRIANGLE;
        }
        if (shape instanceof Circle) {
            result.shapeType = ShapeType.CIRCLE;
        }
        return result;
    }

    public static Shape createShape(SaveConfiguration configuration, Board board) {
        Shape shape = new Circle(board.gc, false);
        if(configuration.getSaveType() == ShapeType.TRIANGLE) {
            shape = new Triangle(board.gc, false);
        }
        if(configuration.getSaveType() == ShapeType.SQUARE) {
            shape = new Square(board.gc, false);
        }
        shape.setX(configuration.getX());
        shape.setY(configuration.getY());
        shape.setHeight(configuration.getHeight());
        shape.setWidth(configuration.getWidth());
        return shape;
    }

}