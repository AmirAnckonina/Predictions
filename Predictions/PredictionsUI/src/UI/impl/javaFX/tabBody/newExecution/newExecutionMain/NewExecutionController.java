package UI.impl.javaFX.tabBody.newExecution.newExecutionMain;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.tabBody.newExecution.components.entityPopulation.EntityPopulationController;
import UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.KeyValueProperty;
import UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.bool.EnvironmentBooleanVariableController;
import UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.floats.EnvironmentFloatVariableController;
import UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.string.EnvironmentStringVariableController;
import UI.impl.javaFX.utils.exception.PredictionsUIComponentException;
import dto.EnvironmentPropertyDto;
import dto.SimulationDocumentInfoDto;
import enums.SetPropertyStatus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static UI.impl.javaFX.common.CommonResourcesPaths.*;


public class NewExecutionController {

    @FXML private ListView<GridPane> entPopulationListView;
    @FXML private ListView<GridPane> envPropListView;
    @FXML private Button clearVarButton;
    @FXML private Button startButton;
    @FXML private Label maxPopLabel;
    @FXML private ProgressIndicator loadingProgressIndicator;

    private SimulatorManager simulatorManager;
    private Map<String, EntityPopulationController> entityPopulationControllerMap;
    private Map<String, KeyValueProperty> environmentPropertyControllerMap;
    private PredictionsMainController predictionsMainController;
    private Stage primaryStage;
    private boolean firstSimulationStartedFlag;

    public NewExecutionController() {
        this.entityPopulationControllerMap = new HashMap<>();
        this.environmentPropertyControllerMap = new HashMap<>();
        firstSimulationStartedFlag = false;
    }

    @FXML
    private void initialize() {
        loadingProgressIndicator.setVisible(false);
    }

    public void setPredictionsMainController(PredictionsMainController predictionsMainController) {
        this.predictionsMainController = predictionsMainController;
    }

    @FXML
    void onClearVarClicked() {
        entityPopulationControllerMap.forEach((entityName, controller) -> controller.clearAndResetProperty());
        environmentPropertyControllerMap.forEach((envPropName, controller) -> controller.clearAndResetProperty());
    }

    @FXML
    void onStartButtonClicked() {
        loadingProgressIndicator.setVisible(true);
        try {
            SimulationDocumentInfoDto simulationDocumentInfoDto = this.simulatorManager.runSimulator();
            if (!firstSimulationStartedFlag) {
                firstSimulationStartedFlag = true;
                this.predictionsMainController.onFirstSimulationStarted();
            }
            this.predictionsMainController.onNewSimulationStart(simulationDocumentInfoDto.getSimulationGuid());
            predictionsMainController.moveToResultTab();
            loadingProgressIndicator.setVisible(false);

        } catch (Exception e) {
            e.printStackTrace(System.out);

        } finally {
            this.simulatorManager.resetAllManualSetup();
            initializeNewExecutionTab();
        }
    }

    public void initializeNewExecutionTab() {

        try {
            resetController();
            this.maxPopLabel.textProperty().set(this.maxPopLabel.textProperty().get() + " " + this.simulatorManager.getMaxPopulationSize());

            List<String> entities = this.simulatorManager.getAllEntities();
            entities.forEach(this::createEntityPopulationComponent);

            List<EnvironmentPropertyDto> envPropsDtoList = this.simulatorManager.getAllEnvironmentProperties();
            envPropsDtoList.forEach(this::createEnvironmentPropertyComponent);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void resetController() {
        this.entPopulationListView.getItems().clear();
        this.envPropListView.getItems().clear();
        this.entityPopulationControllerMap = new HashMap<>();
        this.environmentPropertyControllerMap = new HashMap<>();
    }

    private void createEnvironmentPropertyComponent(EnvironmentPropertyDto envProp) {

        switch (envProp.getPropertyType()) {
            case STRING:
                createEnvironmentStringVariableComponent(envProp);
                break;
            case FLOAT:
            case DECIMAL:
                createEnvironmentFloatVariableComponent(envProp);
                break;
            case BOOLEAN:
                createEnvironmentBooleanVariableComponent(envProp);
                break;
            default:
                throw new PredictionsUIComponentException("Can't create GridPane for key-value");
        }
    }

    private void createEnvironmentBooleanVariableComponent(EnvironmentPropertyDto envProp) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENV_BOOLEAN_VAR_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            Node gpComponent = loader.load();

            EnvironmentBooleanVariableController controller = loader.getController();
            controller.setNewExecutionController(this);
            controller.initSetupForEnvBooleanVariable(envProp.getEnvPropName());
            envPropListView.getItems().add((GridPane) gpComponent);
            environmentPropertyControllerMap.put(envProp.getEnvPropName(), controller);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace(System.out);
        }
    }

