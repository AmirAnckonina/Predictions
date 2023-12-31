import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.PredictionsMainController;

import java.net.URL;

import static common.CommonResourcesPaths.APP_FXML_LIGHT_RESOURCE;

public class PredictionsClientApp extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        Thread.currentThread().setName("main");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource(APP_FXML_LIGHT_RESOURCE);
        fxmlLoader.setLocation(url);
        Parent root = fxmlLoader.load(url.openStream());

        PredictionsMainController appController = fxmlLoader.getController();
        appController.setPrimaryStage(this.primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
