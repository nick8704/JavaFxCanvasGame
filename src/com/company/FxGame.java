package com.company;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FxGame extends Application {

    private static final String FILE_NAME = "save.txt";
    private Board board;
    private GraphicsContext gc;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Canvas canvas = new Canvas(Config.WIDTH, Config.HEIGHT);

        BorderPane group = new BorderPane();
        group.setCenter(canvas);

        Scene scene = new Scene(group);
        registerOnKeyPressListener(scene);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX super crazy game by @anikonets v.1.0");
        primaryStage.show();

        gc = canvas.getGraphicsContext2D();
        board = new Board(gc);
        board.draw();
    }

    private void clean() {
        gc.clearRect(0, 0, Config.WIDTH, Config.HEIGHT);
    }

    public void registerOnKeyPressListener(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                clean();
                switch (event.getCode()) {
                    case UP:
                        board.moveUpOnBoard();
                        break;
                    case RIGHT:
                        board.moveRightOnBoard();
                        break;
                    case DOWN:
                        board.moveDownOnBoard();
                        break;
                    case LEFT:
                        board.moveLeftOnBoard();
                        break;
                    case DIGIT1:
                        Board.shapes.add(new Circle(gc, Board.shapes));
                        Board.activeShapeIndex = Board.shapes.size() - 1;
                        break;
                    case DIGIT2:
                        Board.shapes.add(new Triangle(gc, Board.shapes));
                        Board.activeShapeIndex = Board.shapes.size() - 1;
                        break;
                    case DIGIT3:
                        Board.shapes.add(new Square(gc, Board.shapes));
                        Board.activeShapeIndex = Board.shapes.size() - 1;
                        break;
                    case PAGE_DOWN:
                        board.changeActiveShapeIndexDown();
                        break;
                    case PAGE_UP:
                        board.changeActiveShapeIndexUp();
                        break;
                    case Q:
                        board.decreaseSizeOnBoard();
                        break;
                    case W:
                        board.increaseSizeOnBoard();
                        break;
                    case C:
                        board.cloneShape();
                        break;
                    case DELETE:
                        board.remove();
                        break;
                    case S:
                        writeToFile();
                        break;
                    case L:
                        //loadFromFile();
                        break;
                }
                board.draw();
            }
        });
    }

    public void writeToFile() {
        Gson gson = new Gson();
        String boardToJson = gson.toJson(board);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));) {
            writer.write(boardToJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}