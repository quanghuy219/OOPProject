package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Label nameLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(App.getUser().getName());
    }
}
