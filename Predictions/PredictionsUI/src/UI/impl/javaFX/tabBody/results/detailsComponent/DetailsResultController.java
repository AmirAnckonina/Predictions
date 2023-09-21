package UI.impl.javaFX.tabBody.results.detailsComponent;

import static UI.impl.javaFX.common.CommonResourcesPaths.*;
import UI.impl.javaFX.tabBody.results.DetailsResultModel;
import UI.impl.javaFX.tabBody.results.ResultsController;
import UI.impl.javaFX.tabBody.results.detailsComponent.entity.EntityComponentController;
import UI.impl.javaFX.utils.exception.PredictionsUIComponentException;
import dto.SimulationDocumentInfoDto;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import simulator.execution.instance.entity.impl.EntitiesResult;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import simulator.result.api.SimulationResult;
import simulator.result.manager.api.ResultManager;

import java.net.URL;
import java.util.List;
import java.util.Map;


public class DetailsResultController extends DetailsResultModel {

    private ResultsController mainController;

    @FXML
    private Label simulationIDLbl;

    @FXML
    private Label ticksLbl;

    @FXML
    private Label timeCounterLbl;

    @FXML
    private Label statusLbl;

    @FXML
    private Button stopRunningSimulationBtn;

    @FXML
    private Button pauseRunningSimulationBtn;

    @FXML
    private Button resumeRunningSimulationBtn;

    @FXML
    private ListView<GridPane> entitiesContainerLV;

    @FXML
    private HBox runningSimulationButtons;

    @FXML
    public void initialize(){
        simulationIDLbl.textProperty().bind(simulationID);
        ticksLbl.textProperty().bind(numOfTicks);
        timeCounterLbl.textProperty().bind(timeCounter);
        statusLbl.textProperty().bind(status);
    }

    @FXML
    void pauseSimulationClicked(ActionEvent event) {
        this.mainController.onPauseSimulation(this.simulationID.get());
    }

    @FXML
    void resumeSimulationClicked(ActionEvent event) {
        this.mainController.onResumeSimulation(this.simulationID.get());
    }

    @FXML
    void stopSimulationClicked(ActionEvent event) {
        this.mainController.onStopSimulation(this.simulationID.get());
    }

    public void setMainController(ResultsController mainController) {
        this.mainController = mainController;
    }

    public void setValues(String simulationID, String numOfTicks, String timeCounter, String status, Map<String, Integer> mappedEntityToNumOfInstances,
                          Map<String, Integer> mappedEntityToNumOfInstancesInitialized){
        this.simulationID.set(simulationID);
        this.numOfTicks.set(numOfTicks);
        this.timeCounter.set(timeCounter);
        this.status.set(status);
        if(status.toLowerCase().equals("completed") || status.toLowerCase().equals("stop")){
            for (Node node : runningSimulationButtons.getChildren()) {
                if (node instanceof Button) {
                    ((Button) node).setDisable(true);
                }
            }


        }else {
            for (Node node : runningSimulationButtons.getChildren()) {
                if (node instanceof Button) {
                    ((Button) node).setDisable(false);
                }
            }


        }
        entitiesContainerLV.getItems().clear();
        mappedEntityToNumOfInstances.forEach((entityName, numOfEntity) -> createEntityComponent(entityName,
                mappedEntityToNumOfInstancesInitialized.get(entityName), numOfEntity));
    }

    private void createEntityComponent(String entityName, Integer originNumOfInstance, Integer currNumOfEntities) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(RESULT_SIMULATION_ENTITY_DETAILS_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            EntityComponentController controller = loader.getController();
            controller.setValues(entityName + ": ", currNumOfEntities.toString(), originNumOfInstance.toString());
            entitiesContainerLV.getItems().add(gpComponent);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }

    public void setSimulationInfoByGuid(String simulationID, SimulationDocumentInfoDto simulationDocumentInfoDto) {
        //Amir
    }

    public void reset() {
        entitiesContainerLV.getItems().clear();
        simulationID.set("");
        numOfTicks.set("");
        timeCounter.set("");
        status.set("");
    }
}
