package com.notes.main;

import com.notes.helperbox.NewForm;
import com.notes.helperbox.SaveAndExit;
import com.notes.util.Response;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    /*
     * Have an array list of Sub headings. Pass it to the New form and ask for position. (Feature)
     */

    public Button addNewNotes;
    public Label Heading;
    public ScrollPane mainScrollPane;
    public VBox centerVBox;
    public MenuItem Close;
    private boolean unsaved = true;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    public void addNotes(ActionEvent e) {
        Button btn = (Button) e.getSource();
        HBox subHeadingHBox = (HBox) btn.getParent();
        VBox subHeadingVBox = (VBox) subHeadingHBox.getParent();
        TextField newNotes = new TextField();
        subHeadingVBox.getChildren().add(newNotes);
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
                VBox subheadingVBox = (VBox) ((AnchorPane) node).getChildren().get(0);
                HBox subheadingHBox = (HBox) subheadingVBox.getChildren().get(0);
                Label subheadingLabel = (Label) subheadingHBox.getChildren().get(0);
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
        if(display!=null && display.length()>0) {
            Parent root = null;
            try {
                root = FXMLLoader.load(Controller.class.getResource("../util/SubHeading.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mainVBox.getChildren().add(root);
            VBox vBox = (VBox) root.getChildrenUnmodifiable().get(0);
            HBox hBox = (HBox) vBox.getChildren().get(0);
            Label subHeadingTitle = (Label) hBox.getChildren().get(0);
            subHeadingTitle.setText(display);
        }

    }


}
