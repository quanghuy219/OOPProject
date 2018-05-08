package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConfirmBoxController {

    @FXML private Label textLabel;
    @FXML private JFXButton okButton;
    @FXML private JFXButton cancelButton;

    public void setText(String _text)
    {
        textLabel.setText(_text);
    }
}
