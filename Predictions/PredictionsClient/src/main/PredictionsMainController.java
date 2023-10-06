package main;

import body.details.DetailsController;
import body.newExecution.newExecutionMain.NewExecutionController;
import body.requests.RequestsTabController;
import body.results.ResultsController;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;
import simulator.mainManager.impl.SimulatorManagerImpl;


public class PredictionsMainController {

    private SimulatorManager simulatorManager = new SimulatorManagerImpl();
    private Stage primaryStage;
    private eCurrentScreen currentScreen = eCurrentScreen.DETAILS;
    private boolean newSimulationLoadedFlag = false;
    @FXML private DetailsController detailsComponentController;
    @FXML private RequestsTabController requestsTabComponentController;
    @FXML private NewExecutionController newExecutionComponentController;
    @FXML private ResultsController resultsComponentController;

    @FXML private TabPane mainTabPane;

    public PredictionsMainController() {

    }

    @FXML
    public void initialize() {
        if (detailsComponentController != null && requestsTabComponentController != null && newExecutionComponentController != null && resultsComponentController != null) {

            //Set details tab component
            detailsComponentController.setMainController(this);

            //Set details tab component
            requestsTabComponentController.setMainController(this);

            //Set execution tab component
            newExecutionComponentController.setPredictionsMainController(this);

            //Set results tab component
            resultsComponentController.setMainController(this);

            detailsTabClicked();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        detailsComponentController.setPrimaryStage(primaryStage);
        newExecutionComponentController.setPrimaryStage(primaryStage);
        requestsTabComponentController.setPrimaryStage(primaryStage);
        resultsComponentController.setPrimaryStage(primaryStage);
    }

    public void detailsTabClicked() {
        if (this.resultsComponentController != null)
        {
            this.requestsTabComponentController.setInactive();
            currentScreen = eCurrentScreen.DETAILS;
            this.detailsComponentController.setActive();
        }
    }

    public void requestsTabClicked() {
        this.detailsComponentController.setInactive();
        currentScreen = eCurrentScreen.REQUESTS;
        this.requestsTabComponentController.setActive();

    }

    public void executionTabClicked() {
        this.detailsComponentController.setInactive();
        this.requestsTabComponentController.setInactive();
        currentScreen = eCurrentScreen.EXECUTION;
        //this.newExecutionComponentController.setActive();
    }

    public void resultsTabClicked(){
        this.detailsComponentController.setInactive();
        this.requestsTabComponentController.setInactive();
        currentScreen = eCurrentScreen.RESULTS;
        //this.resultsComponentController.setActive();
    }

    public void onNewSimulationStart(String simulationGuid) {
        try {
            this.resultsComponentController.addNewSimulationGuid(simulationGuid);
            this.resultsTabClicked();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }


    public void onFirstSimulationStarted() {
        this.resultsComponentController.startUIPollingThread();
    }

    public void onRerunSimulation(String guid) {
        try {

            this.newExecutionComponentController.setNewExecutionTabToRerunSimulation(guid);
            this.executionTabClicked();


        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }

    public void moveFromDetailsToRequests() {
        this.mainTabPane.getSelectionModel().select(1);
        requestsTabClicked();
    }

    public void loadNewExecutionTab(String simulationWorldNameToLoad) {
        try {

            this.newExecutionComponentController.initializeNewExecutionTab(simulationWorldNameToLoad);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
