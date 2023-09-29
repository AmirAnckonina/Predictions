package ui.mainScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
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
    @FXML private TabPane mainTabPane;

    @FXML
    public void initialize() {
        try {
            ToggleGroup toggleGroup = new ToggleGroup();
        }catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    @FXML
    void allocationsTabClicked(ActionEvent event) {
        System.out.println("allocationsTabClicked");
    }

    @FXML
    void executionsHistoryTabClicked(ActionEvent event) {
        System.out.println("executionsHistoryTabClicked");

    }

    @FXML
    void managementTabClicked(ActionEvent event) {
        System.out.println("executionsHistoryTabClicked");
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void onRerunSimulation(String guid) {
    }
}
