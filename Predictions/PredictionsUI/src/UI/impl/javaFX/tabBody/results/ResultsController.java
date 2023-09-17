package UI.impl.javaFX.tabBody.results;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.tabBody.results.detailsComponent.DetailsResultController;
import UI.impl.javaFX.tabBody.results.detailsComponent.entity.EntityComponentController;
import UI.impl.javaFX.top.PredictionsTopModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;
import simulator.result.api.SimulationResult;
import simulator.result.manager.api.ResultManager;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.List;
import java.util.Map;

import static UI.impl.javaFX.common.CommonResourcesPaths.RESULT_SIMULATION_DETAILS_FXML_RESOURCE;
import static UI.impl.javaFX.common.CommonResourcesPaths.RESULT_SIMULATION_ENTITY_DETAILS_FXML_RESOURCE;

public class ResultsController {

    private PredictionsMainController mainController;
    private PredictionsTopModel predictionsTopModel;
    private Stage primaryStage;
    private SimulatorManager simulatorManager;
    private ResultsModel resultsModel;
    private Map<String, SimulationResult> simulationResultMap;
    private ResultManager simulatorResultManager;


    @FXML
    private ListView<Label> executionListView;

    @FXML
    private Button reRunButton;

    @FXML
    private ListView<?> executionDetailsListView;

    @FXML
    public void initialize() {
    }

    @FXML
    public void reRunButtonClicked(ActionEvent event) {

    }

    @FXML
    public void simulationIDListClicked(MouseEvent event) {
        SimulationResult simulationResult = simulationResultMap.get(this.executionListView.getItems().toString());

        createSimulationResultComponent(simulationResult, this.executionListView.getItems().toString());
    }

    private void createSimulationResultComponent(SimulationResult simulationResult, String simulationIndex) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(RESULT_SIMULATION_DETAILS_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            DetailsResultController controller = loader.getController();
            controller.setValues(simulationResult.getSimulationUuid(),
                    simulationResult.getTerminationReason().name(),
                    simulationResult.getTerminationReason().name(),
                    simulatorResultManager, simulationIndex);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }

    public void simulationTabClicked(){
        this.executionListView.getItems().clear();
        this.simulatorResultManager = this.simulatorManager.getSimulatorResultManagerImpl();
        List<SimulationResult> simulationResults = this.simulatorResultManager.getSortedHistoricalSimulationsList();

        for(SimulationResult simulationResult:simulationResults){

        }
        for (int i = 0; i < simulationResults.size(); i++) {
            Label simulationIDLbl = new Label();
            simulationIDLbl.setText((i + 1) + ". SimulationUuid:\"" + simulationResults.get(i).getSimulationUuid() + "\"" + ": "
                    + getSimulatorStartingTimeInString(simulationResults.get(i)));
            System.out.println();
            executionListView.getItems().add(simulationIDLbl);

        }
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
}
