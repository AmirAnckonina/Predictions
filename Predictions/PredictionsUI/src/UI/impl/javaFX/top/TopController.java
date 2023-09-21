package UI.impl.javaFX.top;
import UI.impl.javaFX.mainScene.PredictionsMainController;
import dto.SimulationsStatusesOverviewDto;
import enums.OverviewSimulationStatus;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    @FXML private RadioButton enableAnimationRB;
    @FXML private Label waitingValueLabel;

    @FXML private TextField queueTitle;
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select simulation");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Simulation", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile == null) {
            return;
        }
        mainController.resetGUI();
        this.loadSimulationPath.setText(selectedFile.getAbsolutePath());
        this.predictionsTopModel.onLoadSimulationButtonClicked();
        this.mainController.onLoadSimulationButtonClicked(selectedFile.getAbsolutePath());

        if(enableAnimationRB.isSelected()){
            FadeTransition ft = new FadeTransition(Duration.millis(800), queueTitle);
            ft.setFromValue(1);
            ft.setToValue(0.2);
            ft.setAutoReverse(true);
            FadeTransition ftIn = new FadeTransition(Duration.millis(800), queueTitle);
            ftIn.setFromValue(0.2);
            ftIn.setToValue(1);
            ftIn.setAutoReverse(true);
            ft.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ftIn.play(); // Start the fade-in animation
                }
            });
            RotateTransition rt = new RotateTransition(Duration.millis(1200), enableAnimationRB);
            rt.setByAngle(360);
            rt.play();
            ft.play();
        }
    }


    @FXML
    void onSkinSelected() {
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

    public void showUserBuilderException(String errorMessage) {
        this.loadSimulationPath.setText(errorMessage);
    }

    public void reset() {
//        this.finishedValue.set(0);
//        this.waitingValue.set(0);
//        this.runningValue.set(0);
    }
}
