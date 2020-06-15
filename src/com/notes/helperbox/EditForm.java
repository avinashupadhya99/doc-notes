package com.notes.helperbox;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EditForm {
    public TextField subHeading;
    public Button save;
    public Button cancel;
    public VBox EditFormVBox;

    static Stage stage;

    public static String SubHeading;
    public static String OldTitle;

    public static String display(String oldTitle) {
        OldTitle = oldTitle;
        Parent root = null;
        try {
            root = FXMLLoader.load(NewForm.class.getResource("EditForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        VBox mainVBox = (VBox) root.getChildrenUnmodifiable().get(0);
        TextField titleField = (TextField) mainVBox.getChildren().get(1);
        titleField.setText(oldTitle);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(500);
        stage.setMinHeight(175);
        stage.setMaxWidth(500);
        stage.setMaxHeight(200);
        stage.setTitle("Edit Sub Heading");
        stage.setScene(new Scene(root, 500, 175));
        stage.showAndWait();
        return SubHeading;
    }

    public void getSubHeading(ActionEvent actionEvent) {
        Button src = (Button) actionEvent.getSource();
        if(subHeading.getText().length()>0 || src==cancel) {
            if(src==save) {
                SubHeading = subHeading.getText();
            } else {
                SubHeading = OldTitle;
            }
            stage.close();
        } else if(src==save) {
            Parent errLabel = null;
            try {
                errLabel = FXMLLoader.load(NewForm.class.getResource("ErrorLabel.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Label errorLabel = new Label("Error: Sub Heading cannot be empty");
            EditFormVBox.getChildren().add(errLabel);
        }

    }
}
