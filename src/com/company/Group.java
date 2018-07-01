package com.company;

import java.util.ArrayList;
import java.util.List;

public class Group implements Shape {

    private List<Shape> groupList = new ArrayList<>();

    public Group() {
    }

    public Group(Shape firstShape, Shape secondShape) {
        addShapeToGroupList(firstShape);
        addShapeToGroupList(secondShape);
    }

    private void addShapeToGroupList(Shape shape) {
        if (shape instanceof Group) {
            Group group = (Group) shape;
            groupList.addAll(group.getList());
        } else {
            groupList.add(shape);
        }
    }

    @Override
    public void drawShadedShape() {
        for (Shape shape: groupList) {
            shape.drawShadedShape();
        }
    }

    @Override
    public void drawContourShape() {
        for (Shape shape: groupList) {
            shape.drawContourShape();
        }
    }

    @Override
    public void moveUp() {
        for (Shape shape: groupList) {
            shape.moveUp();
        }
    }

    @Override
    public void moveDown() {
        for (Shape shape: groupList) {
            shape.moveDown();
        }
    }

    @Override
    public void moveLeft() {
        for (Shape shape: groupList) {
            shape.moveLeft();
        }
    }

    @Override
    public void moveRight() {
        for (Shape shape: groupList) {
            shape.moveRight();
        }
    }

    @Override
    public void increaseSize() {
        for (Shape shape: groupList) {
            shape.increaseSize();
        }
    }

    @Override
    public void decreaseSize() {
        for (Shape shape: groupList) {
            shape.decreaseSize();
        }
    }

    @Override
    public void setWidth(int width) {
        for (Shape shape: groupList) {
            shape.setWidth(width);
        }
    }

    @Override
    public void setHeight(int height) {
        for (Shape shape: groupList) {
            shape.setHeight(height);
        }
    }

    @Override
    public int getWidth() {
        return groupList.get(0).getWidth();
    }

    @Override
    public int getHeight() {
        return groupList.get(0).getHeight();
    }

    @Override
    public void setX(double x) {
    }

    @Override
    public void setY(double y) {
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public boolean consistPoint(int sceneX, int sceneY) {
        for (Shape shape : groupList) {
            if (shape.consistPoint(sceneX, sceneY)) {
                return true;
            }
        }
        return false;
    }

    private void addShape(Shape shape) {
        if (shape instanceof Group) {
            Group group = (Group) shape;
            groupList.addAll(group.getList());
        } else {
            groupList.add(shape);
        }
    }

    public void setList(List<Shape> groupList) {
        this.groupList.clear();
        for (Shape shape : groupList) {
            addShape(shape);
        }
    }

    public List<Shape> getList() {
        return groupList;
    }

}