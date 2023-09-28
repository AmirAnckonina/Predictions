package ui.mainScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import ui.allocations.AllocationsController;
import ui.executionsHistory.ExecutionsHistoryController;
import ui.executionsHistory.detailsComponent.histogram.ExecutionHistogramController;
import ui.management.ManagementController;

public class MainController {

    private Stage primaryStage;

    @FXML private ManagementController managementTabController;
    @FXML private ExecutionsHistoryController executionHistoryTabController;
    @FXML private AllocationsController allocationsTabController;

    @FXML
    public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
    }

    @FXML
    void allocationsTabClicked(ActionEvent event) {

    }

    @FXML
    void executionsHistoryTabClicked(ActionEvent event) {

    }

    @FXML
    void managementTabSelected(ActionEvent event) {

    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void onRerunSimulation(String guid) {
    }
}
