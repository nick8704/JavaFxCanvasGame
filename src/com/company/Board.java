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
        shapes.add(new Circle(gc));
    }

    public void draw() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeIndex) {
                shapes.get(i).drawShadedShape();
            } else {
                shapes.get(i).drawContourShape();
            }
        }
    }

    public void moveUpOnBoard() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeIndex) {
                shapes.get(i).moveUp();
            }
        }
    }

    public void moveDownOnBoard() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeIndex) {
                shapes.get(i).moveDown();
            }
        }
    }

    public void moveLeftOnBoard() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeIndex) {
                shapes.get(i).moveLeft();
            }
        }
    }

    public void moveRightOnBoard() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeIndex) {
                shapes.get(i).moveRight();
            }
        }
    }

    public void addTriangle() {
        shapes.add(new Triangle(gc));
        activeIndex = shapes.size() - 1;
    }

    public void addCircle() {
        shapes.add(new Circle(gc));
        activeIndex = shapes.size() - 1;
    }

    public void addSquare() {
        shapes.add(new Square(gc));
        activeIndex = shapes.size() - 1;
    }

    public void changeActiveShapeIndexUp() {
        if(activeIndex == shapes.size() - 1) {
            activeIndex = 0;
        } else {
            activeIndex++;
        }
    }

    public void changeActiveShapeIndexDown() {
        if(activeIndex == 0) {
            activeIndex = shapes.size() - 1;
        } else {
            activeIndex--;
        }
    }

    public void increaseSizeOnBoard() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeIndex) {
                shapes.get(i).increaseSize();
            }
        }
    }

    public void decreaseSizeOnBoard() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeIndex) {
                shapes.get(i).decreaseSize();
            }
        }
    }

    public void remove() {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (i == activeIndex) {
                shapes.remove(i);
                changeActiveShapeIndexDown();
                break;
            }
        }
    }

    public void cloneShape() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeIndex) {
                if (shapes.get(i) instanceof Square) {
                    shapes.add(new Square(gc));
                } else if (shapes.get(i) instanceof Triangle) {
                    shapes.add(new Triangle(gc));
                } else if (shapes.get(i) instanceof Circle) {
                    shapes.add(new Circle(gc));
                }
                shapes.get(shapes.size() - 1).setHeight(shapes.get(i).getHeight());
                shapes.get(shapes.size() - 1).setWidth(shapes.get(i).getWidth());
                shapes.get(shapes.size() - 1).setX(shapes.get(i).getX() + 5);
                shapes.get(shapes.size() - 1).setY(shapes.get(i).getY() + 5);
                activeIndex = shapes.size() - 1;
                break;
            }
        }
    }

    public void interflow(int sceneX, int sceneY) {
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).consistPoint(sceneX, sceneY)) {
                Shape result = new Group(shapes.get(i), shapes.get(activeIndex));
                Shape forRemove1 = shapes.get(i);
                Shape forRemove2 = shapes.get(activeIndex);
                shapes.remove(forRemove1);
                shapes.remove(forRemove2);
                shapes.add(result);
                activeIndex = shapes.size() - 1;
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