package UI.impl.javaFX.tabBody.results.detailsComponent;

import static UI.impl.javaFX.common.CommonResourcesPaths.*;
import UI.impl.javaFX.tabBody.results.DetailsResultModel;
import UI.impl.javaFX.tabBody.results.detailsComponent.entity.EntityComponentController;
import javafx.event.ActionEvent;
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

    @FXML
    private Label simulationIDLbl;

    @FXML
    private Label ticksLbl;

    @FXML
    private Label timeCounterLbl;

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
    }

    @FXML
    void pauseSimulationClicked(ActionEvent event) {

    }

    @FXML
    void resumeSimulationClicked(ActionEvent event) {

    }

    @FXML
    void stopSimulationClicked(ActionEvent event) {

    }

    public void setValues(String simulationID, String numOfTicks, String timeCounter, ResultManager resultManager, String simulationIndex){
        this.simulationID.set(simulationID);
        this.numOfTicks.set(numOfTicks);
        this.timeCounter.set(timeCounter);
        this.runningSimulationButtons.setDisable(false);
        SimulationResult simulationResults = resultManager.getSimulationResultBySimulationId(simulationIndex);
//
//        for(EntitiesResult entitiesResult:simulationResults.getEntities())){
//            createEntityComponent(entitiesResult);
//        }
    }

    private void createEntityComponent(EntitiesResult entitiesResult) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(RESULT_SIMULATION_ENTITY_DETAILS_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            EntityComponentController controller = loader.getController();
            controller.setValues(entitiesResult.getName().toString(),
                    entitiesResult.getNumOfInstancesInitialized().toString(),
                    entitiesResult.getNumOfInstancesInEndOfSimulation().toString());
            entitiesContainerLV.getItems().add(gpComponent);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }
}