package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class App extends Application {

    public static SceneManager sceneManager;
    public static DataManager dataManager;

    public static final String defaultPath = "D:\\JavaOOP\\MediaOne\\target\\classes\\Image\\default.png";

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

        sceneManager.setMainStage(primaryStage);

        primaryStage.setScene(mainScene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static String[] getEnumConstants(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

    public static Boolean displayConfirmBox(String _text)
    {
        AtomicBoolean selection = new AtomicBoolean(false);

        JFXButton yesButton;
        JFXButton noButton;
        Label textLabel;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Cancel");
        window.setMinWidth(350);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/View/ConfirmBox.fxml"));
        AnchorPane confirmBoxLayout = null;
        try {
            confirmBoxLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(confirmBoxLayout);
        yesButton = (JFXButton) scene.lookup("#yesButton");
        noButton = (JFXButton) scene.lookup("#noButton");
        textLabel = (Label) scene.lookup("#textLabel");

        yesButton.setOnAction(e -> {
            selection.set(true);
            window.close();
        });

        noButton.setOnAction(e -> {
            selection.set(false);
            window.close();
        });

        textLabel.setText(_text);

        window.setScene(scene);

        window.showAndWait();
        return selection.get();
    }


//    public static void switchPane(Node newPane)
//    {
//        mainBodyPane.getChildren().removeAll(mainBodyPane.getChildren());
//        mainBodyPane.getChildren().add(newPane);
//    }


}
