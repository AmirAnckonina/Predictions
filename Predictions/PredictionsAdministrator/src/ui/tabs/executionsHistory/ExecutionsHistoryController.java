package ui.tabs.executionsHistory;



import java.net.URL;
import java.util.*;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import dto.simulationInfo.*;
import enums.SimulationExecutionStatus;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ui.tabs.executionsHistory.detailsComponent.DetailsResultControllerImpl;
import ui.tabs.executionsHistory.detailsComponent.histogram.byEntities.ExecutionResultByEntityController;
import ui.tabs.executionsHistory.detailsComponent.entityPopulationGraph.EntityPopulationGraphController;
import ui.tabs.executionsHistory.detailsComponent.entityPopulationListView.EntityPopulationListViewController;
import ui.tabs.executionsHistory.detailsComponent.histogram.ExecutionHistogramController;
import ui.tabs.executionsHistory.detailsComponent.histogram.byStatistic.ExecutionResultStatisticByPropertyController;
import ui.mainScene.MainController;
import simulator.mainManager.api.SimulatorManager;
import utils.SimulationDocumentRefresher;
import utils.SimulationGuidListRefresher;
import utils.SimulationResultRefresher;

import static ui.common.CommonResourcesPaths.*;
import static utils.Constants.SIMULATION_REQUESTS_LIST_REFRESH_RATE;


public class ExecutionsHistoryController {
    private MainController mainController;
    private ExecutionHistogramController executionHistoryController;
    private ExecutionResultStatisticByPropertyController executionResultStatisticByPropertyController;
    private EntityPopulationGraphController entityPopulationGraphController;
    private EntityPopulationListViewController entityPopulationListViewController;
    private Stage primaryStage;
    private ScheduledExecutorService scheduledExecutorService;
    private ExecutionResultByEntityController executionResultByEntityController = null;
    @FXML
    private DetailsResultControllerImpl detailsResultController;

    @FXML private ListView<Label> executionListView;

    @FXML private Button reRunButton;

    @FXML private RadioButton resultByEntity;
    @FXML private RadioButton entityStatisticsRadioButton;
    @FXML private RadioButton simulationStatisticsRadioButton;
    @FXML private RadioButton entityGraphPopulationRadioButton;

    @FXML private GridPane resultComponentHolderGP;
    private SimulatorManager simulatorManager;

    private String simulatorResultManager;
    private SimulationDocumentRefresher simulationInfoListRefresher;
    private SimulationGuidListRefresher simulationGuidListRefresher;
    private SimulationResultRefresher simulationResultRefresher;
    private Timer simulationInfoTableTimer;
    private SimulationDocumentInfoDto currSimulationDocumentInfoDto;

