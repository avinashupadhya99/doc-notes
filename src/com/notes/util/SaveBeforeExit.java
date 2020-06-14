package com.notes.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SaveBeforeExit {

    public Button dontsave;
    public Button save;
    public Button cancel;

    public static Stage stage;
    public static Response response;
    public ImageView warningImage;

    public static Response display() {
        Parent root = null;
        try {
            root = FXMLLoader.load(NewForm.class.getResource("SaveBeforeExit.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(500);
        stage.setMinHeight(165);
        stage.setMaxWidth(500);
        stage.setMaxHeight(165);
        stage.setTitle("Exit Program");
        stage.setScene(new Scene(root, 500, 165));
        stage.showAndWait();
        return response;
    }


    public void sendResponse(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        if(btn==dontsave) {
            response = Response.NO;
        } else if(btn==save) {
            response = Response.YES;
        } else {
            response = Response.CANCEL;
        }
    }
}
