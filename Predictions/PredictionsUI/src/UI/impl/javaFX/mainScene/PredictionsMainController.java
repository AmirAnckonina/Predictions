package UI.impl.javaFX.mainScene;

import UI.impl.javaFX.tabBody.details.DetailsController;
import UI.impl.javaFX.tabBody.details.DetailsModel;
import UI.impl.javaFX.tabBody.newExecution.newExecutionMain.NewExecutionController;
import UI.impl.javaFX.tabBody.results.ResultsController;
import UI.impl.javaFX.tabBody.results.detailsComponent.ResultsModel;
import UI.impl.javaFX.top.TopController;
import UI.impl.javaFX.top.PredictionsTopModel;
import dto.EnvironmentPropertiesDto;
import dto.SimulationDetailsDto;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.mainManager.api.SimulatorManager;
import simulator.mainManager.impl.SimulatorManagerImpl;

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

    @FXML
    private TabPane mainTabPane;

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
            detailsModel.setSimulatorManager(simulatorManager);

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
        try {
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
        }catch (WorldBuilderException e){
            topComponentController.showUserBuilderException(e.getMessage());
        }catch (Exception e){

        }
    }

    public void detailsTabClicked(){
        if(currentScreen == eCurrentScreen.DETAILS && !newSimulationLoadedFlag){return;}

        currentScreen = eCurrentScreen.DETAILS;
        newSimulationLoadedFlag = false;

        SimulationDetailsDto simulationDetailsDto = simulatorManager.getSimulationWorldDetails();
        EnvironmentPropertiesDto environmentPropertiesDto = simulatorManager.getEnvironmentPropertiesDefinition();

        detailsComponentController.setPropertyDtoMap(environmentPropertiesDto.getPropertiesMap());
        detailsComponentController.setRuleMap(simulationDetailsDto.getRuleMap());
        detailsComponentController.setEntitiesDtoMap(simulationDetailsDto.getEntityDtoMap());
        detailsComponentController.setTerminationDto(simulationDetailsDto.getTerminationInfo());
        detailsComponentController.showCurrPropertyDtoList();

        System.out.println("detailsTabClicked");
    }

    public void resetGUI(){
        this.topComponentController.reset();
        this.resultsComponentController.reset();
        this.detailsComponentController.reset();
        this.newExecutionComponentController.reset();
    }

    public void executionTabClicked() {
        if(currentScreen == eCurrentScreen.EXECUTION && !newSimulationLoadedFlag){return;}

        currentScreen = eCurrentScreen.EXECUTION;
        newSimulationLoadedFlag = false;



        System.out.println("executionTabClicked");
    }

    public void resultsTabClicked(){
        if (currentScreen == eCurrentScreen.RESULTS && !newSimulationLoadedFlag){ return; }

        currentScreen = eCurrentScreen.RESULTS;
        newSimulationLoadedFlag = false;
        resultsComponentController.simulationTabClicked();

        System.out.println("resultsTabClicked");
    }

    public void onNewSimulationStart(String simulationGuid) {
        try {
            this.resultsComponentController.addNewSimulationGuid(simulationGuid);
            this.resultsTabClicked();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void moveToResultTab() {
        System.out.println("Moved to results");
        mainTabPane.getSelectionModel().select(2);
    }

    public void onFirstSimulationStarted() {
        this.topComponentController.startUIPolling();
        this.resultsComponentController.startUIPolling();
    }
}
