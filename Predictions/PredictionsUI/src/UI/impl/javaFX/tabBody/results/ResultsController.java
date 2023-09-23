package UI.impl.javaFX.tabBody.results;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.tabBody.results.detailsComponent.DetailsResultController;
import UI.impl.javaFX.tabBody.results.detailsComponent.ResultsModel;
import UI.impl.javaFX.tabBody.results.detailsComponent.entityPopulationGraph.EntityPopulationGraphController;
import UI.impl.javaFX.tabBody.results.detailsComponent.histogram.ExecutionResultController;
import UI.impl.javaFX.tabBody.results.detailsComponent.histogram.byEntities.ExecutionResultByEntityController;
import UI.impl.javaFX.tabBody.results.detailsComponent.histogram.byStatistic.ExecutionResultStatisticByPropertyController;
import UI.impl.javaFX.top.PredictionsTopModel;
import dto.*;
import enums.SimulationStatus;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import simulator.information.simulationDocument.api.SimulationDocumentFacade;
import simulator.mainManager.api.SimulatorManager;
import simulator.result.api.SimulationResult;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static UI.impl.javaFX.common.CommonResourcesPaths.*;

public class ResultsController {
    private PredictionsMainController mainController;
    private PredictionsTopModel predictionsTopModel;
    private ExecutionResultController executionResultController;
    private ExecutionResultStatisticByPropertyController executionResultStatisticByPropertyController;
    private EntityPopulationGraphController entityPopulationGraphController;
    private Stage primaryStage;
    private SimulatorManager simulatorManager;
    private SimulationDocumentFacade simulationDocumentFacade;
    private ResultsModel resultsModel;
    private Map<String, SimulationResult> simulationResultMap;
    private ScheduledExecutorService scheduledExecutorService;
    private ExecutionResultByEntityController executionResultByEntityController = null;
    @FXML private DetailsResultController detailsResultController;

    @FXML private ListView<Label> executionListView;

    @FXML private Button reRunButton;

    @FXML private RadioButton resultByEntity;
    @FXML private RadioButton entityStatisticsRadioButton;
    @FXML private RadioButton simulationStatisticsRadioButton;
    @FXML private RadioButton entityGraphPopulationRadioButton;

    @FXML private GridPane resultComponentHolderGP;

    @FXML
    public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        detailsResultController.setMainController(this);
        entityStatisticsRadioButton.setToggleGroup(toggleGroup);
        simulationStatisticsRadioButton.setToggleGroup(toggleGroup);
        entityGraphPopulationRadioButton.setToggleGroup(toggleGroup);
        resultByEntity.setToggleGroup(toggleGroup);
    }

    public ResultsController() {
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
            SimulationDocumentInfoDto simulationDocumentInfoDto = this.simulatorManager.getLatestSimulationDocumentInfo(guid);

            updateSimulationResultComponent(simulationDocumentInfoDto); //info component
            startUIPollingThread();

            if(simulationDocumentInfoDto.getSimulationStatus() == SimulationStatus.STOPPED||
                simulationDocumentInfoDto.getSimulationStatus() == SimulationStatus.COMPLETED) {
                if (resultByEntity.isSelected()) {
                    List<String> simulationEntities = new ArrayList<>();
                    simulationDocumentInfoDto.getCurrentEntityPopulationMap().forEach(
                            (entityName, numOfEntities) ->simulationEntities.add(entityName + ": " + numOfEntities));
                    createHistogramByEntityComponent(simulationEntities);
                } else {
                    if(entityStatisticsRadioButton.isSelected()) {
                        createHistogramByPropertyComponent(simulatorManager.getAllEntities());
                    }
                    else if(simulationStatisticsRadioButton.isSelected()) {
                        createStatisticByPropertyComponent(simulatorManager.getAllEntities());
                    } else if (entityGraphPopulationRadioButton.isSelected()) {
                        createEntityPopulationGraphComponent();
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

    private void updateSimulationResultComponent(SimulationDocumentInfoDto simulationDocumentInfoDto) {
        detailsResultController.setValues(simulationDocumentInfoDto.getSimulationGuid(),
                (new Integer(simulationDocumentInfoDto.getTickNo() + 1)).toString(),
                simulationDocumentInfoDto.getTimePassedInSeconds().toString(),
                simulationDocumentInfoDto.getSimulationStatus().toString(),
                simulationDocumentInfoDto.getCurrentEntityPopulationMap(),
                simulationDocumentInfoDto.getInitialEntityPopulationMap());
    }

    private void createStatisticByPropertyComponent(List<String> entitiesList){
        try {
            if (this.executionListView.getSelectionModel().getSelectedItem() != null) {
                // get the current simulation Guid - according to what currently choosed under lost view
                String guid = this.executionListView.getSelectionModel().getSelectedItem().getText();
                PropertiesConsistencyDto simulationDocumentDto =
                        this.simulatorManager.getEntitiesPropertiesConsistencyMapByGuid(guid);
                PropertiesAvgConsistencyDto propertiesAvgConsistencyDto =
                        this.simulatorManager.geEntitiesNumericPropertiesAverageByGuid(guid);

                FXMLLoader loader = new FXMLLoader();
                URL fxmlUrl = getClass().getResource(RESULT_SIMULATION_PROPERTY_STATIC_HISTOGRAM_FXML_RESOURCE);
                loader.setLocation(fxmlUrl);
                GridPane gpComponent = loader.load();

                executionResultController = loader.getController();
                executionResultController.setMainController(this);
                executionResultController.setLeftEntitiesList(entitiesList);
                executionResultController.setPropertiesAvgConsistencyDto(propertiesAvgConsistencyDto);
                executionResultController.setPropertiesConsistencyDto(simulationDocumentDto);

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

            executionResultController = loader.getController();
            executionResultController.setMainController(this);
            executionResultController.setLeftEntitiesList(entitiesList);
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
            URL fxmlUrl = getClass().getResource(RESULT_SIMULATION_ENTITIES_GRAPH_HISTOGRAM_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            String guid = this.executionListView.getSelectionModel().getSelectedItem().getText();
            EntityPopulationOvertimeDto entityPopulationOvertimeDto =
                    this.simulatorManager.getEntityPopulationOvertimeByGuid(guid);

            entityPopulationGraphController = loader.getController();
            entityPopulationGraphController.initEntityPopulationLineChart(entityPopulationOvertimeDto);
            resultComponentHolderGP.getChildren().clear();
            resultComponentHolderGP.getChildren().add(gpComponent);
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
        List<String> resList = simulatorManager.getPropertiesByEntity(entityName);
        executionResultController.setPropertiesList(resList);
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

        executionResultController.setRightEntitiesList(mappedPropertiesList);
    }

    public void simulationTabClicked() {

        //Consider use it under bottom component that responsible to show result (histogram ... )
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

    public void setMainController(PredictionsMainController mainController) {
        this.mainController = mainController;
    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void setResultsModel(ResultsModel resultsModel) {
        this.resultsModel = resultsModel;
    }
    public void setSimulatorManager(SimulatorManager simulatorManager) {
        this.simulatorManager = simulatorManager;
    }

    private String getSimulatorStartingTimeInString(SimulationResult simulationResults){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy | HH.mm.ss");
        String formattedTime = formatter.format(new Date(simulationResults.getSimulationStartingTime()));

        return formattedTime;
    }

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