    @FXML
    public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        detailsResultController.setMainController(this);
        entityStatisticsRadioButton.setToggleGroup(toggleGroup);
        simulationStatisticsRadioButton.setToggleGroup(toggleGroup);
        entityGraphPopulationRadioButton.setToggleGroup(toggleGroup);
        resultByEntity.setToggleGroup(toggleGroup);
        startSimulationGuidListRefresher();
    }

    public ExecutionsHistoryController() {
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }
    @FXML
    public void reRunButtonClicked(ActionEvent event) {
        try {
            String guid = this.executionListView.getSelectionModel().getSelectedItem().getText();
            this.mainController.onRerunSimulation(guid);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    @FXML
    void resultByEntityClicked(MouseEvent event) {
        try {
            this.executionListView.getSelectionModel().getSelectedItem().getText();
            simulationIDListClicked(null);

        }catch (Exception e){
            //Means that no simulation ID was selected while clicking on the radio button
        }
    }

    @FXML
    void entityGraphPopulationRadioButtonClicked(MouseEvent event) {
        try {
            this.executionListView.getSelectionModel().getSelectedItem().getText();
            simulationIDListClicked(null);

        }catch (Exception e){
            //Means that no simulation ID was selected while clicking on the radio button
        }
    }

    @FXML
    void propertyStatisticClicked(MouseEvent event) {
        try {
            this.executionListView.getSelectionModel().getSelectedItem().getText();
            simulationIDListClicked(null);

        }catch (Exception e){
            //Means that no simulation ID was selected while clicking on the radio button
        }
    }

    @FXML
    void simulationStatisticClicked(MouseEvent event) {
        try {
            this.executionListView.getSelectionModel().getSelectedItem().getText();

            simulationIDListClicked(null);

        }catch (Exception e){
            //Means that no simulation ID was selected while clicking on the radio button
        }
    }

    @FXML
    public void simulationIDListClicked(MouseEvent event) {

        try {
            String guid = this.executionListView.getSelectionModel().getSelectedItem().getText();
            startSimulationDocumentRefresher();
//            SimulationDocumentInfoDto simulationDocumentInfoDto = this.simulatorManager.getLatestSimulationDocumentInfo(guid);
            startSimulationResultRefresher();
//            updateSimulationResultComponent(simulationDocumentInfoDto); //info component
//            startUIPollingThread();
            synchronized (this) {
                if (currSimulationDocumentInfoDto.getSimulationStatus() == SimulationExecutionStatus.STOPPED ||
                        currSimulationDocumentInfoDto.getSimulationStatus() == SimulationExecutionStatus.COMPLETED) {
                    if (resultByEntity.isSelected()) {
                        List<String> simulationEntities = new ArrayList<>();
                        currSimulationDocumentInfoDto.getCurrentEntityPopulationMap().forEach(
                                (entityName, numOfEntities) -> simulationEntities.add(entityName + ": " + numOfEntities));
                        createHistogramByEntityComponent(simulationEntities);
                    } else {
                        if (entityStatisticsRadioButton.isSelected()) {
                            createHistogramByPropertyComponent(simulatorManager.getAllEntities(""));
                        } else if (simulationStatisticsRadioButton.isSelected()) {
                            createStatisticByPropertyComponent(simulatorManager.getAllEntities(""));
                        } else if (entityGraphPopulationRadioButton.isSelected()) {
                            createEntityPopulationGraphComponent();
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace(System.out);
        }
    }

    private void pollUpdatedSimulationDocumentDto() {
        try {
            if (this.executionListView.getSelectionModel().getSelectedItem() != null) {
                // get the current simulation Guid - according to what currently choosed under lost view
                String guid = this.executionListView.getSelectionModel().getSelectedItem().getText();
                SimulationDocumentInfoDto simulationDocumentDto = this.simulatorManager.getLatestSimulationDocumentInfo(guid);
                Platform.runLater(() -> {
                    updateSimulationInfoUI(simulationDocumentDto);
                });
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void updateSimulationInfoUI(SimulationDocumentInfoDto simulationDocumentInfoDto) {
        detailsResultController.setValues(simulationDocumentInfoDto.getSimulationGuid(),
                (new Integer(simulationDocumentInfoDto.getTickNo() + 1)).toString(),
                simulationDocumentInfoDto.getTimePassedInSeconds().toString(),
                simulationDocumentInfoDto.getSimulationStatus().toString(),
                simulationDocumentInfoDto.getCurrentEntityPopulationMap(),
                simulationDocumentInfoDto.getInitialEntityPopulationMap());
    }

    private void updateSimulationList(List<String> simulationList){
        executionListView.getItems().clear();
        simulationList.forEach(simulationGuid -> executionListView.getItems().add(new Label(simulationGuid)));
    }

    private void startSimulationDocumentRefresher() {
        this.simulationInfoListRefresher = new SimulationDocumentRefresher(this::updateSimulationInfoUI,
                executionListView.getSelectionModel().getSelectedItem().toString());
        this.simulationInfoTableTimer = new Timer();
        this.simulationInfoTableTimer.schedule(simulationInfoListRefresher, SIMULATION_REQUESTS_LIST_REFRESH_RATE,
                SIMULATION_REQUESTS_LIST_REFRESH_RATE);
    }

    private void startSimulationGuidListRefresher() {
        this.simulationGuidListRefresher = new SimulationGuidListRefresher(this::updateSimulationList);
        this.simulationInfoTableTimer = new Timer();
        this.simulationInfoTableTimer.schedule(simulationGuidListRefresher, SIMULATION_REQUESTS_LIST_REFRESH_RATE, SIMULATION_REQUESTS_LIST_REFRESH_RATE);
    }

    private void startSimulationResultRefresher() {
        this.simulationResultRefresher = new SimulationResultRefresher(this::updateSimulationResultComponent,
                executionListView.getSelectionModel().getSelectedItem().toString());
        this.simulationInfoTableTimer = new Timer();
        this.simulationInfoTableTimer.schedule(simulationResultRefresher, SIMULATION_REQUESTS_LIST_REFRESH_RATE, SIMULATION_REQUESTS_LIST_REFRESH_RATE);
    }

    private void updateSimulationResultComponent(SimulationDocumentInfoDto simulationDocumentInfoDto) {
        detailsResultController.setValues(simulationDocumentInfoDto.getSimulationGuid(),
                (new Integer(simulationDocumentInfoDto.getTickNo() + 1)).toString(),
                simulationDocumentInfoDto.getTimePassedInSeconds().toString(),
                simulationDocumentInfoDto.getSimulationStatus().toString(),
                simulationDocumentInfoDto.getCurrentEntityPopulationMap(),
                simulationDocumentInfoDto.getInitialEntityPopulationMap());
        synchronized (this) {
            currSimulationDocumentInfoDto = simulationDocumentInfoDto;
        }
    }

    private void createStatisticByPropertyComponent(List<String> entitiesList){
        try {
            if (this.executionListView.getSelectionModel().getSelectedItem() != null) {
                // get the current simulation Guid - according to what currently choosed under lost view
                String guid = this.executionListView.getSelectionModel().getSelectedItem().getText();
                PropertiesConsistencyDto simulationDocumentDto =
                        this.simulatorManager.getEntitiesPropertiesConsistencyMapByGuid(guid);
                PropertiesAvgConsistencyDto propertiesAvgConsistencyDto =
                        this.simulatorManager.getEntitiesNumericPropertiesAverageByGuid(guid);

                FXMLLoader loader = new FXMLLoader();
                URL fxmlUrl = getClass().getResource(RESULT_SIMULATION_PROPERTY_STATIC_HISTOGRAM_FXML_RESOURCE);
                loader.setLocation(fxmlUrl);
                GridPane gpComponent = loader.load();

                executionHistoryController = loader.getController();
                executionHistoryController.setMainController(this);
                executionHistoryController.setLeftEntitiesList(entitiesList);
                executionHistoryController.setPropertiesAvgConsistencyDto(propertiesAvgConsistencyDto);
                executionHistoryController.setPropertiesConsistencyDto(simulationDocumentDto);

                resultComponentHolderGP.getChildren().clear();
                resultComponentHolderGP.getChildren().add(gpComponent);

            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void createHistogramByPropertyComponent(List<String> entitiesList){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(RESULT_SIMULATION_PROPERTY_DETAILS_HISTOGRAM_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            executionHistoryController = loader.getController();
            executionHistoryController.setMainController(this);
            executionHistoryController.setLeftEntitiesList(entitiesList);
            resultComponentHolderGP.getChildren().clear();
            resultComponentHolderGP.getChildren().add(gpComponent);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void createEntityPopulationGraphComponent(){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            //URL fxmlUrl = getClass().getResource(RESULT_SIMULATION_ENTITIES_GRAPH_HISTOGRAM_FXML_RESOURCE);
            URL fxmlUrl = getClass().getResource(RESULT_SIMULATION_ENTITIES_POPULATION_LV_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            String guid = this.executionListView.getSelectionModel().getSelectedItem().getText();
            EntityPopulationOvertimeDto entityPopulationOvertimeDto =
                    this.simulatorManager.getEntityPopulationOvertimeByGuid(guid);

            //entityPopulationGraphController = loader.getController();
            entityPopulationListViewController = loader.getController();
            // entityPopulationGraphController.setPrimaryStage(this.primaryStage);
            //entityPopulationGraphController.setPrimaryStage(this.primaryStage);
            // resultComponentHolderGP.getChildren().clear();
            resultComponentHolderGP.getChildren().clear();
            resultComponentHolderGP.getChildren().add(gpComponent);
            // entityPopulationGraphController.initEntityPopulationLineChart(entityPopulationOvertimeDto);
            entityPopulationListViewController.initEntityPopulationListView(entityPopulationOvertimeDto);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void createHistogramByEntityComponent(List<String> entitiesList){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(RESULT_SIMULATION_ENTITY_DETAILS_HISTOGRAM_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();
            ExecutionResultByEntityController controller = loader.getController();
            controller.setList(entitiesList);
            resultComponentHolderGP.getChildren().clear();
            resultComponentHolderGP.getChildren().add(gpComponent);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }

    public void entityChosenInHistogramByProperty(String entityName){
//        executionResultByPropertyController.clearRightEntityList();
//        SimulationResultMappedProperties mappedPropertiesValuesToNumOfEntitiesWithSameValue = simulatorManager.
//                getMappedPropertiesToNumOfEntitiesWithSameValues(propertyName, this.executionListView.getSelectionModel().getSelectedItem().getText());
//        List<String> resMapped = new ArrayList<>();
//        mappedPropertiesValuesToNumOfEntitiesWithSameValue.getMappedPropertiesToNumOfEntitiesByValues()
//                .forEach((propName, numOfEntities) -> resMapped.add(propName + ": " + numOfEntities));
//        executionResultByPropertyController.setPropertiesList(resMapped);
        List<String> resList = simulatorManager.getPropertiesByEntity("", entityName);
        executionHistoryController.setPropertiesList(resList);
    }

//    public void entityChosenInHistogramByPropertyStatistic(List<String> entitiesList){
//        try {
//            if (this.executionListView.getSelectionModel().getSelectedItem() != null) {
//                // get the current simulation Guid - according to what currently choosed under lost view
//                String guid = this.executionListView.getSelectionModel().getSelectedItem().getText();
//                PropertiesConsistencyDto simulationDocumentDto = this.simulatorResultManager
//                        .getSimulationResultBySimulationId(guid).getEntitiesPropertiesConsistencyMap();
//                PropertiesAvgConsistencyDto propertiesAvgConsistencyDto = this.simulatorResultManager
//                        .getSimulationResultBySimulationId(guid).getEntitiesPropertiesAvgDto();
//
//                FXMLLoader loader = new FXMLLoader();
//                URL fxmlUrl = getClass().getResource(RESULT_SIMULATION_PROPERTY_DETAILS_HISTOGRAM_FXML_RESOURCE);
//                loader.setLocation(fxmlUrl);
//                GridPane gpComponent = loader.load();
//
//                executionResultStatisticByPropertyController = loader.getController();
//                executionResultStatisticByPropertyController.setMainController(this);
//                executionResultStatisticByPropertyController.setLeftEntitiesList(entitiesList);
//                executionResultStatisticByPropertyController.setPropertiesAvgConsistencyDto(propertiesAvgConsistencyDto);
//                executionResultStatisticByPropertyController.setPropertiesConsistencyDto(simulationDocumentDto);
//
//                resultComponentHolderGP.getChildren().clear();
//                resultComponentHolderGP.getChildren().add(gpComponent);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//    }

    public void propertyChosenInHistogramByProperty(String propertyName, String entityName){
        SimulationResultMappedProperties mappedProperties = this.simulatorManager.
                getMappedPropertiesToNumOfEntitiesWithSameValues(propertyName, entityName,
                        this.executionListView.getSelectionModel().getSelectedItem().getText());

        List<String> mappedPropertiesList = new ArrayList<>();
        mappedProperties.getMappedPropertiesToNumOfEntitiesByValues().forEach(
                (value, numOfEntities) -> mappedPropertiesList.add(value + ": " + numOfEntities));

        executionHistoryController.setRightEntitiesList(mappedPropertiesList);
    }

    public void simulationTabClicked() {

//        //Consider use it under bottom component that responsible to show result (histogram ... )
//        this.executionListView.getItems().clear();
//        this.simulatorResultManager = this.simulatorManager.getSimulatorResultManagerImpl();
//
//        List<SimulationResult> simulationResults = this.simulatorResultManager.getSortedHistoricalSimulationsList();
//
//        for (SimulationResult simulationResult:simulationResults){
//
//        }
//        for (int i = 0; i < simulationResults.size(); i++) {
//            Label simulationIDLbl = new Label();
//            simulationIDLbl.setText((i + 1) + ". SimulationUuid:\"" + simulationResults.get(i).getSimulationUuid() + "\"" + ": "
//                    + getSimulatorStartingTimeInString(simulationResults.get(i)));
//            System.out.println();
//            executionListView.getItems().add(simulationIDLbl);
//        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

//    private String getSimulatorStartingTimeInString(SimulationResult simulationResults){
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy | HH.mm.ss");
//        String formattedTime = formatter.format(new Date(simulationResults.getSimulationStartingTime()));
//
//        return formattedTime;
//    }

    public void addNewSimulationGuid(String simulationGuid) {
        // Impl adding to listView
        this.executionListView.getItems().add(new Label(simulationGuid));
    }

    public void onPauseSimulation(String simulationID) {
        try {
            SimulationDocumentInfoDto simulationDocumentInfoDto = this.simulatorManager.pauseSimulationByGuid(simulationID);
            this.detailsResultController.setSimulationInfoByGuid(simulationID, simulationDocumentInfoDto);
        } catch (Exception e){
            e.printStackTrace(System.out);
        }
    }

    public void onStopSimulation(String simulationID) {
        try {
            SimulationDocumentInfoDto simulationDocumentInfoDto = this.simulatorManager.stopSimulationByGuid(simulationID);
            this.detailsResultController.setSimulationInfoByGuid(simulationID, simulationDocumentInfoDto);
            simulationIDListClicked(null);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void onResumeSimulation(String simulationID) {
        try {
            SimulationDocumentInfoDto simulationDocumentInfoDto = this.simulatorManager.resumeSimulationByGuid(simulationID);
            this.detailsResultController.setSimulationInfoByGuid(simulationID, simulationDocumentInfoDto);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void reset() {
//        executionListView.getItems().clear();
        resultComponentHolderGP.getChildren().clear();
        detailsResultController.reset();
    }
    public void startUIPollingThread() {

        try {
            if (!executionListView.getItems().isEmpty()) {
                this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
                Thread.sleep(200);
                this.scheduledExecutorService.scheduleAtFixedRate(this::pollUpdatedSimulationDocumentDto, 0, 200, TimeUnit.MILLISECONDS);
            }

        } catch (InterruptedException e) {
        }
    }
}
