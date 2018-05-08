package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainBody implements Initializable {
    @FXML
    private AnchorPane xxx;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void testAdd()
    {
        xxx.getChildren().add(new Button("asdasd"));
    }
}
