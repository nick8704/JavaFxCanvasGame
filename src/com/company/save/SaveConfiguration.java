package com.company.save;

import com.company.AbstractShape;
import com.company.Board;
import com.company.Circle;
import com.company.Group;
import com.company.Shape;
import com.company.ShapeType;
import com.company.Square;
import com.company.Triangle;
import java.util.ArrayList;
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
        if (shape instanceof Group) {
            Group group = (Group) shape;
            result.list = new ArrayList<SaveConfiguration>();
            for (Shape tmpShape : group.getList()) {
                result.list.add(createSaveConfiguration(tmpShape));
            }
            result.shapeType = ShapeType.GROUP;
        }
        return result;
    }

    public static Shape createShape(SaveConfiguration configuration, Board board) {
        if (configuration.getSaveType() == ShapeType.GROUP) {
            Group group = new Group();
            List<Shape> groupList = new ArrayList<Shape>();
            for (SaveConfiguration tmpShapeSave : configuration.getList()) {
                groupList.add(createShape(tmpShapeSave, board));
            }
            group.setList(groupList);
            return group;
        } else {
            Shape shape = new Circle(board.gc);
            if (configuration.getSaveType() == ShapeType.TRIANGLE) {
                shape = new Triangle(board.gc);
            }
            if (configuration.getSaveType() == ShapeType.SQUARE) {
                shape = new Square(board.gc);
            }
            shape.setX(configuration.getX());
            shape.setY(configuration.getY());
            shape.setHeight(configuration.getHeight());
            shape.setWidth(configuration.getWidth());
            return shape;
        }
    }

}