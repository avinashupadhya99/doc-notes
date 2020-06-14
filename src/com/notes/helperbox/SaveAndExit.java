package com.notes.helperbox;

import com.notes.util.Response;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SaveAndExit {
    public Button donotsave;
    public Button save;
    public Button cancel;

    public static Stage stage;
    public static Response response;

    public static Response display() {
        Parent root = null;
        try {
            root = FXMLLoader.load(SaveAndExit.class.getResource("SaveAndExit.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(550);
        stage.setMinHeight(165);
        stage.setMaxWidth(550);
        stage.setMaxHeight(165);
        stage.setTitle("Exit Program");
        stage.setScene(new Scene(root, 550, 165));
        stage.showAndWait();
        return response;
    }

    public void sendResponse(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        if(btn==donotsave) {
            response = Response.NO;
        } else if(btn==save) {
            response = Response.YES;
        } else {
            response = Response.CANCEL;
        }
        stage.close();
    }
}
