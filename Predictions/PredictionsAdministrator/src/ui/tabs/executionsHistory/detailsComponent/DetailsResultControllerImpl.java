package ui.tabs.executionsHistory.detailsComponent;

import dto.simulationInfo.SimulationDocumentInfoDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ui.tabs.executionsHistory.detailsComponent.entity.EntityComponentController;
import ui.tabs.executionsHistory.ExecutionsHistoryController;

import java.net.URL;
import java.util.Map;

import static ui.common.CommonResourcesPaths.RESULT_SIMULATION_ENTITY_DETAILS_FXML_RESOURCE;


public class DetailsResultControllerImpl extends ResultsModel {

    private ExecutionsHistoryController mainController;

    @FXML
    private Label simulationIDLbl;

    @FXML
    private Label ticksLbl;

    @FXML
    private Label timeCounterLbl;

    @FXML
    private Label statusLbl;

    @FXML
    private ListView<GridPane> entitiesContainerLV;

    @FXML
    private HBox runningSimulationButtons;

    @FXML
    public void initialize() {
//        ToggleGroup toggleGroup = new ToggleGroup();
//        detailsResultController.setMainController(this);
//        entityStatisticsRadioButton.setToggleGroup(toggleGroup);
//        simulationStatisticsRadioButton.setToggleGroup(toggleGroup);
//        entityGraphPopulationRadioButton.setToggleGroup(toggleGroup);
//        resultByEntity.setToggleGroup(toggleGroup);
    }

    public void setMainController(ExecutionsHistoryController mainController) {
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