    private void createEnvironmentFloatVariableComponent(EnvironmentPropertyDto envProp) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENV_FLOAT_VAR_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            Node gpComponent = loader.load();

            EnvironmentFloatVariableController controller = loader.getController();
            controller.setNewExecutionController(this);
            controller.initSetupForEnvFloatVariable(envProp.getEnvPropName(), envProp.getRange());
            envPropListView.getItems().add((GridPane) gpComponent);
            environmentPropertyControllerMap.put(envProp.getEnvPropName(), controller);

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace(System.out);
        }

    }

    private void createEnvironmentStringVariableComponent(EnvironmentPropertyDto envProp) {

        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENV_STRING_VAR_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            Node gpComponent = loader.load();

            EnvironmentStringVariableController controller = loader.getController();
            controller.setNewExecutionController(this);
            controller.initSetupForEnvStringVariable(envProp.getEnvPropName());
            envPropListView.getItems().add((GridPane) gpComponent);
            environmentPropertyControllerMap.put(envProp.getEnvPropName(), controller);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace(System.out);
        }

    }

    private void createEntityPopulationComponent(String entityName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENTITY_POPULATION_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            Node gpComponent = loader.load();

            EntityPopulationController entityPopulationController = loader.getController();
            entityPopulationController.setNewExecutionController(this);
            entityPopulationController.initSetupForEntityPopulation(entityName);
            entPopulationListView.getItems().add((GridPane) gpComponent);
            entityPopulationControllerMap.put(entityName, entityPopulationController);
        } catch (IOException ioe) {
//            System.out.println(ioe.getMessage());
            ioe.printStackTrace(System.out);
//            System.out.println(ioe.getMessage());
            //throw new PredictionsUIComponentException("failed to load component under GridPaneFactory.");
        } catch (Exception e) {
//            e.getMessage();
            e.printStackTrace(System.out);
//            System.out.println(e.getMessage());
        }
    }

    public void setSingleEntityPopulation(String entityName, Integer population) {

        try {
             this.simulatorManager.setEntityDefinitionPopulation(entityName, population);
             this.entityPopulationControllerMap.get(entityName).setStatus(SetPropertyStatus.SUCCEEDED);
        } catch (Exception e) {
            this.entityPopulationControllerMap.get(entityName).setStatus(SetPropertyStatus.FAILED);
        }
    }

    public <T> void setEnvironmentProperty(String envPropertyName, T envPropertyValue) {
        try {
            this.simulatorManager.setEnvironmentPropertyValue(envPropertyName, envPropertyValue);
            this.environmentPropertyControllerMap.get(envPropertyName).setStatus(SetPropertyStatus.SUCCEEDED);
        } catch (Exception e) {
            this.environmentPropertyControllerMap.get(envPropertyName).setStatus(SetPropertyStatus.FAILED);
        }
    }

    public void setSimulatorManager(SimulatorManager simulatorManager) {
        this.simulatorManager = simulatorManager;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void rollbackSingleEntityPopulation(String entityName) {
        this.simulatorManager.resetSingleEntityPopulation(entityName);
        this.entityPopulationControllerMap.get(entityName).setStatus(SetPropertyStatus.NONE);
    }

    public void rollbackSingleEnvironmentVariable(String envVarName) {
        this.simulatorManager.resetSingleEnvironmentVariable(envVarName);
        this.environmentPropertyControllerMap.get(envVarName).setStatus(SetPropertyStatus.NONE);
    }
}
