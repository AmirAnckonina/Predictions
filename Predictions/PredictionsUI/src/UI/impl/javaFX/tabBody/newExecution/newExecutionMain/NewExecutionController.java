package UI.impl.javaFX.tabBody.newExecution.newExecutionMain;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.tabBody.newExecution.components.entityPopulation.EntityPopulationController;
import UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.EnvironmentPropertyController;
import UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.bool.EnvironmentBooleanVariableController;
import UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.floats.EnvironmentFloatVariableController;
import UI.impl.javaFX.tabBody.newExecution.components.environmentVariable.string.CalculationActionController;
import UI.impl.javaFX.utils.exception.PredictionsUIComponentException;
import dto.EnvironmentPropertyDto;
import enums.PropertyType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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

    private SimulatorManager simulatorManager;
    private Map<String, EntityPopulationController> entityPopulationControllerMap;
    private Map<String, EnvironmentPropertyController> environmentPropertyControllerMap;
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

            List<EnvironmentPropertyDto> envPropsDtoList = this.simulatorManager.getAllEnvironmentProperties();
            envPropsDtoList.forEach(this::createEnvironmentPropertyComponent);

        } catch (Exception e) {
            System.out.println("t");
        }
    }

    private void createEnvironmentPropertyComponent(EnvironmentPropertyDto envProp) {
        String propName = envProp.getEnvPropName();
        PropertyType propType = envProp.getPropertyType();

        switch (propType) {
            case STRING:
                createEnvironmentStringVariableComponent(propName, propType);
                break;
            case FLOAT:
            case DECIMAL:
                createEnvironmentFloatVariableComponent(propName, propType);
                break;
            case BOOLEAN:
                createEnvironmentBooleanVariableComponent(propName, propType);
                break;
            default:
                throw new PredictionsUIComponentException("Can't create GridPane for key-value");
        }
    }

    private void createEnvironmentBooleanVariableComponent(String propName, PropertyType propType) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENV_BOOLEAN_VAR_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            EnvironmentBooleanVariableController controller = loader.getController();
            controller.setNewExecutionController(this);
            controller.initSetupForEnvBooleanVariable(propName);
            envPropListView.getItems().add(gpComponent);
            environmentPropertyControllerMap.put(propName, controller);
        } catch (IOException ioe) {

        }
    }

    private void createEnvironmentFloatVariableComponent(String propName, PropertyType propType) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENV_FLOAT_VAR_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            EnvironmentFloatVariableController controller = loader.getController();
            controller.setNewExecutionController(this);
            controller.initSetupForEnvFloatVariable(propName);
            envPropListView.getItems().add(gpComponent);
            environmentPropertyControllerMap.put(propName, controller);

        } catch (IOException ioe) {

        }

    }

    private void createEnvironmentStringVariableComponent(String propName, PropertyType propType) {

        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENV_STRING_VAR_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            CalculationActionController controller = loader.getController();
            controller.setNewExecutionController(this);
            controller.initSetupForEnvStringVariable(propName);
            envPropListView.getItems().add(gpComponent);
            environmentPropertyControllerMap.put(propName, controller);
        } catch (IOException ioe) {

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
//            ioe.printStackTrace(System.out);
//            System.out.println(ioe.getMessage());
            //throw new PredictionsUIComponentException("failed to load component under GridPaneFactory.");
        } catch (Exception e) {
//            e.getMessage();
//            e.printStackTrace(System.out);
//            System.out.println(e.getMessage());
        }
    }

    public void setSingleEntityPopulation(String entityName, int population) {

        try {
             this.simulatorManager.setEntityDefinitionPopulation(entityName, population);
        } catch (Exception e) {
            // Impl here something like red color around the textfield in the sub comp
        }
    }

    public <T> void setEnvironmentProperty(String envPropertyName, T envPropertyValue) {
        try {
            this.simulatorManager.setEnvironmentPropertyValue(envPropertyName, envPropertyValue);
        } catch (Exception e) {

        }
    }

    public void setSimulatorManager(SimulatorManager simulatorManager) {
        this.simulatorManager = simulatorManager;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
