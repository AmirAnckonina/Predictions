package UI.impl.javaFX.mainScene;

import UI.impl.javaFX.tabBody.details.DetailsController;
import UI.impl.javaFX.tabBody.details.DetailsModel;
import UI.impl.javaFX.tabBody.newExecution.newExecutionMain.NewExecutionController;
import UI.impl.javaFX.tabBody.results.ResultsController;
import UI.impl.javaFX.tabBody.results.ResultsModel;
import UI.impl.javaFX.top.TopController;
import UI.impl.javaFX.top.PredictionsTopModel;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import response.SimulatorResponse;
import simulator.mainManager.api.SimulatorManager;
import simulator.mainManager.impl.SimulatorManagerImpl;

public class PredictionsMainController {


    private SimulatorManager simulatorManager;
    private Stage primaryStage;
    private PredictionsTopModel topModule;
    private DetailsModel detailsModel;
    private ResultsModel resultsModel;

    @FXML
    private TopController topComponentController;
    @FXML
    private DetailsController detailsComponentController;
    @FXML
    private ResultsController resultsComponentController;
    @FXML
    private NewExecutionController newExecutionComponentController;

    public PredictionsMainController() {
        this.simulatorManager = new SimulatorManagerImpl();
    }

    @FXML
    public void initialize() {
        if (topComponentController != null && detailsComponentController != null) {
            topModule = new PredictionsTopModel();
            resultsModel = new ResultsModel();
            detailsModel = new DetailsModel();

            // Set top component
            topComponentController.setMainController(this);
            topComponentController.setModel(topModule);
            topComponentController.setSimulatorManager(simulatorManager);
            topModule.setController(topComponentController);

            //Set details tab component
            detailsComponentController.setMainController(this);
            detailsComponentController.setDetailsModel(detailsModel);
            detailsComponentController.setSimulatorManager(simulatorManager);
            detailsModel.setController(detailsComponentController);

            //Set execution tab component
            newExecutionComponentController.setPredictionsMainController(this);
            newExecutionComponentController.setSimulatorManager(simulatorManager);

            //Set results tab component
            resultsComponentController.setMainController(this);
            resultsComponentController.setResultsModel(resultsModel);
            resultsComponentController.setSimulatorManager(simulatorManager);
            resultsModel.setResultsController(resultsComponentController);
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public boolean insertNewLineToLeftListView(String line){
        return detailsComponentController.insertNewLineToLeftListView(line);
    }

    public void onLoadSimulationButtonClicked(String simulationFilePath) {
        simulatorManager.buildSimulationWorld(simulationFilePath);
        simulatorManager.getEnvironmentPropertiesDefinition();
    }

    public void detailsTabClicked(){
        System.out.println("detailsTabClicked");
    }

    public void executionTabClicked(){
        System.out.println("executionTabClicked");
    }

    public void resultsTabClicked(){
        System.out.println("resultsTabClicked");
    }
}
