package UI.impl.javaFX.top;
import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.top.queue.QueueManagementController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;

import java.io.File;

public class TopController {

    private PredictionsMainController mainController;
    private PredictionsTopModel predictionsTopModel;
    private Stage primaryStage;
    private SimulatorManager simulatorManager;
    @FXML
    private QueueManagementController queueManagementController;

    @FXML
    private Button loadSimulationButton;

    @FXML
    private Label loadSimulationPath;

    @FXML
    private ComboBox<?> chooseSkinCB;

    @FXML
    private Label predictionTitle;


    @FXML
    void loadSimulationButtonClicked(ActionEvent event) {
        insertNewLineToQueueComponent("test");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select simulation");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Simulation", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if(selectedFile == null){
            return;
        }

        this.loadSimulationPath.setText(selectedFile.getAbsolutePath());
        this.predictionsTopModel.onLoadSimulationButtonClicked();
        this.mainController.onLoadSimulationButtonClicked(selectedFile.getAbsolutePath());
    }

    @FXML
    void onSkinSelected(ActionEvent event) {

        System.out.println("onSkinSelected");
    }

    public void setMainController(PredictionsMainController mainController) {
        this.mainController = mainController;
    }
    public void setModel(PredictionsTopModel predictionsTopModel) {
        this.predictionsTopModel = predictionsTopModel;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setSimulatorManager(SimulatorManager simulatorManager) {
        this.simulatorManager = simulatorManager;
    }

    public void insertNewLineToQueueComponent(String line){
        queueManagementController.addItem(line);
    }

    public void removeLineFromQueueComponent(String line){
        queueManagementController.removeItem(line);
    }

    public void cleanQueueComponent(){
        queueManagementController.cleanList();
    }

    public void showUserBuilderException(String massage) {
        this.loadSimulationPath.setText(massage);
    }

    public void reset() {
        mainController.resetGUI();
    }
}
