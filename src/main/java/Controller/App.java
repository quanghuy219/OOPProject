package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    public static SceneManager sceneManager;
    public static DataManager dataManager;

    @Override
    public void start(Stage primaryStage) throws Exception{
        sceneManager = new SceneManager();
        dataManager = new DataManager();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/MainStage.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("MediaOneeeeeeeeee");
        Scene mainScene = new Scene(root, 1368, 700);

        sceneManager.setMainBodyPane((Pane) mainScene.lookup("#mainBody"));
        sceneManager.setPaneContent("Dashboard");


        primaryStage.setScene(mainScene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


//    public static void switchPane(Node newPane)
//    {
//        mainBodyPane.getChildren().removeAll(mainBodyPane.getChildren());
//        mainBodyPane.getChildren().add(newPane);
//    }
}
