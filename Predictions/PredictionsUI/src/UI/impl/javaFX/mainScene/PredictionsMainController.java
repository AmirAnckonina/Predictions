package UI.impl.javaFX.mainScene;

import UI.impl.javaFX.tabBody.details.DetailsController;
import UI.impl.javaFX.tabBody.details.DetailsModel;
import UI.impl.javaFX.tabBody.newExecution.newExecutionMain.NewExecutionController;
import UI.impl.javaFX.tabBody.results.ResultsController;
import UI.impl.javaFX.tabBody.results.ResultsModel;
import UI.impl.javaFX.top.TopController;
import UI.impl.javaFX.top.PredictionsTopModel;
import dto.BasePropertyDto;
import dto.EnvironmentPropertiesDto;
import dto.SimulationDetailsDto;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;
import simulator.mainManager.impl.SimulatorManagerImpl;

import java.util.Map;

public class PredictionsMainController {


    private SimulatorManager simulatorManager;
    private Stage primaryStage;
    private PredictionsTopModel topModule;
    private DetailsModel detailsModel;
    private ResultsModel resultsModel;
    private eCurrentScreen currentScreen = eCurrentScreen.DETAILS;
    private boolean newSimulationLoadedFlag = false;

    @FXML
    private TopController topComponentController;
    @FXML
    private DetailsController detailsComponentController;
    @FXML
    private NewExecutionController newExecutionComponentController;
    @FXML
    private ResultsController resultsComponentController;

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
        topComponentController.setPrimaryStage(primaryStage);
        detailsComponentController.setPrimaryStage(primaryStage);
        newExecutionComponentController.setPrimaryStage(primaryStage);
        resultsComponentController.setPrimaryStage(primaryStage);
    }

    public boolean insertNewLineToLeftListView(String line){
        return detailsComponentController.insertNewLineToLeftEnvironmentListView(line);
    }

    public void onLoadSimulationButtonClicked(String simulationFilePath) {
        simulatorManager.buildSimulationWorld(simulationFilePath);
        this.newSimulationLoadedFlag = true;

        // Here we want to activate the tabs.
        // Details tab - after the workd is built we can collect all the WorldDefinition/SimulationDetails to the view
        // New Exec tab -
        this.newExecutionComponentController.initializeNewExecutionTab();

        switch (currentScreen) {
            case DETAILS:
                detailsTabClicked();
                break;
            case EXECUTION:
                executionTabClicked();
                break;
            case RESULTS:
                resultsTabClicked();
                break;
        }
    }

    public void detailsTabClicked(){
        if(currentScreen == eCurrentScreen.DETAILS && !newSimulationLoadedFlag){return;}

        currentScreen = eCurrentScreen.DETAILS;
        newSimulationLoadedFlag = false;

        SimulationDetailsDto simulationDetailsDto = simulatorManager.getSimulationWorldDetails();
        EnvironmentPropertiesDto environmentPropertiesDto = simulatorManager.getEnvironmentPropertiesDefinition();
        Map<String ,BasePropertyDto> basePropertyDtoMap = environmentPropertiesDto.getPropertiesMap();
        detailsComponentController.setPropertyDtoMap(basePropertyDtoMap);
        detailsComponentController.setSimulationDetailsDto(simulationDetailsDto);
        detailsComponentController.showCurrPropertyDtoList();

        System.out.println("detailsTabClicked");
    }

    public void executionTabClicked() {
        if(currentScreen == eCurrentScreen.EXECUTION && !newSimulationLoadedFlag){return;}

        currentScreen = eCurrentScreen.EXECUTION;
        newSimulationLoadedFlag = false;



        System.out.println("executionTabClicked");
    }

    public void resultsTabClicked(){
        if(currentScreen == eCurrentScreen.RESULTS&& !newSimulationLoadedFlag){return;}

        currentScreen = eCurrentScreen.RESULTS;
        newSimulationLoadedFlag = false;



        System.out.println("resultsTabClicked");


    }
}
