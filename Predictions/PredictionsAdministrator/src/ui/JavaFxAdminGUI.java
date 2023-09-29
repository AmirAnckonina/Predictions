package ui;

import ui.common.CommonResourcesPaths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.mainScene.MainController;

import java.net.URL;

import static ui.common.CommonResourcesPaths.APP_FXML_LIGHT_RESOURCE;


public class JavaFxAdminGUI extends Application {


    private Stage primaryStage;

    public JavaFxAdminGUI() {

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource(APP_FXML_LIGHT_RESOURCE);
        fxmlLoader.setLocation(url);
        Parent root = null;
        try {
            root = fxmlLoader.load(url.openStream());
        }catch (Exception e){
            e.printStackTrace(System.out);
        }

        MainController appController = fxmlLoader.getController();
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
