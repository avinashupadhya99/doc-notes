package com.notes.main;

import com.notes.helperbox.ConfirmBox;
import com.notes.helperbox.EditForm;
import com.notes.helperbox.NewForm;
import com.notes.helperbox.SaveAndExit;
import com.notes.util.Response;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.lang.model.util.Types;
import javax.swing.text.LabelView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class Controller implements Initializable {


    /*
     * Have an array list of Sub headings. Pass it to the New form and ask for position. (Feature)
     */

    public Button addNewNote;
    public Label Heading;
    public ScrollPane mainScrollPane;
    public VBox centerVBox;
    public MenuItem Close;
    public MenuItem Save;
    public BorderPane borderPane;
    private boolean unsaved = true;
    public static HashSet<String> subHeadingList = new HashSet<>();;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }



    public void closeApp(ActionEvent actionEvent) {
        Response response = Response.CANCEL;
        if(unsaved) {
            response = SaveAndExit.display();
        }
        
        if(!unsaved || response==Response.NO){
            Platform.exit();
        }
            
    }

    public void saveApp(ActionEvent actionEvent) {
        ObservableList<Node> children = centerVBox.getChildren();
        Label headingLabel = (Label) centerVBox.getChildren().get(0);
        String heading = headingLabel.getText();
        List<String> subHeadingList = new ArrayList<>();
        for(Node node: children) {
            if(node instanceof AnchorPane) {
                HBox subheadingHBox = (HBox) ((AnchorPane) node).getChildren().get(0);
                VBox subheadingVBox = (VBox) subheadingHBox.getChildren().get(0);
                Label subheadingLabel = (Label) subheadingVBox.getChildren().get(0);
                subHeadingList.add(subheadingLabel.getText());
            }
        }
        for(String s: subHeadingList) {
            System.out.println(s);
        }
    }

    public void addSubHeading(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        HBox btnHBox = (HBox) btn.getParent();
        VBox mainVBox = (VBox) btnHBox.getParent();
        String display = NewForm.display();
        if(display!=null && display.length()>0 && !subHeadingList.contains(display)) {
            Parent root = null;
            try {
                root = FXMLLoader.load(Controller.class.getResource("../util/SubHeading.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mainVBox.getChildren().add(root);
            HBox hBox = (HBox) root.getChildrenUnmodifiable().get(0);
            VBox vBox = (VBox) hBox.getChildren().get(0);
            Label subHeadingTitle = (Label) vBox.getChildren().get(0);
            subHeadingTitle.setText(display);
            subHeadingList.add(display);
        }

    }


    public void editSubHeading(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        VBox sidebar  = (VBox) btn.getParent();
        HBox hBox = (HBox) sidebar.getParent();
        VBox vBox = (VBox) hBox.getChildren().get(0);

        Label subHeadingTitle = (Label) vBox.getChildren().get(0);
        String title = subHeadingTitle.getText();
        title = EditForm.display(title);
        if(title!=null && title.length()>0 && !subHeadingList.contains(title) ) {
            subHeadingTitle.setText(title);
            subHeadingList.add(title);
        }
    }

    public void deleteSubHeading(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        VBox sidebar  = (VBox) btn.getParent();
        HBox hBox = (HBox) sidebar.getParent();
        VBox vBox = (VBox) hBox.getChildren().get(0);

        Label subHeadingTitle = (Label) vBox.getChildren().get(0);
        String title = subHeadingTitle.getText();
        Response response = ConfirmBox.display("Delete SubHeading", "Do you really want to delete the sub heading \""+title+"\"?");

        if(response==Response.YES) {
            AnchorPane anchorPane = (AnchorPane) hBox.getParent();
            VBox mainVBox = (VBox) anchorPane.getParent();
            mainVBox.getChildren().remove(anchorPane);
            subHeadingList.remove(title);
            //TODO: Remove notes from the persisting object
        }
    }

    @FXML
    public void addNote(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Controller.class.getResource("../util/Note.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Button btn = (Button) actionEvent.getSource();
        VBox sidebar = (VBox) btn.getParent();
        HBox subHeadingHBox = (HBox) sidebar.getParent();
        VBox subHeadingVBox = (VBox) subHeadingHBox.getChildren().get(0);
        subHeadingVBox.getChildren().add(root);
        TextField note = (TextField) root.getChildrenUnmodifiable().get(0);
        note.requestFocus();
        ContextMenu cm = new ContextMenu();
        cm.getItems().add(new MenuItem("Delete"));
        note.setContextMenu(cm);
    }

    public void saveNote(ActionEvent actionEvent) {
        TextField noteTextField = (TextField) actionEvent.getSource();
        noteTextField.setEditable(false);
        noteTextField.getStyleClass().add("note");
        //TODO: Add this note for persisting

    }

    public void editNote(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            TextField noteTextField = (TextField) mouseEvent.getSource();
            noteTextField.setEditable(true);
            noteTextField.getStyleClass().remove("note");
        }
    }
}
