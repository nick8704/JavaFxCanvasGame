package com.company;

import com.company.save.Save;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;

public class FxGame extends Application {

    private Board board;
    private GraphicsContext gc;
    private final String FILE_NAME = "save.txt";

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
        registerOnMousePressListener(scene);

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
                        board.addCircle();
                        break;
                    case DIGIT2:
                        board.addTriangle();
                        break;
                    case DIGIT3:
                        board.addSquare();
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
                        loadFromFile();
                        break;
                }
                board.draw();
            }
        });
    }

    public void registerOnMousePressListener(Scene scene) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isControlDown()) {
                    board.interflow((int) event.getSceneX(), (int) event.getSceneY());
                }
            }
        });
    }

    public void writeToFile() {
        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(board.makeSave());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));) {
            writer.write(jsonString);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void loadFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));) {
            StringBuilder result = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                result.append(line);
                line = reader.readLine();
            }
            Gson gson = new Gson();
            Save load = gson.fromJson(result.toString(), Save.class);
            board.loadSave(load);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}