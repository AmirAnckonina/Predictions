package ui.mainScene;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import ui.mainScene.login.LoginController;
import ui.tabs.allocations.AllocationsController;
import ui.tabs.executionsHistory.ExecutionsHistoryController;
import ui.tabs.management.ManagementController;
import utils.eCurrentScreen;

public class MainController {

    private Stage primaryStage;
    private eCurrentScreen currentScreen;
    private boolean newSimulationLoadedFlag;

    @FXML private ManagementController managementTabController;
    @FXML private ExecutionsHistoryController executionHistoryTabController;
    @FXML private AllocationsController allocationsTabController;
    @FXML private LoginController logInController;
    @FXML private TabPane mainTabPane;

    @FXML
    public void initialize() {
        try {
            managementTabController.setMainController(this);
            executionHistoryTabController.setMainController(this);
            allocationsTabController.setMainController(this);
            logInController.setMainController(this);

        }catch (Exception e)
        {
            e.printStackTrace(System.out);
        }

        logoutProcess();
    }

    @FXML
    void allocationsTabClicked() {
        if(currentScreen == eCurrentScreen.ALLOCATIONS){
            allocationsTabController.setInactive();
            return;
        }
        currentScreen = eCurrentScreen.ALLOCATIONS;
        System.out.println("allocationsTabClicked");

        allocationsTabController.setActive();
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

        managementTabController.setActive();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void onRerunSimulation(String guid) {
    }

    public void resetGUI() {
    }

    public void onLoadSimulationButtonClicked(String absolutePath) {
        try {
            //simulatorManager.buildSimulationWorld(simulationFilePath);

            this.newSimulationLoadedFlag = true;

            // Here we want to activate the tabs.
            // Details tab - after the workd is built we can collect all the WorldDefinition/SimulationDetails to the view
            // New Exec tab -
//            this.newExecutionComponentController.initializeNewExecutionTab();

            switch (currentScreen) {
                case MANAGEMENT:
                    managementTabClicked();
                    break;
                default:
                    // an option to add more screens to interact with loaded simulation
                    break;

            }
        }catch (Exception e){

        }
    }

    public void loginProcess() {
        mainTabPane.setDisable(false);
    }

    public void logoutProcess() {
        mainTabPane.setDisable(true);
    }
}
