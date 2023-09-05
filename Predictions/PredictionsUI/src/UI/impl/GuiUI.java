package UI.impl;

import UI.api.UserInterface;
import UI.dynamicbody.DynamicInfoController;
import UI.staticheader.StaticHeaderController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;
import simulator.mainManager.impl.SimulatorManagerImpl;
import simulator.result.manager.api.ResultManager;

import java.io.File;
import java.net.URL;

import static UI.utils.enums.CommonResourcesPaths.*;

public class GuiUI extends Application{

    private SimulatorManager simulatorManager;
    private ResultManager simulatorResultManager;
    private Stage primaryStage;

    public GuiUI() {
        this.simulatorManager = new SimulatorManagerImpl();
        this.simulatorResultManager = this.simulatorManager.getSimulatorResultManagerImpl();
    }

    public void runSimulatorUI() {

    }

    public void loadSimulationSession() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(this.primaryStage);
        int i = 1;
    }

    public void showLoadedSimulationWorldDetails() {

    }

    public void runSimulationSession() {

    }

    public void showHistoricalSimulationResult() {

    }

    public void exitSimulator() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource(APP_FXML_LIGHT_RESOURCE);
        fxmlLoader.setLocation(url);
        Parent root = fxmlLoader.load(url.openStream());

        Scene scene = new Scene(root, 500, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("main");
        launch(args);
    }
}
