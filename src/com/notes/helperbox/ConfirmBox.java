package com.notes.helperbox;

import com.notes.util.Response;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmBox {

    public static Response response;
    public static Stage stage;

    public Button yes;
    public Button no;

    public static Response display(String title, String message) {
        Parent root = null;
        try {
            root = FXMLLoader.load(NewForm.class.getResource("ConfirmBox.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        VBox vBox = (VBox) root.getChildrenUnmodifiable().get(0);
        Label messageLabel = (Label)vBox.getChildren().get(0);
        messageLabel.setText(message);

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(500);
        stage.setMinHeight(155);
        stage.setMaxWidth(500);
        stage.setMaxHeight(155);
        stage.setTitle(title);
        stage.setScene(new Scene(root, 500, 155));
        stage.showAndWait();
        return response;
    }

    public void sendResponse(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        if(btn==yes) {
            response = Response.YES;
        } else {
            response = Response.NO;
        }
        stage.close();
    }
}
