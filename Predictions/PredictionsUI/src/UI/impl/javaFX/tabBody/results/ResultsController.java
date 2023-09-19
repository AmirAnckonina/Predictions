package UI.impl.javaFX.tabBody.results;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.tabBody.results.detailsComponent.DetailsResultController;
import UI.impl.javaFX.tabBody.results.detailsComponent.ResultsModel;
import UI.impl.javaFX.tabBody.results.detailsComponent.histogram.byEntities.ExecutionResultByEntityController;
import UI.impl.javaFX.tabBody.results.detailsComponent.histogram.byProperty.ExecutionResultByPropertyController;
import UI.impl.javaFX.top.PredictionsTopModel;
import dto.SimulationDocumentInfoDto;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import simulator.information.simulationDocument.api.SimulationDocumentFacade;
import simulator.mainManager.api.SimulatorManager;
import simulator.result.api.SimulationResult;
import simulator.result.manager.api.ResultManager;

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
    private ExecutionResultByPropertyController executionResultByPropertyController;
    private Stage primaryStage;
    private SimulatorManager simulatorManager;
    private SimulationDocumentFacade simulationDocumentFacade;
    private ResultsModel resultsModel;
    private Map<String, SimulationResult> simulationResultMap;
    private ResultManager simulatorResultManager;
    private ExecutionResultByEntityController executionResultByEntityController = null;
    private DetailsResultController detailsResultController = null;

    @FXML private DetailsResultController executionDetailsController;

    @FXML private ListView<Label> executionListView;

    @FXML private Button reRunButton;

    @FXML private RadioButton resultByEntity;

    @FXML private GridPane resultComponentHolderGP;

    @FXML
    public void initialize() {
        executionDetailsController.setMainController(this);
    }

    public ResultsController() {
        //ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        //scheduledExecutorService.scheduleAtFixedRate(this::pollUpdatedSimulationDocumentDto, 0, 200, TimeUnit.MILLISECONDS);
    }
    @FXML
    public void reRunButtonClicked(ActionEvent event) {

    }


    @FXML
    void resultByEntityClicked(MouseEvent event) {

    }

    @FXML
    public void simulationIDListClicked(MouseEvent event) {

        try {

            String guid = this.executionListView.getSelectionModel().getSelectedItem().getText();
            SimulationDocumentInfoDto simulationDocumentInfoDto = this.simulatorManager.getLatestSimulationDocumentInfo(guid);


            createSimulationResultComponent(simulationDocumentInfoDto); //info component

// -------------------------------------------------------------------------------------------------

            SimulationResult simulationResult = simulationResultMap.get(guid);
            if (resultByEntity.isPressed()) {
                createHistogramByEntityComponent(new ArrayList<>(simulationResult.getEntities().keySet()));
            } else {
                createHistogramByPropertyComponent(simulationResult.getAllPropertiesOfAllEntities());
            }

        } catch (Exception e){
            e.printStackTrace(System.out);
        }
    }


    public void setSimulationDocumentFacade(SimulationDocumentFacade simulationDocumentFacade) {
        this.simulationDocumentFacade = simulationDocumentFacade;
    }

    private void pollUpdatedSimulationDocumentDto() {
        // get the current simulation Guid - according to what currently choosed under lost view
        String guid = this.executionListView.getSelectionModel().getSelectedItem().getText();
        SimulationDocumentInfoDto simulationDocumentDto = this.simulatorManager.getLatestSimulationDocumentInfo(guid);
        Platform.runLater(() -> {
            updateSimulationInfoUI(simulationDocumentDto);
        });
    }

    private void updateSimulationInfoUI(SimulationDocumentInfoDto simulationDocumentInfoDto) {
        executionDetailsController.setValues(simulationDocumentInfoDto.getSimulationGuid(),
                simulationDocumentInfoDto.getTickNo().toString(),
                simulationDocumentInfoDto.getTimePassedInSeconds().toString(),
                simulationDocumentInfoDto.getCurrentEntityPopulationMap(),
                simulationDocumentInfoDto.getInitialEntityPopulationMap());
    }

    private void createSimulationResultComponent(SimulationDocumentInfoDto simulationDocumentInfoDto) {
//        try
//        {
//            FXMLLoader loader = new FXMLLoader();
//            URL fxmlUrl = getClass().getResource(RESULT_SIMULATION_DETAILS_FXML_RESOURCE);
//            loader.setLocation(fxmlUrl);
//            GridPane gpComponent = loader.load();
//
//            detailsResultController = loader.getController();
//            detailsResultController.setValues(simulationDocumentInfoDto.getSimulationGuid(),
//                    simulationDocumentInfoDto.getTickNo().toString(),
//                    simulationDocumentInfoDto.getTimePassedInSeconds().toString(),
//                    simulationDocumentInfoDto.getCurrentEntityPopulationMap(),
//                    simulationDocumentInfoDto.getInitialEntityPopulationMap());
//        } catch (Exception e) {
//            e.getMessage();
//            e.printStackTrace(System.out);
//        }
    }

    private void createHistogramByPropertyComponent(List<String> propertiesList){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(RESULT_SIMULATION_PROPERTY_DETAILS_HISTOGRAM_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            this.executionResultByPropertyController = loader.getController();
            executionResultByPropertyController.setMainController(this);
            executionResultByPropertyController.setPropertiesList(propertiesList);
            resultComponentHolderGP.getChildren().clear();
            resultComponentHolderGP.getChildren().add(gpComponent);
        } catch (Exception e) {
            e.getMessage();
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

    public void entityChosenInHistogramByProperty(String propertyName){
        SimulationResult simulationResult = simulationResultMap.get(this.executionListView.getSelectionModel().getSelectedItem().toString());
        executionResultByPropertyController.clearPropertyList();
        Map<String, Integer> mappedPropertiesValuesToNumOfEntitiesWithSameValue = simulationDocumentFacade.
                getMappedPropertiesToNumOfEntitiesWithSameValues(propertyName);
        List<String> resMapped = new ArrayList<>();
        mappedPropertiesValuesToNumOfEntitiesWithSameValue.forEach((propName, numOfEntities) -> resMapped.add(propName + ": " + numOfEntities));
        executionResultByPropertyController.setPropertiesList(resMapped);
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
}
