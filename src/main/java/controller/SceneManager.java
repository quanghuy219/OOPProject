package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private MainStageController mainStageController;

    private Pane mainBodyPane;

    public SceneManager()
    {

    }

    public void setPaneContent(String menuTitle)
    {
        mainStageController.setPaneContent(menuTitle);
    }

    public void setMainStageController(MainStageController mainStageController) {
        this.mainStageController = mainStageController;
    }

    public MainStageController getMainStageController() {
        return mainStageController;
    }

    public FXMLLoader getLoader(String name)
    {
        return mainStageController.getLoader(name);
    }

}
