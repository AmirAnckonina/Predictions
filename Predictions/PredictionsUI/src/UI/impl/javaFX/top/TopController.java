package UI.impl.javaFX.top;
import UI.impl.javaFX.mainScene.PredictionsMainController;
import dto.SimulationsStatusesOverviewDto;
import enums.OverviewSimulationStatus;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TopController {

    private PredictionsMainController mainController;
    private PredictionsTopModel predictionsTopModel;
    private Stage primaryStage;
    private SimulatorManager simulatorManager;

    private SimpleIntegerProperty runningValue;
    private SimpleIntegerProperty finishedValue;
    private SimpleIntegerProperty waitingValue;

    @FXML private Button loadSimulationButton;
    @FXML private Label loadSimulationPath;
    @FXML private ComboBox<?> chooseSkinCB;
    @FXML private Label waitingValueLabel;
    @FXML private Label predictionTitle;
    @FXML private Label runningLabel;
    @FXML private Label runningValueLabel;
    @FXML private Label finishedValueLabel;


    public TopController() {
        this.finishedValue = new SimpleIntegerProperty(0);
        this.waitingValue = new SimpleIntegerProperty(0);
        this.runningValue = new SimpleIntegerProperty(0);
    }

    @FXML
    private void initialize() {
        this.runningValueLabel.textProperty().bind(this.runningValue.asString());
        this.waitingValueLabel.textProperty().bind(this.waitingValue.asString());
        this.finishedValueLabel.textProperty().bind(this.finishedValue.asString());
    }

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

    public void collectSimulationsStatuses() {
        try {
            SimulationsStatusesOverviewDto simulationsStatusesOverviewDto = this.simulatorManager.collectAllSimulationsStatuses();
            updateQueueManagementInfo(simulationsStatusesOverviewDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateQueueManagementInfo(SimulationsStatusesOverviewDto simulationsStatusesOverviewDto) {
        Platform.runLater(() -> {
            this.runningValue.setValue(simulationsStatusesOverviewDto.getSimulationsStatusesOverviewMap().get(OverviewSimulationStatus.RUNNING));
            this.waitingValue.setValue(simulationsStatusesOverviewDto.getSimulationsStatusesOverviewMap().get(OverviewSimulationStatus.WAITING));
            this.finishedValue.setValue(simulationsStatusesOverviewDto.getSimulationsStatusesOverviewMap().get(OverviewSimulationStatus.FINISHED));
        });

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

    public void startUIPolling() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(this::pollUpdatedSimulationStatusesOverview, 0, 1, TimeUnit.SECONDS);

    }

    private void pollUpdatedSimulationStatusesOverview() {
        collectSimulationsStatuses();
    }

    public void showUserBuilderException(String massage) {
        this.loadSimulationPath.setText(massage);
    }

    public void reset() {
        mainController.resetGUI();
    }
}
