package UI.impl.javaFX.tabBody.newExecution.newExecutionMain;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.tabBody.newExecution.components.entityPopulation.EntityPopulationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static UI.impl.javaFX.common.CommonResourcesPaths.ENTITY_POPULATION_FXML_RESOURCE;


public class NewExecutionController {

    @FXML private Button clearVarButton;
    @FXML private Button startButton;

    private SimulatorManager simulatorManager;
    private Map<String, EntityPopulationController> entityPopulationControllerMap;
    private PredictionsMainController predictionsMainController;
    private Stage primaryStage;

    public NewExecutionController() {
        this.entityPopulationControllerMap = new HashMap<>();
    }

    @FXML
    private void initialize() {

    }

    public void setPredictionsMainController(PredictionsMainController predictionsMainController) {
        this.predictionsMainController = predictionsMainController;
    }

    @FXML
    void onClearVarClicked() {

    }

    @FXML
    void onStartButtonClicked() {

    }

    /**
     * Should be called by the AppController
     */
    public void initializeNewExecutionTab() {

        try {
            List<String> entities = this.simulatorManager.getAllEntities();
            entities.forEach(this::createEntityPopulationComponent);

        } catch (Exception e) {

        }
    }

    private void createEntityPopulationComponent(String entityName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENTITY_POPULATION_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            Node singleComponent = loader.load();

            EntityPopulationController entityPopulationController = loader.getController();
            entityPopulationController.setPredictionsMainController(this.predictionsMainController);
            entityPopulationController.setNewExecutionController(this);
            entityPopulationController.initSetupForEntityPopulation(entityName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSingleEntityPopulation(String entityName, int population) {

        try {
             this.simulatorManager.setEntityDefinitionPopulation(entityName, population);
        } catch (Exception e) {
            // Impl here somthing like red color around the textfield in the sub comp
        }
    }

    public void setSimulatorManager(SimulatorManager simulatorManager) {
        this.simulatorManager = simulatorManager;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
