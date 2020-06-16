package com.notes.main;

import com.notes.helperbox.EditForm;
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

import javax.lang.model.util.Types;
import javax.swing.text.LabelView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
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
    public static HashSet<String> subHeadingList = new HashSet<>();;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void addNotes(ActionEvent e) {
        Button btn = (Button) e.getSource();
        VBox sidebar = (VBox) btn.getParent();
        HBox subHeadingHBox = (HBox) sidebar.getParent();
        VBox subHeadingVBox = (VBox) subHeadingHBox.getChildren().get(0);
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
        System.out.println(subHeadingList.size());
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
        System.out.println(this.subHeadingList.size());
        title = EditForm.display(title);
        if(title!=null && title.length()>0 && !subHeadingList.contains(title) ) {
            subHeadingTitle.setText(title);
            subHeadingList.add(title);
        }
    }

    public void deleteSubHeading(ActionEvent actionEvent) {
    }
}
