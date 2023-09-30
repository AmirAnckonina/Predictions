package ui.tabs.management;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.mainScene.MainController;

import java.io.File;

public class ManagementController {

    @FXML
    private Label currLoadedFilePathLbl;

    @FXML
    private Button setThreadsCountButton;

    @FXML
    private GridPane availableSimulationDetailsGrid;

    @FXML
    private GridPane threadPoolManagementGrid;

    @FXML
    private Label waitingValueLabel;

    @FXML
    private Label runningValueLabel;

    @FXML
    private Label runningLabel;

    @FXML
    private Label finishedValueLabel;

    @FXML
    private Button setNumOfThreadBtn;

    @FXML
    private Label setThreadMassageLbl;
    private MainController mainController;
    private Stage primaryStage;

    @FXML
    public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
    }


    @FXML
    void loadFileButtonClicked(ActionEvent event) {
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
    void setNumOfThreadBtnClicked(ActionEvent event) {

    }

    @FXML
    void setThreadsCountButtonClicked(ActionEvent event) {

    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}