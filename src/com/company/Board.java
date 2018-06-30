package com.company;

import com.company.save.Save;
import com.company.save.SaveConfiguration;
import javafx.scene.canvas.GraphicsContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {

    public final GraphicsContext gc;
    private int activeIndex;

    public List<Shape> shapes = new ArrayList<>();

    public Board(GraphicsContext gc) {
        this.gc = gc;
        shapes.add(new Circle(gc, true));
    }

    public void draw() {
        for (Shape shape : shapes) {
            if (shape.isActive()) {
                shape.drawShadedShape();
            } else {
                shape.drawContourShape();
            }
        }
    }

    public void moveUpOnBoard() {
        for (Shape shape : shapes) {
            if (shape.isActive()) {
                shape.moveUp();
            }
        }
    }

    public void moveDownOnBoard() {
        for (Shape shape : shapes) {
            if (shape.isActive()) {
                shape.moveDown();
            }
        }
    }

    public void moveLeftOnBoard() {
        for (Shape shape : shapes) {
            if (shape.isActive()) {
                shape.moveLeft();
            }
        }
    }

    public void moveRightOnBoard() {
        for (Shape shape : shapes) {
            if (shape.isActive()) {
                shape.moveRight();

            }
        }
    }

    public void addTriangle() {
        for (Shape shape : shapes) {
            shape.setActive(false);
        }
        shapes.add(new Triangle(gc, true));
    }

    public void addCircle() {
        for (Shape shape : shapes) {
            shape.setActive(false);
        }
        shapes.add(new Circle(gc, true));
    }

    public void addSquare() {
        for (Shape shape : shapes) {
            shape.setActive(false);
        }
        shapes.add(new Square(gc, true));
    }

    public void changeActiveShapeIndexUp() {
        if (shapes.get(shapes.size() - 1).isActive()) {
            for (Shape shape : shapes) {
                shape.setActive(false);
            }
            shapes.get(0).setActive(true);
        } else {
            for (int i = 0; i < shapes.size(); i++) {
                if (shapes.get(i).isActive()) {
                    for (Shape shape : shapes) {
                        shape.setActive(false);
                    }
                    shapes.get(i + 1).setActive(true);
                    break;
                }
            }
        }
    }

    public void changeActiveShapeIndexDown() {
        if (shapes.get(0).isActive()) {
            for (Shape shape : shapes) {
                shape.setActive(false);
            }
            shapes.get(shapes.size() - 1).setActive(true);
        } else {
            for (int i = 0; i < shapes.size(); i++) {
                if (shapes.get(i).isActive()) {
                    for (Shape shape : shapes) {
                        shape.setActive(false);
                    }
                    shapes.get(i - 1).setActive(true);
                    break;
                }
            }
        }
    }

    public void increaseSizeOnBoard() {
        for (Shape shape : shapes) {
            if (shape.isActive()) {
                shape.increaseSize();
            }
        }
    }

    public void decreaseSizeOnBoard() {
        for (Shape shape : shapes) {
            if (shape.isActive()) {
                shape.decreaseSize();
            }
        }
    }

    public void remove() {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).isActive()) {
                shapes.remove(i);
            }
        }
        if (shapes.size() >= 1) {
            shapes.get(shapes.size() - 1).setActive(true);
        }
    }

    public void cloneShape() {
        List<Shape> listForClone = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.isActive()) {
                listForClone.add(shape);
            }
        }

        for (Shape shape : shapes) {
            shape.setActive(false);
        }

        for (int i = 0; i < listForClone.size(); i++) {
            if (listForClone.get(i) instanceof Square) {
                shapes.add(new Square(gc, true));
            } else if (listForClone.get(i) instanceof Triangle) {
                shapes.add(new Triangle(gc,  true));
            } else if (listForClone.get(i) instanceof Circle) {
                shapes.add(new Circle(gc,  true));
            }
            shapes.get(shapes.size() - 1).setHeight(listForClone.get(i).getHeight());
            shapes.get(shapes.size() - 1).setWidth(listForClone.get(i).getWidth());
            shapes.get(shapes.size() - 1).setX(listForClone.get(i).getX() + 5);
            shapes.get(shapes.size() - 1).setY(listForClone.get(i).getY() + 5);
        }
    }

    public void interflow(int sceneX, int sceneY) {
        for (int i = 0; i < shapes.size(); i++) {
            if ((sceneX >= shapes.get(i).getX() && sceneX <= shapes.get(i).getX() + shapes.get(i).getWidth()) &&
                    (sceneY >= shapes.get(i).getY() && sceneY <= shapes.get(i).getY() + shapes.get(i).getHeight())) {
                shapes.get(i).setActive(true);
                draw();
            }
        }
    }

    public Save makeSave() {
        List<SaveConfiguration> saveList = new ArrayList<>();
        for (Shape shape: shapes) {
            SaveConfiguration save = SaveConfiguration.createSaveConfiguration(shape);
            saveList.add(save);
        }
        return new Save(saveList, activeIndex);
    }

    public void loadSave(Save save) {
        shapes.clear();
        activeIndex = save.getActiveIndex();
        List<SaveConfiguration> saveList = save.getList();
        for (SaveConfiguration configuration: saveList) {
            Shape shape = SaveConfiguration.createShape(configuration, this);
            shapes.add(shape);
        }
        draw();
    }

}