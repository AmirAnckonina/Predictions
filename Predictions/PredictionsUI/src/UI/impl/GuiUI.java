package UI.impl;

import UI.api.UserInterface;
import UI.dynamicbody.DynamicInfoController;
import UI.staticheader.StaticHeaderController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

import static UI.utils.enums.CommonResourcesPaths.*;

public class GuiUI extends Application implements UserInterface{
    @Override
    public void runSimulatorUI() {

    }

    @Override
    public void loadSimulationSession() {

    }

    @Override
    public void showLoadedSimulationWorldDetails() {

    }

    @Override
    public void runSimulationSession() {

    }

    @Override
    public void showHistoricalSimulationResult() {

    }

    @Override
    public void exitSimulator() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // load header component and controller from fxml
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource(HEADER_fXML_RESOURCE);
        fxmlLoader.setLocation(url);
        ScrollPane headerComponent = fxmlLoader.load(url.openStream());
        StaticHeaderController headerController = fxmlLoader.getController();

        // load body component and controller from fxml
        fxmlLoader = new FXMLLoader();
        url = getClass().getResource(BODY_FXML_RESOURCE);
        fxmlLoader.setLocation(url);
        ScrollPane bodyComponent = fxmlLoader.load(url.openStream());
        DynamicInfoController bodyController = fxmlLoader.getController();

        // load master app and controller from fxml
        fxmlLoader = new FXMLLoader();
        url = getClass().getResource(APP_FXML_LIGHT_RESOURCE);
        fxmlLoader.setLocation(url);
        BorderPane root = fxmlLoader.load(url.openStream());
        AppController appController = fxmlLoader.getController();

        // add subcomponents to master app placeholders
        root.setTop(headerComponent);
        root.setCenter(bodyComponent);

        // connect between controllers
        appController.setBodyComponentController(bodyController);
        appController.setHeaderComponentController(headerController);

        Scene scene = new Scene(root, 500, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
