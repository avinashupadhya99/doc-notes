package com.notes.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        init((BorderPane) root);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(500);
        primaryStage.setTitle("Doc Notes");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }

    public void init(BorderPane borderPane) {
        //TODO: Read the config file and determine what has to be set in the center of the border pane
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("mainNote.fxml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        borderPane.setCenter(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
