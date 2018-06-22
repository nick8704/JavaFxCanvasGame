package com.company;

import javafx.scene.canvas.GraphicsContext;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {

    private final String FILE_NAME = "save.txt";
    private final GraphicsContext gc;
    public List<Shape> shapes = new ArrayList<>();

    public Board(GraphicsContext gc) {
        this.gc = gc;
        shapes.add(new Circle(gc, shapes, true));
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
        shapes.add(new Triangle(gc, shapes, true));
    }

    public void addCircle() {
        for (Shape shape : shapes) {
            shape.setActive(false);
        }
        shapes.add(new Circle(gc, shapes, true));
    }

    public void addSquare() {
        for (Shape shape : shapes) {
            shape.setActive(false);
        }
        shapes.add(new Square(gc, shapes, true));
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
                shapes.add(new Square(gc, shapes, true));
            } else if (listForClone.get(i) instanceof Triangle) {
                shapes.add(new Triangle(gc, shapes, true));
            } else if (listForClone.get(i) instanceof Circle) {
                shapes.add(new Circle(gc, shapes, true));
            }
            shapes.get(shapes.size() - 1).setHEIGHT(listForClone.get(i).getHEIGHT());
            shapes.get(shapes.size() - 1).setWIDTH(listForClone.get(i).getWIDTH());
            shapes.get(shapes.size() - 1).setX(listForClone.get(i).getX() + 5);
            shapes.get(shapes.size() - 1).setY(listForClone.get(i).getY() + 5);
        }
    }

    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));) {
            for (Shape shape : shapes) {
                writer.write(shape.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        List<Shape> shapes = new ArrayList<>();
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));) {
            while ((line = reader.readLine()) != null) {
                String[] shapeConfig = line.split(",");
                if (shapeConfig[0].equals("SQUARE")) {
                    shapes.add(new Square(gc, shapes, false));
                } else if (shapeConfig[0].equals("TRIANGLE")) {
                    shapes.add(new Triangle(gc, shapes, false));
                } else if (shapeConfig[0].equals("CIRCLE")) {
                    shapes.add(new Circle(gc, shapes, false));
                }
                shapes.get(shapes.size() - 1).setX(Double.parseDouble(shapeConfig[1]));
                shapes.get(shapes.size() - 1).setY(Double.parseDouble(shapeConfig[2]));
                shapes.get(shapes.size() - 1).setHEIGHT(Integer.parseInt(shapeConfig[3]));
                shapes.get(shapes.size() - 1).setWIDTH(Integer.parseInt(shapeConfig[4]));
                shapes.get(shapes.size() - 1).setActive(Boolean.parseBoolean(shapeConfig[5]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.shapes = shapes;
    }

    public void interflow(int sceneX, int sceneY) {
        for (int i = 0; i < shapes.size(); i++) {
            if ((sceneX >= shapes.get(i).getX() && sceneX <= shapes.get(i).getX() + shapes.get(i).getWIDTH()) &&
                    (sceneY >= shapes.get(i).getY() && sceneY <= shapes.get(i).getY() + shapes.get(i).getHEIGHT())) {
                shapes.get(i).setActive(true);
                draw();
            }
        }
    }
}