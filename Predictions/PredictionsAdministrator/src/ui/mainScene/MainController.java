package ui.mainScene;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import ui.tabs.allocations.AllocationsController;
import ui.tabs.executionsHistory.ExecutionsHistoryController;
import ui.tabs.management.ManagementController;
import utils.eCurrentScreen;

public class MainController {

    private Stage primaryStage;
    private eCurrentScreen currentScreen;

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
    void allocationsTabClicked() {
        if(currentScreen == eCurrentScreen.ALLOCATIONS){ return; }
        currentScreen = eCurrentScreen.ALLOCATIONS;
        System.out.println("allocationsTabClicked");
    }

    @FXML
    void executionsHistoryTabClicked() {
        if (currentScreen == eCurrentScreen.RESULTS) { return; }
        currentScreen = eCurrentScreen.RESULTS;
        System.out.println("executionsHistoryTabClicked");

    }

    @FXML
    void managementTabClicked() {
        if (currentScreen == eCurrentScreen.MANAGEMENT) { return; }
        currentScreen = eCurrentScreen.MANAGEMENT;
        System.out.println("managementTabClicked");
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void onRerunSimulation(String guid) {
    }
}
