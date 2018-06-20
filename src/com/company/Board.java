package com.company;

import javafx.scene.canvas.GraphicsContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {

    private final String FILE_NAME = "save.txt";
    private final GraphicsContext gc;
    public List<Shape> shapes = new ArrayList<>();
    public int activeShapeIndex = 0;

    public Board(GraphicsContext gc) {
        this.gc = gc;
        shapes.add(new Circle(gc, shapes));
    }

    public void draw() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeShapeIndex) {
                shapes.get(i).drawShadedShape();
            } else {
                shapes.get(i).drawContourShape();
            }
        }
    }

    public void moveUpOnBoard() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeShapeIndex) {
                shapes.get(i).moveUp();
            }
        }
    }

    public void addSquare() {
        shapes.add(new Square(gc, shapes));
        activeShapeIndex = shapes.size() - 1;
    }

    public void addCircle() {
        shapes.add(new Circle(gc, shapes));
        activeShapeIndex = shapes.size() - 1;
    }

    public void addTriangle() {
        shapes.add(new Triangle(gc, shapes));
        activeShapeIndex = shapes.size() - 1;
    }

    public void moveDownOnBoard() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeShapeIndex) {
                shapes.get(i).moveDown();
            }
        }
    }

    public void moveLeftOnBoard() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeShapeIndex) {
                shapes.get(i).moveLeft();
            }
        }
    }

    public void moveRightOnBoard() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeShapeIndex) {
                shapes.get(i).moveRight();
            }
        }
    }

    public void changeActiveShapeIndexUp() {
        if (activeShapeIndex == shapes.size() - 1) {
            activeShapeIndex = 0;
        } else {
            activeShapeIndex++;
        }
    }

    public void changeActiveShapeIndexDown() {
        if (activeShapeIndex == 0) {
            activeShapeIndex = shapes.size() - 1;
        } else {
            activeShapeIndex--;
        }
    }

    public void increaseSizeOnBoard() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeShapeIndex) {
                shapes.get(i).increaseSize();
            }
        }
    }

    public void decreaseSizeOnBoard() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeShapeIndex) {
                shapes.get(i).decreaseSize();
            }
        }
    }

    public void remove() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeShapeIndex) {
                shapes.remove(i);
            }
        }
        activeShapeIndex = shapes.size() - 1;
    }

    public void cloneShape() {
        for (int i = 0; i < shapes.size(); i++) {
            if (i == activeShapeIndex) {
                if (shapes.get(i) instanceof Square) {
                    shapes.add(new Square(gc, shapes));
                } else if (shapes.get(i) instanceof Triangle) {
                    shapes.add(new Triangle(gc, shapes));
                } else if (shapes.get(i) instanceof Circle) {
                    shapes.add(new Circle(gc, shapes));
                }
                shapes.get(shapes.size() - 1).setHEIGHT(shapes.get(i).getHEIGHT());
                shapes.get(shapes.size() - 1).setWIDTH(shapes.get(i).getWIDTH());
                break;
            }
        }
        activeShapeIndex = shapes.size() - 1;
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
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));) {
            while ((line = reader.readLine()) != null) {
                String[] shapeConfig = line.split(",");
                if(shapeConfig[0].equals("SQUARE")) {
                    shapes.add(new Square(gc, shapes));
                } else if(shapeConfig[0].equals("TRIANGLE")) {
                    shapes.add(new Triangle(gc, shapes));
                } else if(shapeConfig[0].equals("CIRCLE")) {
                    shapes.add(new Circle(gc, shapes));
                }
                shapes.get(shapes.size() - 1).setX(Double.parseDouble(shapeConfig[1]));
                shapes.get(shapes.size() - 1).setY(Double.parseDouble(shapeConfig[2]));
                shapes.get(shapes.size() - 1).setHEIGHT(Integer.parseInt(shapeConfig[3]));
                shapes.get(shapes.size() - 1).setWIDTH(Integer.parseInt(shapeConfig[4]));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        this.shapes = shapes;
        activeShapeIndex = shapes.size() - 1;
    }
}