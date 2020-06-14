package com.notes.helperbox;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;

public class NewForm {
    public TextField subHeading;
    public Button add;
    public Button cancel;

    static Stage stage;

    public static String SubHeading;
    public VBox NewFormVBox;

    public static String display() {
        Parent root = null;
        try {
            root = FXMLLoader.load(NewForm.class.getResource("NewForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(500);
        stage.setMinHeight(175);
        stage.setMaxWidth(500);
        stage.setMaxHeight(200);
        stage.setTitle("Add Sub Heading");
        stage.setScene(new Scene(root, 500, 175));
        stage.showAndWait();
        return SubHeading;
    }

    public void getSubHeading(ActionEvent actionEvent) {
        Button src = (Button) actionEvent.getSource();
        if(subHeading.getText().length()>0 || src==cancel) {
            if(src==add) {
                SubHeading = subHeading.getText();
            } else {
                SubHeading = "";
            }
            stage.close();
        } else if(src==add) {
            Parent errLabel = null;
            try {
                errLabel = FXMLLoader.load(NewForm.class.getResource("ErrorLabel.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Label errorLabel = new Label("Error: Sub Heading cannot be empty");
            NewFormVBox.getChildren().add(errLabel);
        }

    }
}
