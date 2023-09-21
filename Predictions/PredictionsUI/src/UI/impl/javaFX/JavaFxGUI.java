package UI.impl.javaFX;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

import static UI.impl.javaFX.common.CommonResourcesPaths.*;

public class JavaFxGUI extends Application{


    private Stage primaryStage;

    public JavaFxGUI() {

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource(APP_FXML_LIGHT_RESOURCE);
        fxmlLoader.setLocation(url);
        Parent root = fxmlLoader.load(url.openStream());

        PredictionsMainController appController = fxmlLoader.getController();
        appController.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("main");
        launch(args);
    }
}
